/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author JelZ
 */
public class ConnectionUntil {
    public static Connection getConnection(String url,String user,String pass){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
