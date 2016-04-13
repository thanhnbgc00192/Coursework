/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author JelZ
 */
public class Falculty {
    private int f_id;
    private String f_name;
    private String f_PVC;
    private String f_DLT;
    
    public Falculty(int f_id, String f_name) {
        this.f_id = f_id;
        this.f_name = f_name;
    }

    public Falculty(String f_name, String f_PVC, String f_DLT) {
        this.f_name = f_name;
        this.f_PVC = f_PVC;
        this.f_DLT = f_DLT;
    }

    public Falculty(int f_id, String f_name, String f_PVC, String f_DLT) {
        this.f_id = f_id;
        this.f_name = f_name;
        this.f_PVC = f_PVC;
        this.f_DLT = f_DLT;
    }

    public Falculty(String f_name) {
        this.f_name = f_name;
    }

    public String getF_PVC() {
        return f_PVC;
    }

    public void setF_PVC(String f_PVC) {
        this.f_PVC = f_PVC;
    }

    public String getF_DLT() {
        return f_DLT;
    }

    public void setF_DLT(String f_DLT) {
        this.f_DLT = f_DLT;
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
    
    
}
