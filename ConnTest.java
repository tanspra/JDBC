import java.sql.*;
public class ConnTest
{
public static void main(String args[]) throws Exception
{
//creating object of driver class
oracle.jdbc.driver.OracleDriver driver=new oracle.jdbc.driver.OracleDriver();
//registering driver with DriverManager Service
DriverManager.registerDriver(driver);
//creating connection
Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
if(conn==null)
{
System.out.println("connection not established");
}
else
{
System.out.println("connection established");
}
//creating statement object
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery("SELECT * FROM EMP WHERE JOB='"+"CLERK"+"'");
while(rs.next()!=false)
{
	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
}
conn.close();
rs.close();
}//main method
}//class 