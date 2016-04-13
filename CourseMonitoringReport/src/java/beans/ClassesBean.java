/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.ClassesDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author JelZ
 */
@ManagedBean
@SessionScoped
public class ClassesBean implements Serializable {
    private int cl_id;
    private String cl_name;
    private String cl_academicSession;
    /**
     * Creates a new instance of ClassesBean
     */
    public ClassesBean() {
        
    }
    
    public String getAcademicSession(){
        ClassesDB db = new ClassesDB();
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String coursecode = params.get("coursecode");
        int ccode=0;
        try {
            ccode = Integer.parseInt(coursecode);
            String as = db.getAcademicSession(ccode);
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
