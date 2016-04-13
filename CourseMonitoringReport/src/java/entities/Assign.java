/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author JelZ
 */
public class Assign {
    private int a_id;
    private int a_course;
    private String a_CL;
    private String a_CM;
    private int a_year;

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public int getA_course() {
        return a_course;
    }

    public void setA_course(int a_course) {
        this.a_course = a_course;
    }

    public String getA_CL() {
        return a_CL;
    }

    public void setA_CL(String a_CL) {
        this.a_CL = a_CL;
    }

    public String getA_CM() {
        return a_CM;
    }

    public void setA_CM(String a_CM) {
        this.a_CM = a_CM;
    }

    public int getA_year() {
        return a_year;
    }

    public void setA_year(int a_year) {
        this.a_year = a_year;
    }

    public Assign(int a_id, int a_course, String a_CL, String a_CM, int a_year) {
        this.a_id = a_id;
        this.a_course = a_course;
        this.a_CL = a_CL;
        this.a_CM = a_CM;
        this.a_year = a_year;
    }

    public Assign(int a_course, String a_CL, String a_CM, int a_year) {
        this.a_course = a_course;
        this.a_CL = a_CL;
        this.a_CM = a_CM;
        this.a_year = a_year;
    }
    
    
}
