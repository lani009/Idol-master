package following_server;

import static following_server.jdbc.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
			if(conn != null){
				System.out.println("커넥팅");
			}else{
				System.out.println("다시해이새끼야");
			}
			pstmt = conn.prepareStatement("select username from user where username=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();	// RS -> ResultSet 객체
		
			rs.last();
			if (rs.getRow()!=0) {
				result =  true;
			} else {
				result =  false;
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
			JSONArray taste = new JSONArray();
			pstmt = conn.prepareStatement("SELECT * FROM user_taste Where username=?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			rs.first();
			if(rs.getRow() == 0) {
				// 예외처리
			}
			rs.beforeFirst();
			System.out.println("수정 됨");
			while (rs.next()){
				System.out.println(rs.getString("tagcontent"));
				JSONObject temp = new JSONObject();
				temp.put("username", rs.getString("username"));
				temp.put("tagcontent", rs.getString("tagcontent"));
				taste.add(temp);
			}
			obj.put("taste", taste);
			System.out.println(taste);

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
			pstmt = conn.prepareStatement("INSERT INTO user_tag (user_id, tag_id) VALUES ( (SELECT id from user WHERE username=?), (SELECT id from tag WHERE tagcontent=?) )");
			pstmt.setString(1, username);
			pstmt.setString(2, tag);
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
			JSONObject temp = new JSONObject();
			pstmt = conn.prepareStatement("SELECT * FROM place_review Where place_name=?");
			pstmt.setString(1, place);
			rs = pstmt.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
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

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO review (palce_id, writer_id, content) VALUES ((SELECT id from place WHERE place=?), (SELECT id FROM user WHERE username=?), ?)");
			pstmt.setString(1, place);
			pstmt.setString(2, id);
			pstmt.setString(3, review);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("리뷰등록 오류");
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
			close(conn);
		}
		return true;
	}

	/**
	 * 추천
	 * 
	 * @return 추천 장소
	 */
	public String recommend() {
		return null;
		




	}

	private static class Holder {
		public static final Rest_Dao INSTANCE = new Rest_Dao();
	}

	public static Rest_Dao getInstance() {
		return Holder.INSTANCE;
	}
}
