package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchStudentDetail
{
	public static void main(String args[]) 
	{
			Connection conn=null;
			Scanner sc=null;
			Statement st=null;
			ResultSet rs=null;
			String initialName=null;
			boolean flag=false;
			try{
				//loading driver class
					Class.forName("oracle.jdbc.driver.OracleDriver");
				//creating connection
				conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
				//taking initial character from user
				System.out.print("Enter initial letter of name : ");
				sc=new Scanner(System.in);
				if(sc!=null)
				initialName=sc.next();
				initialName="'"+initialName+"%'";
				if(conn!=null){
				//creating statement object
				st=conn.createStatement();
				}
				if(st!=null){
				//sending and executing sql queries
				 rs=st.executeQuery("SELECT * FROM STUDENT  WHERE NAME LIKE "+initialName);
				}
				if(rs!=null){
				while(rs.next())
				{
					flag=true;
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
				}
				}
				if(flag==false)
					System.out.println("Sorry no record found");
				
			}//try
			catch(SQLException se){
				se.printStackTrace();//known Exception
			}
			catch(Exception e){
				e.printStackTrace();//Unknown Exception
			}
			finally
			{				
				try{
					rs.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
				try{
					conn.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			}//finally
	}//main method
}//class 
//javac -d . SearchStudentDetail.java
//java com.nit.jdbc.SearchStudentDetail