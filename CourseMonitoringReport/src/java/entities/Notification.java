/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author JelZ
 */
public class Notification {
    private int n_id;
    private int n_report;
    private String n_CL;
    private String n_CM;
    private String n_title;
    private String n_message;
    private String n_status;
    private Date n_time;

    public Notification(int n_id, int n_report, String n_CL, String n_CM, String n_title, String n_message, String n_status, Date n_time) {
        this.n_id = n_id;
        this.n_report = n_report;
        this.n_CL = n_CL;
        this.n_CM = n_CM;
        this.n_title = n_title;
        this.n_message = n_message;
        this.n_status = n_status;
        this.n_time = n_time;
    }

    public Notification(int n_report, String n_CL, String n_CM, String n_title, String n_message, String n_status, Date n_time) {
        this.n_report = n_report;
        this.n_CL = n_CL;
        this.n_CM = n_CM;
        this.n_title = n_title;
        this.n_message = n_message;
        this.n_status = n_status;
        this.n_time = n_time;
    }

    public String getN_CM() {
        return n_CM;
    }

    public void setN_CM(String n_CM) {
        this.n_CM = n_CM;
    }

    

    public int getN_id() {
        return n_id;
    }

    public void setN_id(int n_id) {
        this.n_id = n_id;
    }

    public int getN_report() {
        return n_report;
    }

    public void setN_report(int n_report) {
        this.n_report = n_report;
    }

    public String getN_CL() {
        return n_CL;
    }

    public void setN_CL(String n_CL) {
        this.n_CL = n_CL;
    }

    public String getN_title() {
        return n_title;
    }

    public void setN_title(String n_title) {
        this.n_title = n_title;
    }

    public String getN_message() {
        return n_message;
    }

    public void setN_message(String n_message) {
        this.n_message = n_message;
    }

    public String getN_status() {
        return n_status;
    }

    public void setN_status(String n_status) {
        this.n_status = n_status;
    }

    public Date getN_time() {
        return n_time;
    }

    public void setN_time(Date n_time) {
        this.n_time = n_time;
    }
    
    
}
