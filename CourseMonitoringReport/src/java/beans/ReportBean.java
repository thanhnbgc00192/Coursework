/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.ClassesDB;
import db.CookieHelper;
import db.ReportDB;
import db.ScheduleDB;
import db.UsersDB;
import entities.Report;
import entities.Schedule;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import org.primefaces.context.RequestContext;

/**
 *
 * @author JelZ
 */
@ManagedBean
@SessionScoped
public class ReportBean implements Serializable {
    private Schedule scheduleMean;
    
    public ReportBean() {
        
    }
    
    public void init(){
        CookieHelper ch = new CookieHelper();
        ch.setCookie("ccode", getCourseCode(), 3600);
        
    }
    
    public void insertReport(){
        CookieHelper ch = new CookieHelper();
        Cookie ck = ch.getCookie("ccode");
        System.out.println(ck.getValue());
        ReportDB db = new ReportDB();
        ClassesDB dbC = new ClassesDB();
        UsersDB udb = new UsersDB();
        ScheduleBean sb = new ScheduleBean();
        ScheduleDB sdb = new ScheduleDB();
        r_class = dbC.getClassIdByCode(ck.getValue());
        r_course = Integer.parseInt(ck.getValue());
        r_CL = udb.getUserByCode(ck.getValue()).getU_id();
        r_studentCount = udb.countStudent(Integer.parseInt(ck.getValue()));
        r_meanCW1 = sdb.selectMean(ck.getValue()).getS_cw1();
        r_meanCW2 = sdb.selectMean(ck.getValue()).getS_cw2();
        r_meanCW3 = sdb.selectMean(ck.getValue()).getS_cw3();
        r_meanExam = sdb.selectMean(ck.getValue()).getS_exam();
        r_meanOverall = sdb.selectMean(ck.getValue()).getS_overall();
        r_medianCW1 = sb.selectMedianByCode(ck.getValue()).getS_cw1();
        r_medianCW2 = sb.selectMedianByCode(ck.getValue()).getS_cw2();
        r_medianCW3 = sb.selectMedianByCode(ck.getValue()).getS_cw3();
        r_medianExam = sb.selectMedianByCode(ck.getValue()).getS_exam();
        r_medianOverall = sb.selectMedianByCode(ck.getValue()).getS_overall();
        r_standardDeviationCW1 = sb.selectStandardDeviationByCode(ck.getValue()).getS_cw1();
        r_standardDeviationCW2 = sb.selectStandardDeviationByCode(ck.getValue()).getS_cw2();
        r_standardDeviationCW3 = sb.selectStandardDeviationByCode(ck.getValue()).getS_cw3();
        r_standardDeviationExam = sb.selectStandardDeviationByCode(ck.getValue()).getS_exam();
        r_standardDeviationOverall = sb.selectStandardDeviationByCode(ck.getValue()).getS_overall();
        r_cw1_0_39 = sb.selectGrade0_39().getS_cw1();
        r_cw2_0_39 = sb.selectGrade0_39().getS_cw2();
        r_cw3_0_39 = sb.selectGrade0_39().getS_cw3();
        r_exam_0_39 = sb.selectGrade0_39().getS_exam();
        r_overall_0_39 = sb.selectGrade0_39().getS_overall();
        r_cw1_40_69 = sb.selectGrade40_69().getS_cw1();
        r_cw2_40_69 = sb.selectGrade40_69().getS_cw2();
        r_cw3_40_69 = sb.selectGrade40_69().getS_cw3();
        r_exam_40_69 = sb.selectGrade40_69().getS_exam();
        r_overall_40_69 = sb.selectGrade40_69().getS_overall();
        r_cw1_70_100 = sb.selectGrade70_100().getS_cw1();
        r_cw2_70_100 = sb.selectGrade70_100().getS_cw2();
        r_cw3_70_100 = sb.selectGrade70_100().getS_cw3();
        r_exam_70_100 = sb.selectGrade70_100().getS_exam();
        r_overall_70_100 = sb.selectGrade70_100().getS_overall();
        r_status = "Pending";
        Report r = new Report(r_class, r_course, r_CL, r_studentCount, r_meanCW1, r_meanCW2, r_meanCW3, r_meanExam, r_meanOverall, r_medianCW1, r_medianCW2, r_medianCW3, r_medianExam, r_medianOverall, r_standardDeviationCW1, r_standardDeviationCW2, r_standardDeviationCW3, r_standardDeviationExam, r_standardDeviationOverall, r_cw1_0_39, r_cw1_40_69, r_cw1_70_100, r_cw2_0_39, r_cw2_40_69, r_cw2_70_100, r_cw3_0_39, r_cw3_40_69, r_cw3_70_100, r_exam_0_39, r_exam_40_69, r_exam_70_100, r_overall_0_39, r_overall_40_69, r_overall_70_100, r_generalComment, r_actionToBeTaken, r_status);
        boolean check = db.insertReport(r);
        if(check){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Create report successful");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("staff-manager-report.xhtml?faces-redirect=true&status=view");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fail!", "Create report fail!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public String getCourseCode(){
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String coursecode = params.get("coursecode");
        return coursecode;
    }

    public Schedule getScheduleMean() {
        ScheduleDB db = new ScheduleDB();
        scheduleMean= db.selectMean(getCourseCode());
        return scheduleMean;
    }

    public void setScheduleMean(Schedule scheduleMean) {
        this.scheduleMean = scheduleMean;
    }
    
    
    
    private int r_id;
    private int r_class;
    private int r_course;
    private String r_CL;
    private int r_studentCount;
    private float r_meanCW1;
    private float r_meanCW2;
    private float r_meanCW3;
    private float r_meanExam;
    private float r_meanOverall;
    private float r_medianCW1;
    private float r_medianCW2;
    private float r_medianCW3;
    private float r_medianExam;
    private float r_medianOverall;
    private float r_standardDeviationCW1;
    private float r_standardDeviationCW2;
    private float r_standardDeviationCW3;
    private float r_standardDeviationExam;
    private float r_standardDeviationOverall;
    private float r_cw1_0_39;
    private float r_cw1_40_69;
    private float r_cw1_70_100;
    private float r_cw2_0_39;
    private float r_cw2_40_69;
    private float r_cw2_70_100;
    private float r_cw3_0_39;
    private float r_cw3_40_69;
    private float r_cw3_70_100;
    private float r_exam_0_39;
    private float r_exam_40_69;
    private float r_exam_70_100;
    private float r_overall_0_39;
    private float r_overall_40_69;
    private float r_overall_70_100;
    private String r_generalComment;
    private String r_actionToBeTaken;
    private Date r_timeCreate;
    private String r_status;

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public int getR_class() {
        return r_class;
    }

    public void setR_class(int r_class) {
        this.r_class = r_class;
    }

    public int getR_course() {
        return r_course;
    }

    public void setR_course(int r_course) {
        this.r_course = r_course;
    }

    public String getR_CL() {
        return r_CL;
    }

    public void setR_CL(String r_CL) {
        this.r_CL = r_CL;
    }

    public int getR_studentCount() {
        return r_studentCount;
    }

    public void setR_studentCount(int r_studentCount) {
        this.r_studentCount = r_studentCount;
    }

    public float getR_meanCW1() {
        return r_meanCW1;
    }

    public void setR_meanCW1(float r_meanCW1) {
        this.r_meanCW1 = r_meanCW1;
    }

    public float getR_meanCW2() {
        return r_meanCW2;
    }

    public void setR_meanCW2(float r_meanCW2) {
        this.r_meanCW2 = r_meanCW2;
    }

    public float getR_meanCW3() {
        return r_meanCW3;
    }

    public void setR_meanCW3(float r_meanCW3) {
        this.r_meanCW3 = r_meanCW3;
    }

    public float getR_meanExam() {
        return r_meanExam;
    }

    public void setR_meanExam(float r_meanExam) {
        this.r_meanExam = r_meanExam;
    }

    public float getR_meanOverall() {
        return r_meanOverall;
    }

    public void setR_meanOverall(float r_meanOverall) {
        this.r_meanOverall = r_meanOverall;
    }

    public float getR_medianCW1() {
        return r_medianCW1;
    }

    public void setR_medianCW1(float r_medianCW1) {
        this.r_medianCW1 = r_medianCW1;
    }

    public float getR_medianCW2() {
        return r_medianCW2;
    }

    public void setR_medianCW2(float r_medianCW2) {
        this.r_medianCW2 = r_medianCW2;
    }

    public float getR_medianCW3() {
        return r_medianCW3;
    }

    public void setR_medianCW3(float r_medianCW3) {
        this.r_medianCW3 = r_medianCW3;
    }

    public float getR_medianExam() {
        return r_medianExam;
    }

    public void setR_medianExam(float r_medianExam) {
        this.r_medianExam = r_medianExam;
    }

    public float getR_medianOverall() {
        return r_medianOverall;
    }

    public void setR_medianOverall(float r_medianOverall) {
        this.r_medianOverall = r_medianOverall;
    }

    public float getR_standardDeviationCW1() {
        return r_standardDeviationCW1;
    }

    public void setR_standardDeviationCW1(float r_standardDeviationCW1) {
        this.r_standardDeviationCW1 = r_standardDeviationCW1;
    }

    public float getR_standardDeviationCW2() {
        return r_standardDeviationCW2;
    }

    public void setR_standardDeviationCW2(float r_standardDeviationCW2) {
        this.r_standardDeviationCW2 = r_standardDeviationCW2;
    }

    public float getR_standardDeviationCW3() {
        return r_standardDeviationCW3;
    }

    public void setR_standardDeviationCW3(float r_standardDeviationCW3) {
        this.r_standardDeviationCW3 = r_standardDeviationCW3;
    }

    public float getR_standardDeviationExam() {
        return r_standardDeviationExam;
    }

    public void setR_standardDeviationExam(float r_standardDeviationExam) {
        this.r_standardDeviationExam = r_standardDeviationExam;
    }

    public float getR_standardDeviationOverall() {
        return r_standardDeviationOverall;
    }

    public void setR_standardDeviationOverall(float r_standardDeviationOverall) {
        this.r_standardDeviationOverall = r_standardDeviationOverall;
    }

    public float getR_cw1_0_39() {
        return r_cw1_0_39;
    }

    public void setR_cw1_0_39(float r_cw1_0_39) {
        this.r_cw1_0_39 = r_cw1_0_39;
    }

    public float getR_cw1_40_69() {
        return r_cw1_40_69;
    }

    public void setR_cw1_40_69(float r_cw1_40_69) {
        this.r_cw1_40_69 = r_cw1_40_69;
    }

    public float getR_cw1_70_100() {
        return r_cw1_70_100;
    }

    public void setR_cw1_70_100(float r_cw1_70_100) {
        this.r_cw1_70_100 = r_cw1_70_100;
    }

    public float getR_cw2_0_39() {
        return r_cw2_0_39;
    }

    public void setR_cw2_0_39(float r_cw2_0_39) {
        this.r_cw2_0_39 = r_cw2_0_39;
    }

    public float getR_cw2_40_69() {
        return r_cw2_40_69;
    }

    public void setR_cw2_40_69(float r_cw2_40_69) {
        this.r_cw2_40_69 = r_cw2_40_69;
    }

    public float getR_cw2_70_100() {
        return r_cw2_70_100;
    }

    public void setR_cw2_70_100(float r_cw2_70_100) {
        this.r_cw2_70_100 = r_cw2_70_100;
    }

    public float getR_cw3_0_39() {
        return r_cw3_0_39;
    }

    public void setR_cw3_0_39(float r_cw3_0_39) {
        this.r_cw3_0_39 = r_cw3_0_39;
    }

    public float getR_cw3_40_69() {
        return r_cw3_40_69;
    }

    public void setR_cw3_40_69(float r_cw3_40_69) {
        this.r_cw3_40_69 = r_cw3_40_69;
    }

    public float getR_cw3_70_100() {
        return r_cw3_70_100;
    }

    public void setR_cw3_70_100(float r_cw3_70_100) {
        this.r_cw3_70_100 = r_cw3_70_100;
    }

    public float getR_exam_0_39() {
        return r_exam_0_39;
    }

    public void setR_exam_0_39(float r_exam_0_39) {
        this.r_exam_0_39 = r_exam_0_39;
    }

    public float getR_exam_40_69() {
        return r_exam_40_69;
    }

    public void setR_exam_40_69(float r_exam_40_69) {
        this.r_exam_40_69 = r_exam_40_69;
    }

    public float getR_exam_70_100() {
        return r_exam_70_100;
    }

    public void setR_exam_70_100(float r_exam_70_100) {
        this.r_exam_70_100 = r_exam_70_100;
    }

    public float getR_overall_0_39() {
        return r_overall_0_39;
    }

    public void setR_overall_0_39(float r_overall_0_39) {
        this.r_overall_0_39 = r_overall_0_39;
    }

    public float getR_overall_40_69() {
        return r_overall_40_69;
    }

    public void setR_overall_40_69(float r_overall_40_69) {
        this.r_overall_40_69 = r_overall_40_69;
    }

    public float getR_overall_70_100() {
        return r_overall_70_100;
    }

    public void setR_overall_70_100(float r_overall_70_100) {
        this.r_overall_70_100 = r_overall_70_100;
    }

    public String getR_generalComment() {
        return r_generalComment;
    }

    public void setR_generalComment(String r_generalComment) {
        this.r_generalComment = r_generalComment;
    }

    public String getR_actionToBeTaken() {
        return r_actionToBeTaken;
    }

    public void setR_actionToBeTaken(String r_actionToBeTaken) {
        this.r_actionToBeTaken = r_actionToBeTaken;
    }

    public Date getR_timeCreate() {
        return r_timeCreate;
    }

    public void setR_timeCreate(Date r_timeCreate) {
        this.r_timeCreate = r_timeCreate;
    }

    public String getR_status() {
        return r_status;
    }

    public void setR_status(String r_status) {
        this.r_status = r_status;
    }
    
    
}
