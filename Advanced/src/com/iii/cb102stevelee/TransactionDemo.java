package com.iii.cb102stevelee;

import java.sql.*;
// rerun program need to delete all inserted department 300 - 406, in MySql, in table delete- apply
public class TransactionDemo {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC";
			conn = DriverManager.getConnection(connUrl, "root", "steve1924");

			conn.setAutoCommit(false);

			String insStmt = "INSERT INTO department VALUES (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
			pstmt.setInt(1, 401);
			pstmt.setString(2, "Sales");
			pstmt.executeUpdate();
			pstmt.setInt(1, 402);
			pstmt.setString(2, "Service");
			pstmt.executeUpdate();
			pstmt.setInt(1, 403);
			pstmt.setString(2, "Production");
			pstmt.executeUpdate();
			conn.commit();

			pstmt.setInt(1, 404);
			pstmt.setString(2, "Sales1");
			pstmt.executeUpdate();
			pstmt.setInt(1, 405); //  -----------402 error test to check rool back, was 405
			pstmt.setString(2, "Service2");
			pstmt.executeUpdate();
			pstmt.setInt(1, 406);
			pstmt.setString(2, "Production2");
			pstmt.executeUpdate();
			conn.commit();

			conn.setAutoCommit(true);

			pstmt = conn.prepareStatement("SELECT * FROM department");
			ResultSet rs = pstmt.executeQuery();

			System.out.printf("%10s %20s %n", "deptno", "dname");
			while (rs.next()) {
				System.out.printf("%10s %20s %n", rs.getString("deptno"), rs.getString("dname"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
				System.err.println("Transaction is being rolled back");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class TransactionDemo
