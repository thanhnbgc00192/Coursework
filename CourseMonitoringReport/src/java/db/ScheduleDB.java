/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entities.Schedule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JelZ
 */
public class ScheduleDB {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseMonitoringReport";
    private String user="sa";
    private String pass="1234";
    
    public Schedule selectMean(String ccode){
        try {
            String query = "select AVG(s_cw1) as meanCW1,AVG(s_cw2) as meanCW2,AVG(s_cw3) as meanCW3,AVG(s_exam) as meanExam,AVG(s_overall) as meanOverall from Schedule where s_course='"+ccode+"';";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                float meanCW1 = rs.getFloat("meanCW1");
                DecimalFormat df = new DecimalFormat("#.##"); 
                String formatted = df.format(meanCW1); 
                float meanCW2 = rs.getFloat("meanCW2");
                DecimalFormat df2 = new DecimalFormat("#.##"); 
                String formatted2 = df.format(meanCW2); 
                float meanCW3 = rs.getFloat("meanCW3");
                DecimalFormat df3 = new DecimalFormat("#.##"); 
                String formatted3 = df.format(meanCW3); 
                float meanCW4 = rs.getFloat("meanExam");
                DecimalFormat df4 = new DecimalFormat("#.##"); 
                String formatted4 = df.format(meanCW4); 
                float meanCW5 = rs.getFloat("meanOverall");
                DecimalFormat df5 = new DecimalFormat("#.##"); 
                String formatted5 = df.format(meanCW5); 
                
                Schedule s = new Schedule(Float.parseFloat(formatted), Float.parseFloat(formatted2), Float.parseFloat(formatted3), Float.parseFloat(formatted4), Float.parseFloat(formatted5));
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Schedule getGrade0_39(){
        try {
            String query = "select (COUNT(s_cw1)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_cw1 < 40;";
            String query2 = "select (COUNT(s_cw2)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_cw2 < 40;";
            String query3 = "select (COUNT(s_cw3)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_cw3 < 40;";
            String query4 = "select (COUNT(s_exam)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_exam < 40;";
            String query5 = "select (COUNT(s_overall)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_overall < 40";
            float grade1 = selectGrade(query);
            float grade2 = selectGrade(query2);
            float grade3 = selectGrade(query3);
            float grade4 = selectGrade(query4);
            float grade5 = selectGrade(query5);
            Schedule s = new Schedule(grade1, grade2, grade3, grade4, grade5);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Schedule getGrade40_69(){
        try {
            String query = "select (COUNT(s_cw1)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_cw1 > 39 and s_cw1 < 70;";
            String query2 = "select (COUNT(s_cw2)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_cw2 > 39 and s_cw2 < 70;";
            String query3 = "select (COUNT(s_cw3)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_cw3 > 39 and s_cw3 < 70;";
            String query4 = "select (COUNT(s_exam)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_exam > 39 and s_exam < 70;";
            String query5 = "select (COUNT(s_overall)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_overall > 39 and s_overall < 70";
            float grade1 = selectGrade(query);
            float grade2 = selectGrade(query2);
            float grade3 = selectGrade(query3);
            float grade4 = selectGrade(query4);
            float grade5 = selectGrade(query5);
            Schedule s = new Schedule(grade1, grade2, grade3, grade4, grade5);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Schedule getGrade70_100(){
        try {
            String query = "select (COUNT(s_cw1)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_cw1 > 70;";
            String query2 = "select (COUNT(s_cw2)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_cw2 > 70;";
            String query3 = "select (COUNT(s_cw3)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_cw3 > 70;";
            String query4 = "select (COUNT(s_exam)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_exam > 70;";
            String query5 = "select (COUNT(s_overall)*100/ (select COUNT(*) from Schedule)) as grade\n" +
"from Schedule where s_overall > 70";
            float grade1 = selectGrade(query);
            float grade2 = selectGrade(query2);
            float grade3 = selectGrade(query3);
            float grade4 = selectGrade(query4);
            float grade5 = selectGrade(query5);
            Schedule s = new Schedule(grade1, grade2, grade3, grade4, grade5);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public float selectGrade(String query){
        try {
            
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                float meanCW1 = rs.getFloat("grade");
                DecimalFormat df = new DecimalFormat("#.##"); 
                String formatted = df.format(meanCW1);
                
                return meanCW1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
     public List<Schedule> selectAllScheduleByCode(String ccode){
        try {
            String query = "select * from Schedule where s_course='"+ccode+"';";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            List<Schedule> list = new ArrayList<Schedule>();
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                int s_id = rs.getInt("s_id");
                int s_course = rs.getInt("s_course");
                String s_student = rs.getString("s_student");
                float s_cw1 = rs.getFloat("s_cw1");
                float s_cw2 = rs.getFloat("s_cw2");
                float s_cw3 = rs.getFloat("s_cw3");
                float s_exam = rs.getFloat("s_exam");
                float s_overall = rs.getFloat("s_overall");
                Schedule s = new Schedule(s_id, s_course, s_student, s_cw1, s_cw2, s_cw3, s_exam, s_overall);
                list.add(s);
                
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
