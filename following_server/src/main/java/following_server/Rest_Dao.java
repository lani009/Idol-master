package following_server;

import static following_server.jdbc.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.servlet.annotation.WebServlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import following_server.jdbc.connection.ConnectionProvider;

/**
 * Singleton Pattern: RESTFUL DAO
 * 
 * @author song
 *
 */
@WebServlet("/Rest")

public class Rest_Dao {
	private Rest_Dao() {

	}

	/**
	 * 로그인
	 * 
	 * @param id
	 * @return true or false
	 */
	public boolean isLogin(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			if (conn != null) {
				System.out.println("커넥팅");
			} else {
				System.out.println("다시해이새끼야");
			}
			pstmt = conn.prepareStatement("select username from user where username=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // RS -> ResultSet 객체

			rs.last();
			if (rs.getRow() != 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			System.out.println("로그인오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return result;
	}

	/**
	 * 취향 불러오기
	 * 
	 * @return 취향 태그
	 */
	@SuppressWarnings("unchecked")
	public String getTaste(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject obj = new JSONObject();
		try {
			conn = ConnectionProvider.getConnection();
		
		
			pstmt = conn.prepareStatement("SELECT * FROM user_taste Where username=?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			rs.first();
			if (rs.getRow() == 0) {
				// 예외처리
			}
			rs.beforeFirst();
			// System.out.println("수정 됨");
			JSONArray temp = new JSONArray();
			while (rs.next()) {
				// System.out.println(rs.getString("tagcontent"));
				temp.add(rs.getString("tagcontent"));
			}
			obj.put("user", temp);
			
		} catch (Exception e) {
			System.out.println("리뷰불러오기오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return obj.toString();
	}

	/**
	 * 장소태그 불러오기
	 * @param id 유저 아이디
	 * @return JSONObejct 리스트
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<JSONObject> getPlaceTag(String id) {
		Connection conn = null;
		ResultSet place_character = null;	// 장소-태그 테이블
		ResultSet user_taste = null;
		List<JSONObject> jsonList = new ArrayList<>();
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("SELECT  * FROM place_tag_idol WHERE place_tag_idol.name=(SELECT idol.name FROM user INNER JOIN idol ON user.fandom = idol.id WHERE user.username=?)");
			pstmt.setString(1, id);
			place_character = pstmt.executeQuery();
			
			place_character.first();
			if (place_character.getRow() == 0) {
				return null;
			}
			
			place_character.beforeFirst();
			pstmt = conn.prepareStatement("SELECT * FROM user_taste Where username=?");
			pstmt.setString(1, id);
			user_taste = pstmt.executeQuery();
			
			user_taste.first();
			// if (user_taste.getRow() == 0) {
			// }
			// !! user_taste는 없을 경우가 없음 !!
			user_taste.beforeFirst();
			JSONArray userJsonArray = new JSONArray();	// 유저에 대한 태그 "user": [태그들]
			while (user_taste.next()){
				userJsonArray.add(user_taste.getString("tagcontent"));
			}
			HashMap<String, JSONArray> placeMap = new HashMap<>();
			JSONArray temp = null;
			while (place_character.next()) {
				if (placeMap.containsKey(place_character.getString("place"))) {
					// 만약에 해당 place에 대한 Key 가 이미 등록 되어 있을 경우
					temp = placeMap.get(place_character.getString("place"));
					temp.add(place_character.getString("tagcontent"));
				} else {
					// 만약에 해당 place에 대한 key 가 이미 등록 되어 있지 않을 경우
					temp = new JSONArray();
					temp.add(place_character.getString("tagcontent"));
					placeMap.put(place_character.getString("place"), temp);
				}	// place는 완성
			}
			placeMap.keySet().stream().forEach(key -> {
				// tag 5개씩 자르기
				JSONArray placeTemp = placeMap.get(key);
				if (placeTemp.size() > 5) {
					// 만약에 리스트 사이즈가 5보다 크다면, 0~5까지 자름
					placeTemp.subList(0, 5);
				}
			});
			placeMap.keySet().stream().forEach(i -> {
				// 디버깅용
				System.out.println("\nPlace Tag content");
				System.out.println(i + ": " + placeMap.get(i).toJSONString() + "\n");
			});
			placeMap.keySet().stream().forEach(key -> {
				JSONObject jsonObjectTemp = new JSONObject();	// 임시 변수
				jsonObjectTemp.put("place", placeMap.get(key));
				jsonObjectTemp.put("user", userJsonArray);
				jsonObjectTemp.put("placeName", key);
				jsonList.add(jsonObjectTemp);
			});
		} catch (Exception e) {
			System.out.println("리뷰불러오기오류");
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return jsonList;
	}

	@SuppressWarnings("unchecked")
	public String showTaste(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject obj = new JSONObject();
		try {
			int count =0;
			conn = ConnectionProvider.getConnection();
		
		
			pstmt = conn.prepareStatement("SELECT tagcontent FROM tag ORDER BY tagcount DESC");
			rs = pstmt.executeQuery();
			rs.first();
			// if (rs.getRow() == 0) {
			// }
			// !! 태그가 없을 수가 없음 !!
			rs.beforeFirst();
			JSONArray temp = new JSONArray();
			while (count<10) {
				rs.next();
				temp.add(rs.getString("tagcontent"));
				count++;
			}
			obj.put("taste", temp);
			
		} catch (Exception e) {
			System.out.println("리뷰불러오기오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return obj.toString();



	}

	/**
	 * 취향 저장
	 * 
	 * @param tag
	 */
	public boolean setTaste(String username, String tag) {/// 클라이언트 측에서 태그 선택 개수 만큼 메서드 실행 바람. 1번에 1개씩 저장가능
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(
					"INSERT INTO user_tag (user_id, tag_id) VALUES ( (SELECT id from user WHERE username=?), (SELECT id from tag WHERE tagcontent=?) )");
			pstmt.setString(1, username);
			pstmt.setString(2, tag);
			pstmt.executeUpdate();
			close(pstmt);
			pstmt = conn.prepareStatement("update tag set tagcount = tagcount + 1 where tagcontent = ?");
					pstmt.setString(1, tag);
					pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("취향등록 오류");
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
			close(conn);
		}
		return true;

	}

	public String getPlace() {
		return null;
	}

	/**
	 * 리뷰를 가져와서 리스트로 띄우는 용도
	 * 
	 * @return 리뷰
	 */
	@SuppressWarnings("unchecked")
	public String getReview(String place) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject obj = new JSONObject();
		try {
			conn = ConnectionProvider.getConnection();
			JSONArray reviews = new JSONArray();
			pstmt = conn.prepareStatement("SELECT * FROM place_review Where place_name=?");
			pstmt.setString(1, place);
			rs = pstmt.executeQuery();
			
			JSONObject temp = null;
			while (rs.next()) {
				temp = new JSONObject();	// 작성자와 리뷰를 담는 임시 변수
				temp.put("writer", rs.getString("username"));
				temp.put("content", rs.getString("review"));
				reviews.add(temp);
			}
			obj.put("content", reviews);

		} catch (Exception e) {
			System.out.println("리뷰불러오기오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return obj.toString();
	}

	/**
	 * 리뷰작성
	 * 
	 * @param review 리뷰
	 */
	public boolean setReview(String place, String id, String review) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		DeepLearningSocket dls = new DeepLearningSocket();
		ResultSet rs= null;
		ResultSet rss = null;
		ResultSet rsss = null;
		try {
			JSONParser parser = new JSONParser();
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(
					"INSERT INTO review (palce_id, writer_id, content) VALUES ((SELECT id from place WHERE place=?), (SELECT id FROM user WHERE username=?), ?)");
			pstmt.setString(1, place);
			pstmt.setString(2, id);
			pstmt.setString(3, review);
			pstmt.executeUpdate();
			String reviewTag = dls.sendReview(review);
			JSONObject tag = (JSONObject) parser.parse(reviewTag);
			System.out.println(tag);
			JSONArray insertTag = (JSONArray) tag.get("tag");
			System.out.println(insertTag);
			String[] arr = new String[insertTag.size()];
			for(int i = 0; i<insertTag.size();i++){
				arr[i]= (String) insertTag.get(i);
			}
			//["조명 예쁘음","주차장 넓음"]
			//for문으로 하나씩 insert 시키는데, 만약 이미 있을 경우 카운트만 1증가 시키고 없을 경우 추가 시키고 count 1 증가
			for(int i=0; i<arr.length; i++){
				pstmt = conn.prepareStatement("select tagcontent from tag where tagcontent=?");
				pstmt.setString(1, arr[i]);
				rs=pstmt.executeQuery();

			
			
				if (!rs.next()){
					System.out.println("꼬몬요!");
					pstmt2 = conn.prepareStatement("INSERT INTO tag (tagcontent, tagcount) VALUES (?,1)");
					pstmt2.setString(1, arr[i]);
					pstmt2.executeUpdate();
					close(pstmt2);
				} else {
					pstmt2 = conn.prepareStatement("update tag set tagcount = tagcount + 1 where tagcontent = ?");
					pstmt2.setString(1, arr[i]);
					pstmt2.executeUpdate();
					close(pstmt2);
				}
				close(pstmt);
			}
			//사용자가 갔던 장소를 추가
			
			pstmt = conn.prepareStatement("select (SELECT id FROM place WHERE place=?) from user_place where user_id=(SELECT id from user WHERE username=?)");
				pstmt.setString(1,place );
				pstmt.setString(2,id);
				rss=pstmt.executeQuery();
			
			
				if (!rss.next()){
					System.out.println("꼬몬요!");
					pstmt = conn.prepareStatement(
					"INSERT INTO user_place (user_id, place_id) VALUES ((SELECT id from user WHERE username=?), (SELECT id FROM place WHERE place=?))");
					pstmt.setString(1, id);
					pstmt.setString(2, place);
					pstmt.executeUpdate();
				}
			    close(pstmt);
				//리뷰에서 받아온 태그 arr for 문돌리면서 place_tag 에 추가시킨다.
				//if(태그가 존재하지 않을 경우) 새로 추가하고 카운트 1 추가
				//else 카운트 1 증가
				for(int i=0; i<arr.length; i++){
					pstmt = conn.prepareStatement("select tag_id from place_tag where place_id=(SELECT id from place WHERE place=?) and tag_id=(SELECT id from tag WHERE tagcontent=?)");
					pstmt.setString(1, place);
					pstmt.setString(2, arr[i]);
					rsss = pstmt.executeQuery();
					if (!rsss.next()){
						System.out.println("꼬몬요!");
						pstmt2 = conn.prepareStatement("INSERT INTO place_tag (place_id,tag_id,tagcount) VALUES ((SELECT id from place WHERE place=?), (SELECT id from tag WHERE tagcontent=?),1)");
						pstmt2.setString(1, place);
						pstmt2.setString(2, arr[i]);
						pstmt2.executeUpdate();
						close(pstmt2);
					}else{
						pstmt2 = conn.prepareStatement("update place_tag set tagcount = tagcount + 1 where tag_id = (SELECT id from tag WHERE tagcontent=?) AND place_id =(SELECT id from place WHERE place=?)");
						pstmt2.setString(1, arr[i]);
						pstmt2.setString(2, place);
						pstmt2.executeUpdate();
					}
				}

		} catch (Exception e) {
			System.out.println("리뷰등록 오류");
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
			close(pstmt2);
			close(conn);
			close(dls);
		}
		return true;
	}

	/**
	 * 추천
	 * @param id 유저 아이디
	 * @return 추천 장소
	 */
	@SuppressWarnings("unchecked")
	public String recommend(String id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		DeepLearningSocket dls = new DeepLearningSocket(); // 딥러닝 소켓 연결 수행
		
		try {
			conn = ConnectionProvider.getConnection();	// 커넥션 받아옴
			List<JSONObject> userPlaceList = getPlaceTag(id);	// place태그, 유저 태그 JSON 리스트
			String[][] userPlaceDistance = new String[userPlaceList.size()][2];
			int cnt = 0;
			for (JSONObject jsonObject : userPlaceList) {
				userPlaceDistance[cnt][0] = (String) jsonObject.get("placeName");
				userPlaceDistance[cnt][1] = dls.placeUserSim(jsonObject.toJSONString());
				cnt++;
			}	// userPlaceList= [["장소명", "거리"], ["장소명", "거리"], ...]

			// 거리가 낮은 순서 대로 정렬
			for (int i = userPlaceDistance.length-1; i > 0; i--) {
				for (int j = 0; j < i; j++) {
					if (Double.parseDouble(userPlaceDistance[j][1]) > Double.parseDouble(userPlaceDistance[j + 1][1])) {
						String[] temp = userPlaceDistance[j];
						userPlaceDistance[j] = userPlaceDistance[j + 1];
						userPlaceDistance[j + 1] = temp;
					}
				}
			}
			for (int i = 0; i < userPlaceDistance.length; i++) {
				System.out.printf("장소명: %s 거리: %s\n", userPlaceDistance[i][0], userPlaceDistance[i][1]);
			}
			JSONArray popularPlace = new JSONArray();
			for (int i = 0; i < 5; i++) {
				// 5개 까지 뽑음
				if (userPlaceDistance.length <= i) break;
				popularPlace.add(userPlaceDistance[i][0]);
			}

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("place", popularPlace);

			return jsonObject.toJSONString();
		} catch (Exception e) {
			System.out.println("리뷰불러오기오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
			close(dls);
		}
		return null;
	}
	private static class Holder {
		public static final Rest_Dao INSTANCE = new Rest_Dao();
	}

	public static Rest_Dao getInstance() {
		return Holder.INSTANCE;
	}
}
