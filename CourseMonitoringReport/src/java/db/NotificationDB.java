/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entities.Course;
import entities.Notification;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.internet.MailDateFormat;

/**
 *
 * @author JelZ
 */
public class NotificationDB {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseMonitoringReport";
    private String user="sa";
    private String pass="1234";
    
    public int countAllNotification(String cm){
        try {
            String query = "select COUNT(*) AS 'rowcount' from Notification where n_CM='"+cm+"'";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            
            ResultSet rs = stmt.getResultSet();
            rs.next();
            int count = rs.getInt("rowcount");
            
            return count;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Notification> getAllNotification(String cm){
        try {
            String query = "select * from Notification where n_CM='"+cm+"'";
            List<Notification> list = new ArrayList<Notification>();
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                int id = rs.getInt("n_id");
                int reportId = rs.getInt("n_report");
                String CL = rs.getString("n_CL");
                String CM = rs.getString("n_CM");
                String title = rs.getString("n_title");
                String message = rs.getString("n_message");
                String status = rs.getString("n_status");
                SimpleDateFormat sdf = new SimpleDateFormat("mm:hh dd-MM-yyyy");
                String date = sdf.format(rs.getDate("n_time"));
                
                Notification n = new Notification(id, reportId, CL, CM, title, message, status, sdf.parse(date));
                list.add(n);
            }
            return list;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
