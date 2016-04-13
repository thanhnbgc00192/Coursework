/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entities.Users;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JelZ
 */
public class UsersDB {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseMonitoringReport";
    private String user="sa";
    private String pass="1234";
    
    public String getUserNameById(int code,String pos){
        try {
            String query = "select u.u_fullname from Users u \n" +
                            "inner join Assign a on u.u_id=a.a_CL or u.u_id=a.a_CM\n" +
                            "inner join Course c on c.c_code=a.a_course\n" +
                            "where c.c_code='"+code+"' and u.u_position='"+pos+"'";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return rs.getString("u_fullname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Users> getAllUser(){
        try {
            List<Users> list = new ArrayList<Users>();
            String query = "SELECT * FROM Users";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                String u_id = rs.getString("u_id");
                String u_fullname = rs.getString("u_fullname");
                Date u_birthday = Date.valueOf(rs.getString("u_birthday"));
                String u_address = rs.getString("u_address");
                String u_avartar = rs.getString("u_avartar");
                String username = rs.getString("u_username");
                String password = rs.getString("u_password");
                String u_email = rs.getString("u_email");
                String u_phone = rs.getString("u_phone");
                String u_position = rs.getString("u_position");
                
                Users u = new Users(u_id, u_fullname, u_birthday, u_address, u_avartar, u_email, u_phone, username, password, u_position);
                list.add(u);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Users> getListCL(){
         try {
            List<Users> list = new ArrayList<Users>();
            String query = "SELECT * FROM Users WHERE u_position='CL';";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                String u_id = rs.getString("u_id");
                String u_fullname = rs.getString("u_fullname");
                Date u_birthday = Date.valueOf(rs.getString("u_birthday"));
                String u_address = rs.getString("u_address");
                String u_avartar = rs.getString("u_avartar");
                String username = rs.getString("u_username");
                String password = rs.getString("u_password");
                String u_email = rs.getString("u_email");
                String u_phone = rs.getString("u_phone");
                String u_position = rs.getString("u_position");
                
                Users u = new Users(u_id, u_fullname, u_birthday, u_address, u_avartar, u_email, u_phone, username, password, u_position);
                list.add(u);              
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return null;
    }
    
    public List<Users> getListCM(){
        try {
            List<Users> list = new ArrayList<Users>();
            String query = "SELECT * FROM Users WHERE u_position='CM';";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                String u_id = rs.getString("u_id");
                String u_fullname = rs.getString("u_fullname");
                Date u_birthday = Date.valueOf(rs.getString("u_birthday"));
                String u_address = rs.getString("u_address");
                String u_avartar = rs.getString("u_avartar");
                String username = rs.getString("u_username");
                String password = rs.getString("u_password");
                String u_email = rs.getString("u_email");
                String u_phone = rs.getString("u_phone");
                String u_position = rs.getString("u_position");
                
                Users u = new Users(u_id, u_fullname, u_birthday, u_address, u_avartar, u_email, u_phone, username, password, u_position);
                list.add(u);              
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return null;
    }
    
    public Users getUser(String id){
        try {
            String query = "SELECT * FROM Users WHERE u_id='" + id + "';";
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
                String username = rs.getString("u_username");
                String password = rs.getString("u_password");
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
    
    public Users getUserByCode(String ccode){
        try {
            String query = "select * from Users u inner join Assign a on u.u_id=a.a_CL\n" +
"where a.a_course='"+ccode+"';";
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
                String username = rs.getString("u_username");
                String password = rs.getString("u_password");
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
    
    public int countStudent(int ccode){
        try {
            String query = "select COUNT(cd_student) as countStudent from ClassDetail where cd_course='"+ccode+"';";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                int count = rs.getInt("countStudent");
                return count;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Users> getUserByPosition(String pos){
        try {
            String query = "SELECT * FROM Users WHERE u_position='" + pos + "';";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            List<Users> list = new ArrayList<Users>();
            if(rs.next()){
                String u_id = rs.getString("u_id");
                String u_fullname = rs.getString("u_fullname");
                Date u_birthday = Date.valueOf(rs.getString("u_birthday"));
                String u_address = rs.getString("u_address");
                String u_avartar = rs.getString("u_avartar");
                String username = rs.getString("u_username");
                String password = rs.getString("u_password");
                String u_email = rs.getString("u_email");
                String u_phone = rs.getString("u_phone");
                String u_position = rs.getString("u_position");
                
                Users u = new Users(u_id, u_fullname, u_birthday, u_address, u_avartar, u_email, u_phone, username, password, u_position);
                list.add(u);
                
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
