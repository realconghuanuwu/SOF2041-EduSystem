/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author huanl
 */
public class XJdbc {
    static String driverDb = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dbUrl = "jdbc:sqlserver://LUONGCONGHUAN:1433;encrypt=false;databaseName=EduSysPS28692;";
    static String userDb = "sa";
    static String passDb = "123456";
    
    static {
        try {
            Class.forName(driverDb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException {
        Connection con = DriverManager.getConnection(dbUrl,userDb,passDb);
        PreparedStatement stm;
        if(sql.trim().startsWith("{")) {
            stm = con.prepareCall(sql);
        } else {
            stm = con.prepareStatement(sql);
        }
        
        for (int i = 0; i < args.length; i++) {
            stm.setObject(i+1, args[i]);
        }
        return stm;
    
    };   
    
    public static  int update(String sql, Object...args) {
        try {
            PreparedStatement stm = XJdbc.getStmt(sql, args);
            try {
                return stm.executeUpdate();
            } finally {
                stm.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
    
    public static ResultSet query (String sql, Object...args) throws SQLException{
        PreparedStatement stm = XJdbc.getStmt(sql, args);
        return stm.executeQuery();
    };
    
    public static Object value(String sql, Object...args) {
        try {
            ResultSet rs = XJdbc.query(sql, args);
            if(rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
}
