/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entities.Course;
import entities.Report;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author JelZ
 */
public class ReportDB {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseMonitoringReport";
    private String user="sa";
    private String pass="1234";
    
    public boolean insertReport(Report r){
        try {
            
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            CallableStatement stmt = con.prepareCall("{call insertReport(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            stmt.setInt("r_class", r.getR_class());
            stmt.setInt("r_course", r.getR_course());
            stmt.setString("r_CL", r.getR_CL());
            stmt.setInt("r_studentCount", r.getR_studentCount());
            stmt.setFloat("r_meanCW1", r.getR_meanCW1());
            stmt.setFloat("r_meanCW2", r.getR_meanCW2());
            stmt.setFloat("r_meanCW3", r.getR_meanCW3());
            stmt.setFloat("r_meanExam", r.getR_meanExam());
            stmt.setFloat("r_meanOverall", r.getR_meanOverall());
            stmt.setFloat("r_medianCW1", r.getR_medianCW1());
            stmt.setFloat("r_medianCW2", r.getR_medianCW2());
            stmt.setFloat("r_medianCW3", r.getR_medianCW3());
            stmt.setFloat("r_medianExam", r.getR_medianExam());
            stmt.setFloat("r_medianOverall", r.getR_medianOverall());
            stmt.setFloat("r_standardDeviationCW1", r.getR_standardDeviationCW1());
            stmt.setFloat("r_standardDeviationCW2", r.getR_standardDeviationCW2());
            stmt.setFloat("r_standardDeviationCW3", r.getR_standardDeviationCW3());
            stmt.setFloat("r_standardDeviationExam", r.getR_standardDeviationExam());
            stmt.setFloat("r_standardDeviationOverall", r.getR_standardDeviationOverall());
            stmt.setFloat("r_cw1_0_39", r.getR_cw1_0_39());
            stmt.setFloat("r_cw1_40_69", r.getR_cw1_40_69());
            stmt.setFloat("r_cw1_70_100", r.getR_cw1_70_100());
            stmt.setFloat("r_cw2_0_39", r.getR_cw2_0_39());
            stmt.setFloat("r_cw2_40_69", r.getR_cw2_40_69());
            stmt.setFloat("r_cw2_70_100", r.getR_cw2_70_100());
            stmt.setFloat("r_cw3_0_39", r.getR_cw3_0_39());
            stmt.setFloat("r_cw3_40_69", r.getR_cw3_40_69());
            stmt.setFloat("r_cw3_70_100", r.getR_cw3_70_100());
            stmt.setFloat("r_exam_0_39", r.getR_exam_0_39());
            stmt.setFloat("r_exam_40_69", r.getR_exam_40_69());
            stmt.setFloat("r_exam_70_100", r.getR_exam_70_100());
            stmt.setFloat("r_overall_0_39", r.getR_overall_0_39());
            stmt.setFloat("r_overall_40_69", r.getR_overall_40_69());
            stmt.setFloat("r_overall_70_100", r.getR_overall_70_100());
            stmt.setString("r_generalComment", r.getR_generalComment());
            stmt.setString("r_actionToBeTaken", r.getR_actionToBeTaken());
            stmt.setString("r_status", r.getR_status());
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
