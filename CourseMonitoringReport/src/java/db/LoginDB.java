/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entities.Users;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author JelZ
 */
public class LoginDB {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseMonitoringReport";
    private String user="sa";
    private String pass="1234";
    
    public Users checkLogin(String username,String password){
        try {
            String query = "SELECT * FROM Users WHERE u_username='" + username.toLowerCase() + "' AND u_password='" + hashPass(password) + "';";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                String u_id = rs.getString("u_id");
                String u_fullname = rs.getString("u_fullname");
                Date u_birthday = Date.valueOf(rs.getString("u_birthday"));
                String u_address = rs.getString("u_address");
                String u_avartar = rs.getString("u_avartar");
                String u_email = rs.getString("u_email");
                String u_phone = rs.getString("u_phone");
                String u_position = rs.getString("u_position");
                
                Users u = new Users(u_id, u_fullname, u_birthday, u_address, u_avartar, u_email, u_phone, username, password, u_position);
                
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private String hashPass(String s) {
    try {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        BigInteger i = new BigInteger(1,m.digest());
        return String.format("%1$032x", i);         
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    return null;
}
}
