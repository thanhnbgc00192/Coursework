/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.CookieHelper;
import db.NotificationDB;
import entities.Notification;
import entities.Users;
import javax.inject.Named;

import java.io.Serializable;
import java.util.Date;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Cookie;

/**
 *
 * @author JelZ
 */
@ManagedBean
@SessionScoped
public class NotificationBean implements Serializable {

    private int n_id;
    private int n_report;
    private String n_CL;
    private String n_CM;
    private String n_title;
    private String n_message;
    private String n_status;
    private Date n_time;
    private List<Notification> list;
    private int count;

    public NotificationBean() {
    }

    public String getTimeAgo(Date date) {
        Calendar cal = Calendar.getInstance();
        Date dateNow = (Date) cal.getTime();
        String ago;
        long diff = dateNow.getTime() - date.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        String days = String.valueOf(diffDays);
        String hours = String.valueOf(diffHours);
        String mins = String.valueOf(diffMinutes);
        String secs = String.valueOf(diffSeconds);
        if (days == null || "".equals(days) || Integer.parseInt(days)==0) {
            if (hours == null || "".equals(hours)|| Integer.parseInt(hours)==0) {
                if (mins == null || "".equals(mins)|| Integer.parseInt(mins)==0) {
                    ago = diffSeconds + " secs ago";
                    return ago ;
                }
                else{
                    ago = diffMinutes + " mins ago";
                    return ago ;
                }
            }
            else {
                ago = diffHours + " hours ago";
                return ago ;
            }
        }   
        else{
            ago = diffDays + " days ago";
            return ago ;
        }
        
        
}
    public List<Notification> getList() {
        CookieHelper ch = new CookieHelper();
        Cookie ck = ch.getCookie("u_id");
        String cm = ck.getValue();

        NotificationDB db = new NotificationDB();
        list = db.getAllNotification(cm);
        if(list==null){
            return null;
        }
        return list;
    }

    public int getCount() {
        CookieHelper ch = new CookieHelper();
        Cookie ck = ch.getCookie("u_id");
        String cm = ck.getValue();
        
        NotificationDB db = new NotificationDB();
        count = db.countAllNotification(cm);
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void setList(List<Notification> list) {
        this.list = list;
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

    public String getN_CM() {
        return n_CM;
    }

    public void setN_CM(String n_CM) {
        this.n_CM = n_CM;
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
