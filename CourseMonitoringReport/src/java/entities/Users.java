/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author JelZ
 */
public class Users {
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

    public Users() {
    }

    public Users(String u_id, String u_fullname, Date u_birthday, String u_address, String avartar, String u_email, String u_phone, String u_username, String u_password, String u_position) {
        this.u_id = u_id;
        this.u_fullname = u_fullname;
        this.u_birthday = u_birthday;
        this.u_address = u_address;
        this.avartar = avartar;
        this.u_email = u_email;
        this.u_phone = u_phone;
        this.u_username = u_username;
        this.u_password = u_password;
        this.u_position = u_position;
    }

    public Users(String u_fullname, Date u_birthday, String u_address, String avartar, String u_email, String u_phone, String u_username, String u_password, String u_position) {
        this.u_fullname = u_fullname;
        this.u_birthday = u_birthday;
        this.u_address = u_address;
        this.avartar = avartar;
        this.u_email = u_email;
        this.u_phone = u_phone;
        this.u_username = u_username;
        this.u_password = u_password;
        this.u_position = u_position;
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
