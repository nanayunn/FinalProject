package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardLoginDB {
	private static CardLoginDB instance = new CardLoginDB();

	public static CardLoginDB getInstance() {
		return instance;
	}

	public CardLoginDB() {
	}

	// oracle ∞Ë¡§
//	String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String jdbcUrl = "jdbc:oracle:thin:@70.12.113.206:1521:xe";

	String userId = "db";
	String userPw = "db";

	Connection conn = null;
	Connection conn2 = null;

	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;

	String sql = "";
	String sql2 = "";
	String result = "";
	String result2 = "";

	public String cardloginnDB(String id) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			sql = "SELECT * FROM CARD WHERE USERID =?";
		//	sql2 = "SELECT COUNT(CARDNO) FROM CARD WHERE USERID =?";
		

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			//pstmt2.setString(1, id);
			
			rs = pstmt.executeQuery();
//age gender phone email agree ++ address,adcategorynum
			if (rs.next()) {
				String cardno = rs.getString("cardno");
				String cardname = rs.getString("cardname");
				String cardagency = rs.getString("cardagency");
				
				
			
				return "YY";
				
//				if (rs.getString("userid") != null && rs.getString("userpwd") == pwd) {
//					return "YY";
//
//				} else if(rs.getString("userid")== null){
//						return "nulllll";
//				}
			} else {
				return " NNNNNN ";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null)
					rs.close();
				
				
				if (pstmt != null)
					pstmt.close();
		
				if (conn != null)
				
					conn.close();
			} catch(SQLException e) {
				System.out.println(e+"error");
			}
			
				 
		}
		return "NN";
		

	}
}
