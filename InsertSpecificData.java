package com.nit.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * This application will insert some specific column value into employee table
 * 
 * @author Prashant Suman
 *
 */
public class InsertSpecificData {
    public static void main(String[] args) {
        Scanner sc = null;
        Connection conn = null;
        Statement st = null;
        int empno = 0, sal = 0;
        String ename = null, job = null;
        String query = null;
        int result = 0;
        try {
            // getting data from user
            sc = new Scanner(System.in);
            if (sc != null) {
                System.out.print("Enter Employee no : ");
                empno = sc.nextInt();
                System.out.print("Enter Employee name : ");
                sc.nextLine();
                ename = sc.nextLine();
                System.out.print("Enter job designation : ");
                job = sc.nextLine();
                System.out.print("Enter salary : ");
                sal = sc.nextInt();
                // preparing query
                query = "INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES(" + empno + ",'" + ename + "','"
                        + job.toUpperCase() + "'," + sal + ")";
                // loading Driver class
                Class.forName("oracle.jdbc.driver.OracleDriver");
                // create Connection object
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
                // creating Statement object
                if (conn != null)
                    st = conn.createStatement();
                // send and execute query
                System.out.println(query);
                result = st.executeUpdate(query);
                if (result == 0)
                    System.out.println("record not inserted");
                else
                    System.out.println("record inserted successfully");
            }
        } // try
        catch (SQLException se) {
            System.out.println("record not inserted");
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } // catch
        finally {
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