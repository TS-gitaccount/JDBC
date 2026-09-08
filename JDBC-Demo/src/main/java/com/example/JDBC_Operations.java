package com.example;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Operations {

	// database configuration information
	private static String url = "jdbc:postgresql://localhost:3000/jdbcdb";
	private static String user = "postgres";
	private static String password = "2004";

	public static void main(String[] args) {

		try {

			// ----------- Connection establishment------------

			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connection Established");

			// ----------Statement creation -----------------

			Statement statement = connection.createStatement();
			String sql1 = "SELECT * FROM example";
			// execution of statement
			if (statement.execute(sql1)) {
				System.out.println("Statement execute()");
			} else {
				System.out.println("error");
			}

			// -----------PreparedStatement creation--------------

			String sql2 = "INSERT INTO example(id, name, doj) VALUES(?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql2);
			pstmt.setInt(1, 201);
			pstmt.setString(2, "PreparedUser");
			pstmt.setDate(3, Date.valueOf("2026-05-25")); // yyyy-MM-dd
			int rowsInserted = pstmt.executeUpdate();
			System.out.println("PreparedStatement executed, rows inserted: " + rowsInserted);

			//------------------ CallableStatement ----------------
			
			CallableStatement cstmt = connection.prepareCall("SELECT * FROM example WHERE id = ?");
			cstmt.setInt(1, 201);
			ResultSet rsCall = cstmt.executeQuery();

			System.out.println("CallableStatement results:");
			while (rsCall.next()) {
				int id = rsCall.getInt("id");
				String name = rsCall.getString("name");
				Date doj = rsCall.getDate("doj");
				System.out.println("Row: id=" + id + ", name=" + name + ", doj=" + doj);
			}

			// ------------- DatabaseMetaData ----------------
			DatabaseMetaData dbmd = connection.getMetaData();
			System.out.println("Database Product: " + dbmd.getDatabaseProductName());
			System.out.println("Database Version: " + dbmd.getDatabaseProductVersion());
			System.out.println("Driver Name: " + dbmd.getDriverName());
			System.out.println("Driver Version: " + dbmd.getDriverVersion());

			// -------- ResultSetMetaData --------
			ResultSet rs2 = statement.executeQuery("SELECT * FROM example");
			ResultSetMetaData rsmd = rs2.getMetaData();
			int columnCount = rsmd.getColumnCount();
			System.out.println("ResultSet Metadata:");
			for (int i = 1; i <= columnCount; i++) {
				System.out
						.println("Column " + i + ": " + rsmd.getColumnName(i) + " (" + rsmd.getColumnTypeName(i) + ")");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
