package com.nit.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/*This application will provide employee detail
 * according to user given rank.
 * Rank will be according to decreasing salary
 */
public class EmployeeBySalaryTest {

	public static void main(String[] args) {
		Scanner sc = null;
		int rank = 0;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String query = null;

		// getting rank from user
		sc = new Scanner(System.in);
		System.out.print("Enter rank of employee :");
		if (sc != null)
			rank = sc.nextInt();
		try {
			// loading Driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// creating connection object
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			// creating Statement object
			st = conn.createStatement();
			// preparing query
			query = "SELECT * FROM (SELECT ROWNUM R,EMPNO,ENAME,SAL FROM(SELECT * FROM EMP ORDER BY SAL DESC))WHERE R="
					+ rank;
			// creating ResultSet object
			rs = st.executeQuery(query);
			// processing result
			if (rs.next())
				System.out.println(rs.getInt("empno") + " " + rs.getString("ename") + " " + rs.getString("sal"));
			else
				System.out.println("Sorry no record found");
		} // try

		catch (ClassNotFoundException cnf) {// known Exception
			cnf.printStackTrace();
		} catch (Exception e) {// UnknownException
			e.printStackTrace();
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
			} catch (Exception e) {
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
