package com.nit.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*This application will provide employee detail
 * according to user given rank.
 * Rank will be according to decreasing salary
 */
public class SumTopTenSalary {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String query = null;
		int sumSalary = 0;

		try {
			// loading Driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// creating connection object
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			// creating Statement object
			st = conn.createStatement();
			// preparing query
			query = "SELECT * FROM EMP ORDER BY SAL DESC";
			// creating ResultSet object
			rs = st.executeQuery(query);
			// processing result
			for (int i = 1; i <= 10; i++) {
				rs.next();
				sumSalary+=rs.getInt("sal");
			}
			System.out.println("Sum of salary of top ten employee :"+sumSalary);

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

		} // finally
	}// main
}// class
