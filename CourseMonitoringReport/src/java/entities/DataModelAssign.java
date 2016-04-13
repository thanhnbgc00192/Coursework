/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author JelZ
 */
public class DataModelAssign {
    private int c_code;
    private String c_name;
    private String c_desc;
    private String f_name;
    private int a_year;

    public DataModelAssign(int c_code, String c_name, String c_desc, String f_name, int a_year) {
        this.c_code = c_code;
        this.c_name = c_name;
        this.c_desc = c_desc;
        this.f_name = f_name;
        this.a_year = a_year;
    }

    public DataModelAssign(String c_name, String c_desc, String f_name, int a_year) {
        this.c_name = c_name;
        this.c_desc = c_desc;
        this.f_name = f_name;
        this.a_year = a_year;
    }

    public int getC_code() {
        return c_code;
    }

    public void setC_code(int c_code) {
        this.c_code = c_code;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_desc() {
        return c_desc;
    }

    public void setC_desc(String c_desc) {
        this.c_desc = c_desc;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public int getA_year() {
        return a_year;
    }

    public void setA_year(int a_year) {
        this.a_year = a_year;
    }
    
    
}
