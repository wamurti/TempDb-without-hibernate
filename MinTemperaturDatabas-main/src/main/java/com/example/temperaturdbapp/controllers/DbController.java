package com.example.temperaturdbapp.controllers;

import com.example.temperaturdbapp.models.DbModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbController {

    public List<DbModel> getAllTempsFromUser(String name) {
        Properties prop = new Properties();
        //Hämtar ansultningsinfo från properties filen
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String tableName;
        if(name.equals("Björn")){
            tableName = "bjornsensor";
        } else if (name.equals("Frej")) {
            tableName = "frejsensor";


        } else if (name.equals("Jonas")) {
            tableName = "jonassensor";


        } else if (name.equals("Alexander")) {
            tableName = "ackesensor";

        } else tableName = "bjornsensor";

        String sqlStr = String.format("SELECT id, temperatur ,tid FROM %s",tableName);
        System.out.println(sqlStr);

        List<DbModel> dbModelList = new ArrayList<>();



        try{
            Connection connection = DriverManager.getConnection(
                    prop.getProperty("spring.datasource.url"),
                    prop.getProperty("spring.datasource.username"),
                    prop.getProperty("spring.datasource.password"));
            PreparedStatement statement = connection.prepareStatement(sqlStr);
            statement.setString(1,tableName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DbModel dbModel = new DbModel();
                int id = resultSet.getInt("id"); // by column index
                dbModel.setId(id);

                float temp = resultSet.getFloat("temperatur"); // by column name
                dbModel.setTemperature(temp);

                Date datum = resultSet.getDate("tid"); // by column name
                dbModel.setDate(datum);

                Time tid = resultSet.getTime("tid"); // by column name
                dbModel.setTime(tid);

                dbModelList.add(dbModel);

            }

            connection.close();



        } catch (SQLException d) {
            System.out.println(d);
        }
        return dbModelList;



    }

    public DbModel getMinTempFromUser(String name) {
        Properties prop = new Properties();
        //Hämtar ansultningsinfo från properties filen
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String tableName;
        if(name.equals("Björn")){
            tableName = "bjornsensor";
        } else if (name.equals("Frej")) {
            tableName = "frejsensor";


        } else if (name.equals("Jonas")) {
            tableName = "jonassensor";


        } else if (name.equals("Alexander")) {
            tableName = "ackesensor";

        } else tableName = "bjornsensor";

        String sqlStr = String.format("SELECT id, MIN(temperatur) as temperatur,tid FROM %s",tableName);


        DbModel dbModel = new DbModel();
        List<DbModel> dbModelList = new ArrayList<>();



        try{
            Connection connection = DriverManager.getConnection(
                    prop.getProperty("spring.datasource.url"),
                    prop.getProperty("spring.datasource.username"),
                    prop.getProperty("spring.datasource.password"));
            PreparedStatement statement = connection.prepareStatement(sqlStr);
            statement.setString(1,tableName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id"); // by column index
                dbModel.setId(id);

                float temp = resultSet.getFloat("temperatur"); // by column name
                dbModel.setTemperature(temp);

                Date datum = resultSet.getDate("tid"); // by column name
                dbModel.setDate(datum);

                Time tid = resultSet.getTime("tid"); // by column name
                dbModel.setTime(tid);

                dbModelList.add(dbModel);

            }

            connection.close();



        } catch (SQLException d) {
            System.out.println(d);
        }
        return dbModel;


    }



    public DbModel getMaxTempFromUser(String name) {
        Properties prop = new Properties();
        //Hämtar ansultningsinfo från properties filen
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String tableName;
        if(name.equals("Björn")){
            tableName = "bjornsensor";
        } else if (name.equals("Frej")) {
            tableName = "frejsensor";


        } else if (name.equals("Jonas")) {
            tableName = "jonassensor";


        } else if (name.equals("Alexander")) {
            tableName = "ackesensor";

        } else tableName = "bjornsensor";

        String sqlStr = String.format("SELECT id, MAX(temperatur) as temperatur,tid FROM %s",tableName);


        DbModel dbModel = new DbModel();



        try{
            Connection connection = DriverManager.getConnection(
                    prop.getProperty("spring.datasource.url"),
                    prop.getProperty("spring.datasource.username"),
                    prop.getProperty("spring.datasource.password"));
            PreparedStatement statement = connection.prepareStatement(sqlStr);
            statement.setString(1,tableName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id"); // by column index
                dbModel.setId(id);

                float temp = resultSet.getFloat("temperatur"); // by column name
                dbModel.setTemperature(temp);

                Date datum = resultSet.getDate("tid"); // by column name
                dbModel.setDate(datum);

                Time tid = resultSet.getTime("tid"); // by column name
                dbModel.setTime(tid);

            }

            connection.close();



        } catch (SQLException d) {
            System.out.println(d);
        }
        return dbModel;



    }






}
