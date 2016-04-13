/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.CookieHelper;
import db.CourseDB;
import db.PositionDB;
import db.UsersDB;
import entities.Course;
import entities.Users;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;

/**
 *
 * @author JelZ
 */
@ManagedBean
@SessionScoped
public class CourseManagerBean implements Serializable {

    private String u_id;
    private String u_fullname;
    private Date u_birthday;
    private String u_address;
    private String avartar;
    private String u_email;
    private String u_phone;
    private String u_username;
    private String u_password;
    private String u_position;
    private String c_code;
    private String c_name;
    private String c_avatar;
    private String c_desc;
    private int c_falculty;
    private Part file;
    private String fileAvatar;
    private List<Course> list;
    private String checkInformation;
    private boolean checkbox;
    private String searchValue;
    private int getId;
    private Course course;
    private String valueChange;
    
    public CourseManagerBean() {
    }

    public void init() {
        loadUser();
    }
    
    public void setCookieCCode(String code){
        CookieHelper ck = new CookieHelper();
        Cookie ck2 = ck.getCookie("c_code");
        if(ck2 == null){
            ck.setCookie("c_code", code, 100);
        }
        else{
            ck2.setValue(code);
            ck2.setMaxAge(100);
        }
    }
    
    public void insertCourse() {
        boolean c = validateInsertCourse();
        if (!c) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", checkInformation);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            CourseDB db = new CourseDB();
            if ("".equals(c_avatar) || c_avatar == null) {
                c_avatar = "avartar.jpg";
            }

            Course course = new Course(c_name, c_avatar, c_desc, c_falculty);
            boolean insert = db.insertCourse(course);
            if (insert) {
                upload();
                clearCourse();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Insert successful");
                RequestContext.getCurrentInstance().showMessageInDialog(message);

            }
        }

    }
    public void saveCookieCode(String code) {
        CookieHelper ch = new CookieHelper();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("staff-manager-report.xhtml?faces-redirect=true");
                ch.setCookie("ccode", code, 3600);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
    public void editCourse() {
        boolean c = validateEditCourse();
        if (!c) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", checkInformation);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            CourseDB db = new CourseDB();
            if (c_avatar != null || c_avatar != "") {
                course.setC_avatar(c_avatar);
            }
            boolean edit = db.editCourse(course);
            if (edit) {

                upload();
                clearCourse();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Edit successful!");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("manager-course.xhtml");
                } catch (Exception ex) {
                    Logger.getLogger(CourseManagerBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fail!", "Edit fail!");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        }

    }

    public void deleteCourse() {
        
        CourseDB db = new CourseDB();
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String deleID = params.get("deleID");
        getId = Integer.parseInt(deleID);
        System.out.println(deleID);
        boolean c = db.deleteCourse(getId);
        if (!c) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!", "Delete fail");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful!", "Delete successful");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public boolean validateEditCourse() {
        if ("".equals(course.getC_name())) {
            checkInformation = "Course name be required!";
            return false;
        } else if ("".equals(course.getC_desc())) {
            checkInformation = "Course description be required!";
            return false;
        } else if ("".equals(course.getFalculty()) || course.getFalculty() == 0) {
            checkInformation = "Falculty must be choose!";
            return false;
        } else if (!checkbox || checkbox != true) {
            checkInformation = "You don't want to create new course?";
            return false;
        } else {

            return true;
        }
    }

    public boolean validateInsertCourse() {
        if ("".equals(c_name)) {
            checkInformation = "Course name be required!";
            return false;
        } else if ("".equals(c_desc)) {
            checkInformation = "Course description be required!";
            return false;
        } else if ("".equals(c_falculty) || c_falculty == 0) {
            checkInformation = "Falculty must be choose!";
            return false;
        } else if (!checkbox || checkbox != true) {
            checkInformation = "You don't want to create new course?";
            return false;
        } else {

            return true;
        }
    }

    public void checkUrlCourseCode() {
        try {
            Map<String, String> params = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap();
            String parameterCourseCode = params.get("coursecode");
            CourseDB db = new CourseDB();
            if (parameterCourseCode == null || "".equals(parameterCourseCode)) {

                FacesContext.getCurrentInstance().getExternalContext().redirect("manager-course.xhtml");
            } else {
                try {
                    Course c = db.getCourse(Integer.parseInt(parameterCourseCode));
                } catch (Exception ex) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("manager-course.xhtml");
                }
            }
        } catch (Exception e) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("manager-course.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(CourseManagerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void upload() {
        String path = FacesContext.getCurrentInstance()
                .getExternalContext().getRealPath("images");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\images\\";
        try {
            InputStream in = file.getInputStream();
            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path + getFilename(file)));
            c_avatar = getFilename(file);
            out.write(data);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearCourse() {
        c_avatar = "";
        c_code = "";
        c_desc = "";
        c_name = "";
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    public void loadUser() {
        CookieHelper ch = new CookieHelper();
        UsersDB db = new UsersDB();
        Cookie ck = ch.getCookie("u_id");
        if (ck == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(CourseManagerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            Users u = db.getUser(ck.getValue());
            u_id = u.getU_id();
            u_fullname = u.getU_fullname();
            u_birthday = u.getU_birthday();
            u_address = u.getU_address();
            avartar = u.getAvartar();
            u_email = u.getU_email();
            u_phone = u.getU_phone();
            u_username = u.getU_username();
            u_password = u.getU_password();
            u_position = u.getU_position();
        }
    }

    public String parsePositonString() {
        PositionDB db = new PositionDB();
        String position = db.parsePosition(u_position);
        return position;
    }

    public String getU_id() {
        return u_id;
    }

    public String getValueChange() {
        return valueChange;
    }

    public void setValueChange(String valueChange) {
        this.valueChange = valueChange;
    }
    
    
    public Course getCourse() {
        CourseDB db = new CourseDB();
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String deleID = params.get("coursecode");
        getId = Integer.parseInt(deleID);
        course = db.getCourse(getId);
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Course> getList() {
        CourseDB db = new CourseDB();
        list = db.getAllCourse();
        return list;
    }

    

    public void setList(List<Course> list) {
        this.list = list;
    }

    public int getGetId() {
        return getId;
    }

    public void setGetId(int getId) {
        this.getId = getId;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_fullname() {
        return u_fullname;
    }

    public String getCheckInformation() {
        return checkInformation;
    }

    public void setCheckInformation(String checkInformation) {
        this.checkInformation = checkInformation;
    }

    public void setU_fullname(String u_fullname) {
        this.u_fullname = u_fullname;
    }

    public Date getU_birthday() {
        return u_birthday;
    }

    public void setU_birthday(Date u_birthday) {
        this.u_birthday = u_birthday;
    }

    public String getU_address() {
        return u_address;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getFileAvatar() {
        return fileAvatar;
    }

    public void setFileAvatar(String fileAvatar) {
        this.fileAvatar = fileAvatar;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_username() {
        return u_username;
    }

    public void setU_username(String u_username) {
        this.u_username = u_username;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_position() {
        return u_position;
    }

    public void setU_position(String u_position) {
        this.u_position = u_position;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
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

    public int getC_falculty() {
        return c_falculty;
    }

    public void setC_falculty(int c_falculty) {
        this.c_falculty = c_falculty;
    }
}
