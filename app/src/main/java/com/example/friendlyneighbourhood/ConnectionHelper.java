package com.example.friendlyneighbourhood;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionHelper {
    Connection conn;
    public Connection ConnectionBuild() {
        String url = "jdbc:mysql://sql12.freemysqlhosting.net/sql12665850?characterEncoding=utf8";
        String uname = "sql12665850";
        String pass = "faAjf2RXQ1";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, uname, pass);
            if (conn != null) {
                Log.e("TAG:", "Success");
            }

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return conn;
    }
    public boolean signin(String username, String password) {
        boolean flag = false;

        try {
            Connection loginConn = ConnectionBuild();
            PreparedStatement pstmt = loginConn.prepareStatement("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "' ");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return flag;
    }

    public void register(String fname, String lname, String uname, String email, String password, String city, String locality) {
        try {
            Connection regConn = ConnectionBuild();
            PreparedStatement pstmt = regConn.prepareStatement("INSERT INTO users (fname, lname, username, email, password, city, locality) VALUES (?,?,?,?,?,?,?)");
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, uname);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            pstmt.setString(6, city);
            pstmt.setString(7, locality);

            pstmt.executeUpdate();
            regConn.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }

    public String[][] getServices(String need, String city) {
        try {
            int count = 0;
            Connection serviceConn = ConnectionBuild();
            PreparedStatement pstmt1 = serviceConn.prepareStatement("SELECT COUNT(*) FROM services WHERE city='" + city + "' " +
                    "AND (service1='" + need + "' OR service2='" + need + "' OR service3='" + need + "' OR service4='" + need + "' OR service5='" + need + "')");
            ResultSet rs1 = pstmt1.executeQuery();
            while (rs1.next()) {
                count = rs1.getInt(1);
            }

            String[][] serv = new String[count][4];
            PreparedStatement pstmt2 = serviceConn.prepareStatement("SELECT name, phone, service1, service2, service3, service4, service5, locality FROM services WHERE city='" + city + "' " +
                    "AND (service1='" + need + "' OR service2='" + need + "' OR service3='" + need + "' OR service4='" + need + "' OR service5='" + need + "')");
            ResultSet rs2 = pstmt2.executeQuery();

            int i = 0;
            while(rs2.next()) {
                serv[i][0] = rs2.getString(1);
                serv[i][1] = rs2.getString(2);
                serv[i][2] = rs2.getString(3) + ", " + rs2.getString(4) + ", " + rs2.getString(5) + ", " + rs2.getString(6) + ", " + rs2.getString(7);
                serv[i][3] = rs2.getString(8);
                i++;
            }
            return serv;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return null;
    }
}
