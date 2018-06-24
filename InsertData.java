package com.nit.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * This application will insert data into student table
 * 
 * @author Prashant Suman
 *
 */
public class InsertData {
    public static void main(String[] args) {
        Scanner sc = null;
        Connection conn = null;
        Statement st = null;
        String sname = null;
        int roll = 0;
        String address = null;
        long pin;
        int marks = 0;
        String query = null;
        int result = 0;
        try {
            // getting data from user
            sc = new Scanner(System.in);
            if (sc != null) {
                System.out.print("Enter Student name : ");
                sname = sc.nextLine();
                sname = "'" + sname + "'";
                System.out.print("Enter roll n0 : ");
                roll = sc.nextInt();
                System.out.print("Enter address : ");
                sc.nextLine();
                address = sc.nextLine();
                address = "'" + address + "'";
                System.out.print("Enter pin : ");
                pin = sc.nextLong();
                System.out.print("Enter marks : ");
                marks = sc.nextInt();
                // preparing query
                query = "INSERT INTO STUDENT VALUES(" + sname + "," + roll + "," + address + "," + pin + "," + marks
                        + ")";
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
            System.out.println("Data not inserted");
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
