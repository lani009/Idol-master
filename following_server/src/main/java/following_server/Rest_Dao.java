package following_server;

import static following_server.jdbc.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;

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
	@SuppressWarnings("finally")
	public boolean isLogin(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("SELECT username FROM user Where username=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println(rs);
		} catch (Exception e) {
			System.out.println("로그인오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
			if (rs == null) {
				return true;
			} else {
				return false;
			}
		}

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
			JSONObject temp = new JSONObject();
			pstmt = conn.prepareStatement("SELECT * FROM user_taste Where username=?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				temp.put("username", rs.getString("username"));
				temp.put("tagcontent", rs.getString("tagcontent"));
				taste.add(temp);
			}
			obj.put("taste", taste);

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
			pstmt = conn.prepareStatement("insert into user_taste(?,?)");
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
			pstmt = conn.prepareStatement("SELECT * FROM place_view Where place_name=?");
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
	public boolean setReview(String review, String place, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("Insert into place_review(?,?,?)");
			pstmt.setString(1, place);
			pstmt.setString(2, review);
			pstmt.setString(3, id);
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
