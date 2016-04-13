/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author JelZ
 */
public class Classes {
    private int cl_id;
    private String cl_name;
    private String cl_academicSession;

    public Classes(int cl_id, String cl_name, String cl_academicSession) {
        this.cl_id = cl_id;
        this.cl_name = cl_name;
        this.cl_academicSession = cl_academicSession;
    }

    public Classes(String cl_name, String cl_academicSession) {
        this.cl_name = cl_name;
        this.cl_academicSession = cl_academicSession;
    }

    public int getCl_id() {
        return cl_id;
    }

    public void setCl_id(int cl_id) {
        this.cl_id = cl_id;
    }

    public String getCl_name() {
        return cl_name;
    }

    public void setCl_name(String cl_name) {
        this.cl_name = cl_name;
    }

    public String getCl_academicSession() {
        return cl_academicSession;
    }

    public void setCl_academicSession(String cl_academicSession) {
        this.cl_academicSession = cl_academicSession;
    }
    
    
}
