/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entities.Falculty;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JelZ
 */
public class FalcultyDB {

    private String url = "jdbc:sqlserver://localhost:1433;databaseName=CourseMonitoringReport";
    private String user = "sa";
    private String pass = "1234";

    public FalcultyDB() {
    }

    public List<Falculty> getAllFalculty() {
        try {
            String query = "SELECT * FROM Falculty";
            List<Falculty> list = new ArrayList<Falculty>();
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("f_id");
                String name = rs.getString("f_name");
                String pvc = rs.getString("f_PVC");
                String dlt = rs.getString("f_DLT");
                Falculty f = new Falculty(id,name, pvc, dlt);
                list.add(f);
                
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Falculty getFalculty(int id) {
        try {
            String query = "SELECT * FROM Falculty WHERE f_id="+id;
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                int id2 = rs.getInt("f_id");
                String name = rs.getString("f_name");
                Falculty f = new Falculty(id2, name);   
                return f;
            }
            
        } catch (Exception e) {
        }
        return null;
    }
    
    public boolean insertFalculty(Falculty f) {
        try {
            String query = "insert into Falculty(f_name,f_PVC,f_DLT) values('"+f.getF_name()+"','"+f.getF_PVC()+"','"+f.getF_DLT()+"')";
            Connection con = ConnectionUntil.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            int row = stmt.executeUpdate(query);
            if (row>0) {
                return true;
            }
            
        } catch (Exception e) {
        }
        return false;
    }
}
