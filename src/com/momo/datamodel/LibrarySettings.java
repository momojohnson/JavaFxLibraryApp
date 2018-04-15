package com.momo.datamodel;

import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;

public class LibrarySettings {
    private int numOfDayWithoutFine; // number of days variable
    private double finePerDay; // fine per day variable
    private String username; // default username upon program load
    private String password; // default password upon program load
    public static final String CONFIG_FILE = "config.txt"; // config file name

    public LibrarySettings() {
        this.numOfDayWithoutFine = 21;
        this.finePerDay = 1.50;
        this.username = "username";
        this.setPassword("password");
    }

    // return number of day without fine
    public int getNumOfDayWithoutFine() {
        return numOfDayWithoutFine;
    }

    // set number of days without fine
    public void setNumOfDayWithoutFine(int numOfDayWithoutFine) {
        this.numOfDayWithoutFine = numOfDayWithoutFine;
    }

    // return fine per day
    public double getFinePerDay() {
        return finePerDay;
    }
    // set fine per day
    public void setFinePerDay(double finePerDay) {
        this.finePerDay = finePerDay;
    }

    // return username
    public String getUsername() {
        return username;
    }
    // set username
    public void setUsername(String username) {
        this.username = username;
    }
    // return password
    public String getPassword() {
        return password;
    }
    // Set password
    public void setPassword(String password) {
        if(password.length() < 16){
            this.password = DigestUtils.shaHex(password);
            return;
        }
        this.password = password;
    }

    // Write and covert default LibraryFile to json format
    public static void initConfig(){
        File file = new File(CONFIG_FILE);
       try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
           LibrarySettings libraryFine = new LibrarySettings();
           Gson gson = new Gson();
           gson.toJson(libraryFine, writer);

       }catch (IOException exe){

       }
    }
    // Convert a library fine object to json format
    public static LibrarySettings getLibrarySettings(){
        LibrarySettings libraryFine = null;
        Gson gson = new Gson();
        try(BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))){
            libraryFine = gson.fromJson(reader, LibrarySettings.class);

        }catch (FileNotFoundException exe){
            initConfig();
            exe.printStackTrace();
        }catch (IOException exe){
            System.out.println(exe.getMessage());
        }
        return libraryFine;
    }

    // Update the config with file with these values
    public static void updateConfigFile(int daysWithoutFine, double fine, String username, String password){
        File file = new File(CONFIG_FILE);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            LibrarySettings libraryFine = new LibrarySettings();
            libraryFine.setNumOfDayWithoutFine(daysWithoutFine);
            libraryFine.setFinePerDay(fine);
            libraryFine.setUsername(username);
            libraryFine.setPassword(password);
            Gson gson = new Gson();
            gson.toJson(libraryFine, writer);

        }catch (IOException exe){

        }
    }

    // A method to create a config file  when application starts
    public static void createConfigFile(){
        File file = new File(CONFIG_FILE);
        if(!file.exists() && !file.isDirectory()){
            initConfig();
        }
    }
}
