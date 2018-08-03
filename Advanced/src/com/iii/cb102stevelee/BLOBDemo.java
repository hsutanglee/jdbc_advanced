package com.iii.cb102stevelee;

import java.sql.*;
import java.io.*;

public class BLOBDemo {
	public static void main(String[] args) {
		Connection conn = null;
		String inFile = args[0];
		String outFile = args[1];
		try {     
			String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC";
			conn = DriverManager.getConnection(connUrl, "root", "steve1924");
			
			String qryStmt = "SELECT photo FROM blobtest WHERE name = ?";
			PreparedStatement pstmt = conn.prepareStatement(qryStmt);
			pstmt.setString(1, inFile);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String deleteStmt = "DELETE FROM blobtest WHERE name = ?"; 
				pstmt = conn.prepareStatement(deleteStmt);
				pstmt.setString(1, inFile);
				pstmt.executeUpdate();
				System.out.println("Delete blob is successful!");
			}
			
			File f = new File(inFile);
			FileInputStream fis = new FileInputStream(f);
			String insertStmt = "INSERT INTO blobtest VALUES(?,?)";		
			pstmt = conn.prepareStatement(insertStmt);
			pstmt.setString(1, inFile);
			pstmt.setBinaryStream(2, fis, f.length());
			pstmt.executeUpdate();
			System.out.println("Insert blob is successful!");
	
			pstmt = conn.prepareStatement(qryStmt);
			pstmt.setString(1, inFile);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				FileOutputStream fos = new FileOutputStream(outFile);
				Blob b = rs.getBlob("photo");
				byte[] data = b.getBytes(1, (int)b.length());
				fos.write(data, 0, (int)b.length());
				fos.close();
				System.out.println("File output is successful!");
			} // end of if (rs.next()) 
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class BLOBDemo 
