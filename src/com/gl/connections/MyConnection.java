package com.gl.connections;

import java.sql.*;

public class MyConnection {
    Connection con=null;
    String user = "root";
    String url = "jdbc:mysql://localhost:3306/jdbc";
    String password = "password";
    public Connection getMyConnections()
    {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);

        }
        catch (ClassNotFoundException e) {
                e.printStackTrace();
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
        return con;

    }

}
