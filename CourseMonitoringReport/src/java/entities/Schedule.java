/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author JelZ
 */
public class Schedule {
    private int s_id;
    private int s_course;
    private String s_student;
    private float s_cw1;
    private float s_cw2;
    private float s_cw3;
    private float s_exam;
    private float s_overall;

    public Schedule(float s_cw1, float s_cw2, float s_cw3, float s_exam, float s_overall) {
        this.s_cw1 = s_cw1;
        this.s_cw2 = s_cw2;
        this.s_cw3 = s_cw3;
        this.s_exam = s_exam;
        this.s_overall = s_overall;
    }
    
    

    public Schedule(int s_id, int s_course, String s_student, float s_cw1, float s_cw2, float s_cw3, float s_exam, float s_overall) {
        this.s_id = s_id;
        this.s_course = s_course;
        this.s_student = s_student;
        this.s_cw1 = s_cw1;
        this.s_cw2 = s_cw2;
        this.s_cw3 = s_cw3;
        this.s_exam = s_exam;
        this.s_overall = s_overall;
    }

    public Schedule(int s_course, String s_student, float s_cw1, float s_cw2, float s_cw3, float s_exam, float s_overall) {
        this.s_course = s_course;
        this.s_student = s_student;
        this.s_cw1 = s_cw1;
        this.s_cw2 = s_cw2;
        this.s_cw3 = s_cw3;
        this.s_exam = s_exam;
        this.s_overall = s_overall;
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
