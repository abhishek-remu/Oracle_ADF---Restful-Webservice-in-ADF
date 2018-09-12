package com.abhishek.model.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

public class DBconnection {
    public DBconnection() {
        super();
    }
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++++++++   ESTABLISH DATABASE CONNECTION    +++++++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    public static Connection getConnect() {

        Connection conn = null;
        
        
        try {

            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/PowerBIDS");
            if (ds != null) {
                conn = ds.getConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

               return conn;
    }


    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++++++++   CLOSE DATABASE CONNECTION    +++++++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Conection is closed");
        }
    }
    public static  void main(String arg[]){
        System.out.println("Main Method");
    Connection connn=getConnect();
    System.out.println("connn "+connn);
    }
}
