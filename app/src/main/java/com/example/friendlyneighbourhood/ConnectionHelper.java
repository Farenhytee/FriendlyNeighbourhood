package com.example.friendlyneighbourhood;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public void register(String fname, String lname, String uname, String email, String password, String city, String locality, String phone) {
        try {
            Connection regConn = ConnectionBuild();
            PreparedStatement pstmt = regConn.prepareStatement("INSERT INTO users (fname, lname, username, email, password, city, locality, phone) VALUES (?,?,?,?,?,?,?,?)");
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, uname);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            pstmt.setString(6, city);
            pstmt.setString(7, locality);
            pstmt.setString(8, phone);

            pstmt.executeUpdate();
            regConn.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }

    public List<Item> getServices(String need, String city) {
        try {
            List<Item> serv = new ArrayList<>();
            Connection serviceConn = ConnectionBuild();

            PreparedStatement pstmt2 = serviceConn.prepareStatement("SELECT name, phone, service1, service2, service3, service4, service5, locality FROM services WHERE city='" + city + "' " +
                    "AND (service1='" + need + "' OR service2='" + need + "' OR service3='" + need + "' OR service4='" + need + "' OR service5='" + need + "')");
            ResultSet rs2 = pstmt2.executeQuery();

            while(rs2.next()) {
                String name = rs2.getString(1);
                String phone = rs2.getString(2);
                String servOff = rs2.getString(3) + ", " + rs2.getString(4) + ", " + rs2.getString(5) + ", " + rs2.getString(6) + ", " + rs2.getString(7);
                String locality = rs2.getString(8);

                Item item = new Item(name, phone, servOff, locality);
                serv.add(item);
            }
            return serv;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return null;
    }

    public void postJob(String uname, String jobtype, String jobdesc) {
        try {
            String phone = "", loc = "", city = "";
            Connection postConn = ConnectionBuild();
            PreparedStatement pstmt1 = postConn.prepareStatement("SELECT phone, locality, city FROM users WHERE username='" + uname + "'");
            ResultSet rs1 = pstmt1.executeQuery();
            while (rs1.next()) {
                phone = rs1.getString(1);
                loc = rs1.getString(2);
                city = rs1.getString(3);
            }

            PreparedStatement pstmt2 = postConn.prepareStatement("INSERT INTO open_jobs (n_user, phone, locality, city, job_type, job_info) VALUES (?,?,?,?,?,?)");
            pstmt2.setString(1, uname);
            pstmt2.setString(2, phone);
            pstmt2.setString(3, loc);
            pstmt2.setString(4, city);
            pstmt2.setString(5, jobtype);
            pstmt2.setString(6, jobdesc);
            pstmt2.executeUpdate();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }
}
