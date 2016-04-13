/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entities.Position;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author JelZ
 */
public class PositionDB {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseMonitoringReport";
    private String user="sa";
    private String pass="1234";
    
    public String parsePosition(String id){
        try {
            String query = "SELECT * FROM Position WHERE p_acronym='" + id + "';";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                String position = rs.getString("p_position");
                return position;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
