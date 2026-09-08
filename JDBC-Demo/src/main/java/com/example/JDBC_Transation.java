package com.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Transation {

	public static void main(String[] args) {
		try {

			// Connection Pool
			Connection connection = HikariCP.getDataSource().getConnection();
			System.out.println("Connection established");
			
			// ----------Disable autoCommitMode----------
			
			connection.setAutoCommit(false);

			// ----------Statement creation -----------------

			Statement statement = connection.createStatement();
			String sql1 = "SELECT * FROM example";
			// execution of statement
			if (statement.execute(sql1)) {
				System.out.println("Statement execute()");
			} else {
				System.out.println("error");
			}

			// ---------Create a savepoint--------
			
			connection.setSavepoint();
			
			// -----------PreparedStatement creation--------------

			String sql2 = "INSERT INTO example(id, name, doj) VALUES(?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql2);
			pstmt.setInt(1, 201);
			pstmt.setString(2, "PreparedUser");
			pstmt.setDate(3, Date.valueOf("2026-05-25")); // yyyy-MM-dd
			int rowsInserted = pstmt.executeUpdate();
			System.out.println("PreparedStatement executed, rows inserted: " + rowsInserted);
			connection.rollback();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
