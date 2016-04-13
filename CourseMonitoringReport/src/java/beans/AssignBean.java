/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.AssignDB;
import db.CookieHelper;
import db.UsersDB;
import entities.Assign;
import entities.DataModelAssign;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import org.primefaces.context.RequestContext;

/**
 *
 * @author JelZ
 */
@ManagedBean
@SessionScoped
public class AssignBean {

    private int a_id;
    private int a_course;
    private String a_CL;
    private String a_CM;
    private int a_year;
    private ArrayList<String> listYear;
    private Integer[] iYear;
    private DataModelAssign dma;
    private String c_code;
    
    public AssignBean() {
    }

    public List<DataModelAssign> getAllAssignOfStaff() {
        AssignDB db = new AssignDB();
        CookieHelper ch = new CookieHelper();
        Cookie ck = ch.getCookie("u_id");
        ResultSet rs = db.getAssignOfStaff(ck.getValue());
        List<DataModelAssign> listDMA = new ArrayList<DataModelAssign>();
        if (rs == null) {
            return null;
        } else {
            try {
                while (rs.next()) {
                    int ccode = Integer.parseInt(rs.getString("c_code"));
                    String cname = rs.getString("c_name");
                    String cdesc = rs.getString("c_desc");
                    String fname = rs.getString("f_name");
                    int year = Integer.parseInt(rs.getString("a_year"));
                    dma = new DataModelAssign(ccode, cname, cdesc, fname, a_year);
                    listDMA.add(dma);
                }
                return listDMA;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void insertAssign(String ccodes) {
               
        AssignDB db = new AssignDB();
        
        int ccode = Integer.parseInt(ccodes);

        Assign a = new Assign(ccode, a_CL, a_CM, a_year);
        boolean c = db.insertAssign(a);
        if (c) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Assign successful!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Assign fail!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public Integer[] getiYear() {
        if (iYear == null) {
            for (int i = 2000; i < 2017; i++) {
                iYear = new Integer[i];
            }
        }
        return iYear;
    }

    public void setiYear(Integer[] iYear) {
        this.iYear = iYear;
    }

    public void setListYear(ArrayList<String> listYear) {
        this.listYear = listYear;
    }

    public ArrayList<String> getListYear() {
        for (int i = 2000; i < 2016; i++) {
            listYear.add(String.valueOf(i));
        }
        return listYear;
    }

    public int getA_id() {
        return a_id;
    }

    public void setDma(DataModelAssign dma) {
        this.dma = dma;
    }

    public DataModelAssign getDma() {
        return dma;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public String getC_code() {
        return c_code;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public int getA_course() {
        return a_course;
    }

    public void setA_course(int a_course) {
        this.a_course = a_course;
    }

    public String getA_CL() {
        return a_CL;
    }

    public void setA_CL(String a_CL) {
        this.a_CL = a_CL;
    }

    public String getA_CM() {
        return a_CM;
    }

    public void setA_CM(String a_CM) {
        this.a_CM = a_CM;
    }

    public int getA_year() {
        return a_year;
    }

    public void setA_year(int a_year) {
        this.a_year = a_year;
    }
}
