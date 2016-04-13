/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entities.Assign;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author JelZ
 */
public class AssignDB {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseMonitoringReport";
    private String user="sa";
    private String pass="1234";
    
    public boolean insertAssign(Assign a){
        try {
            String query = "INSERT INTO Assign values('"+a.getA_course()+"','"+a.getA_CL()+"','"+a.getA_CM()+"','"+a.getA_year()+"')";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            int row = stmt.executeUpdate(query);
            if(row > 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public ResultSet getAssignOfStaff(String u_id){
        try {
            String query = "select c.c_code,c.c_name,c.c_desc,f.f_name,a.a_year from Assign a \n" +
                            "inner join Course c on a.a_course=c.c_code\n" +
                            "inner join Falculty f on c.c_falculty=f.f_id\n" +
                            "where a.a_CL='"+u_id+"' or a.a_CM='"+u_id+"'";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
