/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entities.Course;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JelZ
 */
public class CourseDB {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseMonitoringReport";
    private String user="sa";
    private String pass="1234";

    public CourseDB() {
    }
    
    
    public Course getCourse(int id){
        try {
            String query = "SELECT * FROM Course WHERE c_code="+id;
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next() ){
                String name = rs.getString("c_name");
                String avatar = rs.getString("c_avatar");
                String desc = rs.getString("c_desc");
                int falculty = rs.getInt("c_falculty");
                Course c = new Course(id, name, avatar, desc, falculty); 
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean deleteCourse(int id){
        try {
            String query = "DELETE FROM Course WHERE c_code="+id;
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            int row = stmt.executeUpdate(query);
            if(row >0 ){
                return true;              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Course> getAllCourse(){
        try {
            String query = "SELECT * FROM Course";
            List<Course> list = new ArrayList<Course>();
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("c_code");
                String name = rs.getString("c_name");
                String avatar = rs.getString("c_avatar");
                String desc = rs.getString("c_desc");
                int falculty = rs.getInt("c_falculty");
                Course c = new Course(id, name, avatar, desc, falculty);
                list.add(c);
                
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean insertCourse(Course c){
        try {
            
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            CallableStatement stmt = con.prepareCall("{call insertCourse(?,?,?,?)}");
            stmt.setString("c_name", c.getC_name());
            stmt.setString("c_avatar", c.getC_avatar());
            stmt.setString("c_desc", c.getC_desc());
            stmt.setInt("c_falculty", c.getFalculty());
            
            int row = stmt.executeUpdate();
            if(row > 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean editCourse(Course c){
        try {
            
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            CallableStatement stmt = con.prepareCall("{call editCourse(?,?,?,?,?)}");
            stmt.setString("c_name", c.getC_name());
            stmt.setString("c_avatar", c.getC_avatar());
            stmt.setString("c_desc", c.getC_desc());
            stmt.setInt("c_falculty", c.getFalculty());
            stmt.setInt("c_code", c.getC_code());
            
            int row = stmt.executeUpdate();
            if(row > 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
