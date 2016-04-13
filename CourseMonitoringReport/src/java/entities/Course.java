/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author JelZ
 */
public class Course {
    private int c_code;
    private String c_name;
    private String c_avatar;
    private String c_desc;
    private int falculty;

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

    public String getC_avatar() {
        return c_avatar;
    }

    public void setC_avatar(String c_avatar) {
        this.c_avatar = c_avatar;
    }

    public String getC_desc() {
        return c_desc;
    }

    public void setC_desc(String c_desc) {
        this.c_desc = c_desc;
    }

    public int getFalculty() {
        return falculty;
    }

    public void setFalculty(int falculty) {
        this.falculty = falculty;
    }

    public Course(int c_code, String c_name, String c_avatar, String c_desc, int falculty) {
        this.c_code = c_code;
        this.c_name = c_name;
        this.c_avatar = c_avatar;
        this.c_desc = c_desc;
        this.falculty = falculty;
    }

    public Course(String c_name, String c_avatar, String c_desc, int falculty) {
        this.c_name = c_name;
        this.c_avatar = c_avatar;
        this.c_desc = c_desc;
        this.falculty = falculty;
    }
    
    
}
