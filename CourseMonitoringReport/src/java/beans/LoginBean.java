/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.CookieHelper;
import db.LoginDB;
import entities.Users;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import org.primefaces.context.RequestContext;

/**
 *
 * @author JelZ
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private boolean checkbox;
    private String account;
    private String password;
    private String checkLoginInformation;

    public LoginBean() {
    }

    public void loginAction() {
        CookieHelper ch = new CookieHelper();
        Users c = checkInformation();
        if (c!=null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml?faces-redirect=true&position="+c.getU_position());
                ch.setCookie("u_id", c.getU_id(), 3600);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", checkLoginInformation);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    private Users checkInformation() {
        //Check no special characters
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher mAccount = p.matcher(account);
        boolean bAccount = mAccount.find();
        Matcher mPassword = p.matcher(password);
        boolean bPassword = mPassword.find();

        LoginDB db = new LoginDB();
        Users u = db.checkLogin(account, password);

        int aLength = account.length();
        int pLength = password.length();
        
        if ("".equals(account)|| "USERNAME".equals(account)) {
            checkLoginInformation = "Please enter username!";
            return null;
        } else if ("".equals(password)|| "PASSWORD".equals(password)) {
            checkLoginInformation = "Please enter password!";
            return null;
        } else if (aLength <= 5 || aLength >= 20) {
            checkLoginInformation = "Username must be from 6-20 characters!";
            return null;
        } else if (pLength < 6 || pLength > 20) {
            checkLoginInformation = "Password must be from 6-20 characters!";
            return null;
        } else if (bAccount) {
            checkLoginInformation = "Username has special character!";
            return null;
        } else if (bPassword) {
            checkLoginInformation = "Password has special character!";
            return null;
        } else {
            if (u == null) {
                checkLoginInformation = "Username or password is wrong!";
                return null;
            } else {
                checkLoginInformation = "Login succesfull!";
                return u;
            }
        }

    }

    public void logoutAction(){
        try {
            CookieHelper ch = new CookieHelper();
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            Cookie ck = ch.getCookie("u_id");
            ck.setValue(null);
            ck.setPath(null);
            ck.setMaxAge(0);
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public String getCheckLoginInformation() {
        return checkLoginInformation;
    }

    public void setCheckLoginInformation(String checkLoginInformation) {
        this.checkLoginInformation = checkLoginInformation;
    }

    public boolean isBtnDisabled() {
        return !this.checkbox;
    }
}
