package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class SearchStudentByCity
{
	public static void main(String[] args)
	{
		Connection conn=null;
		Scanner sc=null;
		Statement st=null;
		String city1=null,city2=null,city3=null;
		String condition=null;
		ResultSet rs=null;
		boolean isData=false;
		
		try{
			//loading driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//creating Scanner class object
			sc=new Scanner(System.in);
			//create Connection
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			//create Statement Object
			if(conn!=null)	
			st=conn.createStatement();
			//taking city value from user
			
			if(sc!=null){
			System.out.print("Enter city1 :");
			city1=sc.next();
			System.out.print("Enter city1 :");
			city2=sc.next();
			System.out.print("Enter city1 :");
			city3=sc.next();
			}
			condition="('"+city1+"','"+city2+"','"+city3+"')";
			//creating ResultSet object
			if(st!=null)
				rs=st.executeQuery("SELECT * FROM STUDENT WHERE ADDRESS IN"+condition);
			while(rs.next())
			{
				isData=true;
				System.out.println("\n"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
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
			
			try{
				sc.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}//finally
		

	}//main
}//class
//javac -d . SearchStudentByCity.java
//java com.nit.jdbc.SearchStudentByCity