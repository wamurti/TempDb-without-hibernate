package com.example.temperaturdbapp.controllers;

import com.example.temperaturdbapp.models.DbModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Time;
import java.util.*;


@Controller
public class ThymeBasicController {
    DbController dbController = new DbController();


    @RequestMapping("/dashboard")
    public String dashBoard(Model model){
        //model.addAttribute("name", name);
        return "index.html";
    }


    @RequestMapping("/getAllByUsername")
    public String getUserTempsFromName(@RequestParam String name, Model model){
        model.addAttribute("name", name);
        model.addAttribute("listan",dbController.getAllTempsFromUser(name));
        return "userTemps.html";
    }


    //Hämtar all data och sen filtrerar ut det mha java-kod för att fånga flera maxvärden än ett. Ex localhost:8080/getMaxByUsername?name=Björn
    @RequestMapping("/getMaxByUsername")
    public String getMaxTempFromName(@RequestParam String name2, Model model){
        System.out.println("Name is "+name2);

        List<DbModel> maxTempList = new ArrayList<>();
        DbModel maxTemp = dbController.getMaxTempFromUser(name2);


        List<DbModel> dbModel = dbController.getAllTempsFromUser(name2);
        for(int i = 0; i<dbModel.size();i++){

            if (dbModel.get(i).getTemperature() >= maxTemp.getTemperature()){
                maxTemp = new DbModel();
                maxTemp.setId(dbModel.get(i).getId());
                maxTemp.setTemperature(dbModel.get(i).getTemperature());
                maxTemp.setDate(dbModel.get(i).getDate());
                maxTemp.setTime(dbModel.get(i).getTime());
                maxTempList.add(maxTemp);
                System.out.println(maxTemp);
            }
        }
        System.out.println("Max temp is " + maxTemp);
        model.addAttribute("name", name2);
        model.addAttribute("listan",maxTempList);
        return "userTemps.html";
    }

    //Hämtar allas högsta
    @RequestMapping("/getAllMax")
    public String getAllMax(Model model){

        model.addAttribute("listanB",dbController.getMaxTempFromUser("Björn"));
        model.addAttribute("listanF",dbController.getMaxTempFromUser("Frej"));
        model.addAttribute("listanJ",dbController.getMaxTempFromUser("Jonas"));

        return "allUsers.html";
    }

    //Hämtar allas lägsta
    @RequestMapping("/getAllMin")
    public String getAllMin(Model model){

        model.addAttribute("listanB",dbController.getMinTempFromUser("Björn"));
        model.addAttribute("listanF",dbController.getMinTempFromUser("Frej"));
        model.addAttribute("listanJ",dbController.getMinTempFromUser("Jonas"));

        return "allUsers.html";
    }


    //Hämtar endast ut data med SQL query "MIN"
    @RequestMapping("/getMinByUsername")
    public String getMinTempFromName(@RequestParam String name, Model model){

        DbModel dbModel = dbController.getMinTempFromUser(name);

        model.addAttribute("name", name);
        model.addAttribute("listan",dbModel);
        return "userTemps.html";
    }



    @RequestMapping("/getAllUsers")
    public String getAllUserTempsFromName2(Model model){
        model.addAttribute("listanJ",dbController.getAllTempsFromUser("Jonas"));
        model.addAttribute("listanF",dbController.getAllTempsFromUser("Frej"));
        model.addAttribute("listanB",dbController.getAllTempsFromUser("Björn"));
        return "allUsers.html";
    }



    //Hämtar all data högre än x temp för namn. Ex http://localhost:8080/getTempHigherThanByUsername?temp=23.9&name=Björn
    @RequestMapping("/getTempHigherThanByUsername")
    public String getMaxHigherTempFromName(@RequestParam String name,@RequestParam float temp, Model model){

        List<DbModel> maxTempList = new ArrayList<>();
        DbModel maxTemp = new DbModel();
        maxTemp.setTemperature(temp);


        List<DbModel> dbModel = dbController.getAllTempsFromUser(name);
        for(int i = 0; i<dbModel.size();i++){

            if (dbModel.get(i).getTemperature() > maxTemp.getTemperature()){
                maxTemp = new DbModel();
                maxTemp.setId(dbModel.get(i).getId());
                maxTemp.setTemperature(dbModel.get(i).getTemperature());
                maxTemp.setDate(dbModel.get(i).getDate());
                maxTemp.setTime(dbModel.get(i).getTime());
                maxTempList.add(maxTemp);
                System.out.println(maxTemp);
            }
        }
        model.addAttribute("name", name);
        model.addAttribute("listan",maxTempList);
        return "userTemps.html";
    }
}
