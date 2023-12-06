/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taise.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ADMIN
 */
public class DBHelper implements Serializable {
    public static Connection createConnection()
        throws /*ClassNotFoundException*/NamingException, SQLException{
        //1. Get current context
        Context currentContext = new InitialContext();
        //2.Lookup tomcat context
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        //3.Lookup our datasource
        DataSource ds = (DataSource)tomcatContext.lookup("SE1706DS");
        //4. Open con from ds
        Connection con = ds.getConnection();
        
        return con;
        
//        //1. Load serverrver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2.Create url connect String 
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=KHANHKT;";
//        //3.Open connection
//        Connection con = DriverManager.getConnection(url, "sa", "12345");
//        
//        return con;
    }
}