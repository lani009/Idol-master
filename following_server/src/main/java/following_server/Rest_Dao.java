package following_server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import following_server.jdbc.JdbcUtil;
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
			if (rs == null) {
				return true;
			} else {
				return false;
			}
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	
	}

	/**
	 * 취향 불러오기
	 * 
	 * @return 취향 태그
	 */
	public String getTaste() {

		return null;
	}

	/**
	 * 취향 저장
	 * 
	 * @param tag
	 */
	public void setTaste(String tag) {

	}

	public String getPlace() {
		return null;
	}

	/**
	 * 리뷰를 가져와서 리스트로 띄우는 용도
	 * 
	 * @return 리뷰
	 */
	@SuppressWarnings("finally")
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
			return obj.toString();
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}

	}

	/**
	 * 리뷰작성
	 * 
	 * @param review 리뷰
	 */
	public void setReview(String review) {

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
