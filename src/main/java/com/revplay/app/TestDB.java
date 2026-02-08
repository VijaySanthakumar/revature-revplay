package com.revplay.app;

import java.sql.Connection;
import com.revplay.util.DBConnection;

public class TestDB {

    public static void main(String[] args) throws Exception {

        Connection con = DBConnection.getConnection();

        if (con != null) {
            System.out.println("Database Connected Successfully!");
        } else {
            System.out.println("Connection Failed");
        }
    }
}
