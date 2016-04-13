/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.FalcultyDB;
import entities.Falculty;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;


/**
 *
 * @author JelZ
 */
@ManagedBean
@SessionScoped
public class FalcultyBean {
    private int f_id;
    private String f_name;
    private List<Falculty> list;
    private int falcultyId;
    private String f_pvc;
    private String f_DLT;
    
    
    public FalcultyBean() {
    }

    public String getNameFalculty(int id){
        FalcultyDB db = new FalcultyDB();
        String name = "";
        return db.getFalculty(id).getF_name();
    }
    
    public void insertFalculty(){
        if("".equals(f_name) || f_name==null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Required!", "Falculty name required!");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        else{
            if(f_DLT.equals("required")){
                f_DLT = null;
            }
            else if(f_pvc.equals("required")){
                f_pvc = null;
            }
            else{
                Falculty f = new Falculty(f_name, f_pvc, f_DLT);
                FalcultyDB db = new FalcultyDB();
                boolean c = db.insertFalculty(f);
                if(c){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert!", "Insert falculty successful!");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
                else{
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insert!", "Insert falculty fail!");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            }
        }
    }
    
    public String getParameter(String name){
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String pa = params.get(name);
        return pa;
    }
    
    public List<Falculty> getList() {
        FalcultyDB db = new FalcultyDB();
        list = db.getAllFalculty();
        return list;
    }

    public String getF_pvc() {
        return f_pvc;
    }

    public void setF_pvc(String f_pvc) {
        this.f_pvc = f_pvc;
    }

    public String getF_DLT() {
        return f_DLT;
    }

    public void setF_DLT(String f_DLT) {
        this.f_DLT = f_DLT;
    }

    public void setList(List<Falculty> list) {
        this.list = list;
    }

    
    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public int getFalcultyId() {
        return falcultyId;
    }

    public void setFalcultyId(int falcultyId) {
        this.falcultyId = falcultyId;
    }
    
    
}
