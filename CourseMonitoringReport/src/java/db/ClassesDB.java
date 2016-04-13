/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entities.Classes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author JelZ
 */
public class ClassesDB {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseMonitoringReport";
    private String user="sa";
    private String pass="1234";
    
    public int getClassIdByCode(String code){
        try {
            String query = "select cd_class from ClassDetail where cd_course='"+code+"'";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                int id = rs.getInt("cd_class");
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public String getAcademicSession(int id) {
        try {
            String query = "select cl.cl_academicSession from Class cl inner join ClassDetail cd on cl.cl_id=cd.cd_class\n" +
"inner join Course c on c.c_code=cd.cd_course\n" +
"where c.c_code='"+id+"'\n" +
"group by cl.cl_academicSession";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                String academicSession = rs.getString(1);
                return academicSession;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
