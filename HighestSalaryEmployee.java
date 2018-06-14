package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class HighestSalaryEmployee
{
	public static void main(String[] args)
	{
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		boolean isData=false;
		
		try{
			//loading driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//create Connection
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			//create Statement Object
			if(conn!=null)	
			st=conn.createStatement();
			//creating ResultSet object
			if(st!=null)
				rs=st.executeQuery("SELECT * FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)");
			while(rs.next())
			{
				isData=true;
				System.out.println("\n"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			if(isData==false)
				System.out.println("\nSorry no data found");
		}//try
		catch(SQLException se){
			se.printStackTrace();//known Exception
		}
		catch(Exception e){
			e.printStackTrace(); //Unknown Exception
		}
		finally
		{
			try{
				rs.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			try{
				st.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			try{
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
//javac -d . HighestSalaryEmployee.java
//java com.nit.jdbc.HighestSalaryEmployee