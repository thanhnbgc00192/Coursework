/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.ClassesDB;
import db.UsersDB;
import entities.Users;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author JelZ
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
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
    private List<Users> list;
    private List<Users> listCL;
    private List<Users> listCM;
    private String valueChange="id";
    private Users u;
    private Users userByCode;
    private int countStudent;
    
    public UserBean() {
        
    }
    
    public List<Users> getUserByPostionPVC(){
        UsersDB db = new UsersDB();
        List<Users> listu = new ArrayList<Users>();
        listu = db.getUserByPosition("PVC");
        return listu;
    }
    public List<Users> getUserByPostionDLT(){
        UsersDB db = new UsersDB();
        List<Users> listu = new ArrayList<Users>();
        listu = db.getUserByPosition("DLT");
        return listu;
    }
    
    public String getUserNameBy(int code,String pos){
        UsersDB db = new UsersDB();
        return db.getUserNameById(code, pos);
    }
    
    public Users getAUser(String id){
        UsersDB db = new UsersDB();
        Users us = db.getUser(id);
        return us;
        
    }
    
    public void getUser(String id){
        UsersDB db = new UsersDB();
        u = db.getUser(id);
    }

    public Users getU() {
        
        return u;
    }

    public Users getUserByCode() {
        UsersDB db = new UsersDB();
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String coursecode = params.get("coursecode");
        try {
            Users as = db.getUserByCode(coursecode);
            return as;
        } catch (Exception e) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("staff-manager-course.xhtml");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public void setUserByCode(Users userByCode) {
        this.userByCode = userByCode;
    }
    
    
    
    public void handleValueChange(ValueChangeEvent event){
        valueChange = event.getNewValue().toString();
    }
    public List<Users> getList() {
        UsersDB db = new UsersDB();
        list = db.getAllUser();
        return list;
    }

    public void setList(List<Users> list) {
        this.list = list;
    }

    public int getCountStudent() {
        UsersDB db = new UsersDB();
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String coursecode = params.get("coursecode");
        try {
            int as = db.countStudent(Integer.parseInt(coursecode));
            return as;
        } catch (Exception e) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("staff-manager-course.xhtml");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return countStudent;
    }

    public void setCountStudent(int countStudent) {
        this.countStudent = countStudent;
    }

    public void setU(Users u) {
        this.u = u;
    }

    public List<Users> getListCL() {
        UsersDB db = new UsersDB();
        listCL = db.getListCL();
        return listCL;
    }

    public void setListCL(List<Users> listCL) {
        this.listCL = listCL;
    }

    public List<Users> getListCM() {
        UsersDB db = new UsersDB();
        listCM = db.getListCM();
        return listCM;
    }

    public void setListCM(List<Users> listCM) {
        this.listCM = listCM;
    }

    public String getValueChange() {
        return valueChange;
    }

    public void setValueChange(String valueChange) {
        this.valueChange = valueChange;
    }
    
    

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_fullname() {
        return u_fullname;
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
    
    
}
