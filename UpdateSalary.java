package com.nit.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateSalary {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection conn = null;
		String designation = null;
		Statement st = null;
		String query = null;
		int counter = 0;
		try {
			// getting designation from user

			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.print("Enter employee designation to update their salary :");
				designation = sc.nextLine();
				designation = "'" + designation.toUpperCase() + "'";
			}
			// preparing query
			query = "UPDATE EMP SET SAL=SAL+(.01*SAL) WHERE JOB =" + designation;
			// registering driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// creating Connection object
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			// creating Statement object
			if (conn != null) {
				st = conn.createStatement();
			}
			// send and execute sql query
			if (st != null) {
				counter = st.executeUpdate(query);
			}
			if (counter == 0)
				System.out.println("No Record updated");
			else
				System.out.println(counter + " record updated");
		} // try
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally
	}// main
}// class
