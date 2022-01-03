package com.ifly.repositories;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

public class BaseRepository {

    protected static Connection connection = null;
    private static final String url = "jdbc:postgresql://localhost:5432/iFly";
    private static final String password = "12345";
    private static final String user = "postgres";

    public void openConnection(){
        try{
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
