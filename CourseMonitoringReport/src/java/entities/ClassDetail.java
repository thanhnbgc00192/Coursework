/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author JelZ
 */
public class ClassDetail {
    private int cd_id;
    private int cd_class;
    private String cd_student;
    private String cd_teacher;
    private int cd_course;

    public ClassDetail(int cd_id, int cd_class, String cd_student, String cd_teacher, int cd_course) {
        this.cd_id = cd_id;
        this.cd_class = cd_class;
        this.cd_student = cd_student;
        this.cd_teacher = cd_teacher;
        this.cd_course = cd_course;
    }

    public ClassDetail(int cd_class, String cd_student, String cd_teacher, int cd_course) {
        this.cd_class = cd_class;
        this.cd_student = cd_student;
        this.cd_teacher = cd_teacher;
        this.cd_course = cd_course;
    }

    public int getCd_id() {
        return cd_id;
    }

    public void setCd_id(int cd_id) {
        this.cd_id = cd_id;
    }

    public int getCd_class() {
        return cd_class;
    }

    public void setCd_class(int cd_class) {
        this.cd_class = cd_class;
    }

    public String getCd_student() {
        return cd_student;
    }

    public void setCd_student(String cd_student) {
        this.cd_student = cd_student;
    }

    public String getCd_teacher() {
        return cd_teacher;
    }

    public void setCd_teacher(String cd_teacher) {
        this.cd_teacher = cd_teacher;
    }

    public int getCd_course() {
        return cd_course;
    }

    public void setCd_course(int cd_course) {
        this.cd_course = cd_course;
    }
    
    
}
