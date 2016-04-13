
import beans.CourseManagerBean;
import beans.NotificationBean;
import db.AssignDB;
import db.ConnectionUntil;
import db.CookieHelper;
import db.CourseDB;
import db.FalcultyDB;
import db.LoginDB;
import db.NotificationDB;
import db.ScheduleDB;
import entities.Course;
import entities.Notification;
import entities.Schedule;
import entities.Users;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JelZ
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CookieHelper ch = new CookieHelper();
        ch.setCookie("ccode", "AA", 3000);
        Cookie ck = ch.getCookie("ccode");
        System.out.println(ck.getValue());
        if(ck==null){
            System.out.println("null");
        }
    }
    
    
}
