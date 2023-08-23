package com.example.itsak1;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class RepoInjection {

    private Properties properties = new Properties();

    public RepoInjection(){
        try{
            properties.load(new FileInputStream("src/main/java/com/example/itsak1/settings.properties"));
        }catch (Exception e){
            System.out.println("Failed to read properties");
        }
    }

    public User login(String name, String password){

        String sql = "select * from itsak1.user where username = '"+ name + "' and password = '"+ password + "'";
        System.out.println(sql);
        try(Connection con = DriverManager.getConnection(
                properties.getProperty("connection"),
                properties.getProperty("username"),
                properties.getProperty("password"));
            Statement stmn = con.createStatement();
            ResultSet rs = stmn.executeQuery(sql)) {

            while (rs.next()){
                int id = rs.getInt("user.id");
                String userName = rs.getString("user.username");
                String pass = rs.getString("user.password");
                return new User(id, userName, pass);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new User();
    }

}
