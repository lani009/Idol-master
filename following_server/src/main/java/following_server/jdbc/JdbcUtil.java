package following_server.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import following_server.DeepLearningSocket;

public class JdbcUtil {

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		}
	}

	public static void close(DeepLearningSocket dls) {
		if (dls != null) {
			try {
				dls.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
			}
		}
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
			}
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException ex) {
				
			}
		}
	}

	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
			}
		}
	}

	/**
	 * ResultSet이 Empty인지 아닌지 체크
	 * @param rs ResultSet
	 * @return isEmpty
	 */
	public static boolean isEmpty(ResultSet rs) {
		try {
			rs.last();
			if (rs.getRow() == 0) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new RuntimeException();
	}
}
