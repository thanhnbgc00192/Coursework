/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.ScheduleDB;
import entities.Schedule;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author JelZ
 */
@ManagedBean
@SessionScoped
public class ScheduleBean implements Serializable {
    private int s_id;
    private int s_course;
    private String s_student;
    private float s_cw1;
    private float s_cw2;
    private float s_cw3;
    private float s_exam;
    private float s_overall;
    
    public ScheduleBean() {
    }
    
    
    public Schedule selectMedian(){
        ScheduleDB db = new ScheduleDB();
        List<Schedule> list = new ArrayList<Schedule>();
        list = db.selectAllScheduleByCode(getCourseCode());
        float mediancw1[] = new float[list.size()];
        float mediancw2[] = new float[list.size()];
        float mediancw3[] = new float[list.size()];
        float medianexam[] = new float[list.size()];
        float medianoverall[] = new float[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            mediancw1[i] = list.get(i).getS_cw1();
            mediancw2[i] = list.get(i).getS_cw2();
            mediancw3[i] = list.get(i).getS_cw3();
            medianexam[i] = list.get(i).getS_exam();
            medianoverall[i] = list.get(i).getS_overall();
        }
        
        float medianCW1 = calculateMedian(mediancw1);
        float medianCW2 = calculateMedian(mediancw2);
        float medianCW3 = calculateMedian(mediancw3);
        float medianCW4 = calculateMedian(medianexam);
        float medianCW5 = calculateMedian(medianoverall);
        
        Schedule s = new Schedule(medianCW1, medianCW2, medianCW3, medianCW4, medianCW5);
        
        return s;
    }
    public Schedule selectMedianByCode(String code){
        ScheduleDB db = new ScheduleDB();
        List<Schedule> list = new ArrayList<Schedule>();
        list = db.selectAllScheduleByCode(code);
        float mediancw1[] = new float[list.size()];
        float mediancw2[] = new float[list.size()];
        float mediancw3[] = new float[list.size()];
        float medianexam[] = new float[list.size()];
        float medianoverall[] = new float[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            mediancw1[i] = list.get(i).getS_cw1();
            mediancw2[i] = list.get(i).getS_cw2();
            mediancw3[i] = list.get(i).getS_cw3();
            medianexam[i] = list.get(i).getS_exam();
            medianoverall[i] = list.get(i).getS_overall();
        }
        
        float medianCW1 = calculateMedian(mediancw1);
        float medianCW2 = calculateMedian(mediancw2);
        float medianCW3 = calculateMedian(mediancw3);
        float medianCW4 = calculateMedian(medianexam);
        float medianCW5 = calculateMedian(medianoverall);
        
        Schedule s = new Schedule(medianCW1, medianCW2, medianCW3, medianCW4, medianCW5);
        
        return s;
    }
    public Schedule selectGrade0_39(){
        ScheduleDB db = new ScheduleDB();
        Schedule s = db.getGrade0_39();
        return s;
    }
    public Schedule selectGrade40_69(){
        ScheduleDB db = new ScheduleDB();
        Schedule s = db.getGrade40_69();
        return s;
    }
    public Schedule selectGrade70_100(){
        ScheduleDB db = new ScheduleDB();
        Schedule s = db.getGrade70_100();
        return s;
    }
    public Schedule selectStandardDeviation(){
        ScheduleDB db = new ScheduleDB();
        ReportBean rb = new ReportBean();
        Schedule s = rb.getScheduleMean();
        List<Schedule> list = new ArrayList<Schedule>();
        list = db.selectAllScheduleByCode(getCourseCode());
        float mediancw1[] = new float[list.size()];
        float mediancw2[] = new float[list.size()];
        float mediancw3[] = new float[list.size()];
        float medianexam[] = new float[list.size()];
        float medianoverall[] = new float[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            mediancw1[i] = list.get(i).getS_cw1();
            mediancw2[i] = list.get(i).getS_cw2();
            mediancw3[i] = list.get(i).getS_cw3();
            medianexam[i] = list.get(i).getS_exam();
            medianoverall[i] = list.get(i).getS_overall();
        }
        
        float stdev1 = calculateDeviation(mediancw1);
        float stdev2 = calculateDeviation(mediancw2);
        float stdev3 = calculateDeviation(mediancw3);;
        float stdev4 = calculateDeviation(medianexam);;
        float stdev5 = calculateDeviation(medianoverall);;
        
        Schedule sc = new Schedule(stdev1, stdev2, stdev3, stdev4, stdev5);
        
        return sc;
    }
    public Schedule selectStandardDeviationByCode(String code){
        ScheduleDB db = new ScheduleDB();
        ReportBean rb = new ReportBean();
        Schedule s = rb.getScheduleMean();
        List<Schedule> list = new ArrayList<Schedule>();
        list = db.selectAllScheduleByCode(code);
        float mediancw1[] = new float[list.size()];
        float mediancw2[] = new float[list.size()];
        float mediancw3[] = new float[list.size()];
        float medianexam[] = new float[list.size()];
        float medianoverall[] = new float[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            mediancw1[i] = list.get(i).getS_cw1();
            mediancw2[i] = list.get(i).getS_cw2();
            mediancw3[i] = list.get(i).getS_cw3();
            medianexam[i] = list.get(i).getS_exam();
            medianoverall[i] = list.get(i).getS_overall();
        }
        
        float stdev1 = calculateDeviation(mediancw1);
        float stdev2 = calculateDeviation(mediancw2);
        float stdev3 = calculateDeviation(mediancw3);;
        float stdev4 = calculateDeviation(medianexam);;
        float stdev5 = calculateDeviation(medianoverall);;
        
        Schedule sc = new Schedule(stdev1, stdev2, stdev3, stdev4, stdev5);
        
        return sc;
    }
    private float calculateDeviation(float number[]){
        float stdev1 = 0;
        double powerSum1 = 0;
        double powerSum2 = 0;
        for (int i = 0; i < number.length; i++) {
            powerSum1 += number[i];
            powerSum2 += Math.pow(number[i], 2);
            stdev1 = (float) (Math.sqrt(i*powerSum2 - Math.pow(powerSum1, 2))/i);
        }
        return stdev1;
    }
    private float calculateMedian(float number[]){
        float pos11 = (float) Math.floor((number.length-1.0)/2.0);
        float pos12 = (float) Math.ceil((number.length-1.0)/2.0);
        float median = 0;
        if(pos11==pos12){
            median=number[(int)pos11];
        }
        else{
            median=(number[(int)pos11]+number[(int)pos12])/2;           
        }
        return median;
    }
    
    public String getCourseCode(){
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String coursecode = params.get("coursecode");
        return coursecode;
    }
    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getS_course() {
        return s_course;
    }

    public void setS_course(int s_course) {
        this.s_course = s_course;
    }

    public String getS_student() {
        return s_student;
    }

    public void setS_student(String s_student) {
        this.s_student = s_student;
    }

    public float getS_cw1() {
        return s_cw1;
    }

    public void setS_cw1(float s_cw1) {
        this.s_cw1 = s_cw1;
    }

    public float getS_cw2() {
        return s_cw2;
    }

    public void setS_cw2(float s_cw2) {
        this.s_cw2 = s_cw2;
    }

    public float getS_cw3() {
        return s_cw3;
    }

    public void setS_cw3(float s_cw3) {
        this.s_cw3 = s_cw3;
    }

    public float getS_exam() {
        return s_exam;
    }

    public void setS_exam(float s_exam) {
        this.s_exam = s_exam;
    }

    public float getS_overall() {
        return s_overall;
    }

    public void setS_overall(float s_overall) {
        this.s_overall = s_overall;
    }
    
    
}
