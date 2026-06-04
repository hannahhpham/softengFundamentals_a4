package com.busdriver;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class DriverRepository {
    // Add (), Update (), Retrieve (), Count () functions
    //need to check that when adding driver, driverID is unique.
    //driverID and name cant be modified during update
    //check spec for full list of details

    public List<Driver> readDriverRepo(){
        String currDirectory = new File(".").getAbsolutePath();
        File file = new File(currDirectory + "/src/main/java/com/busdriver/DriverRepository.txt");
        List<Driver> drivers = new ArrayList<>();

        if (!file.exists() || file.length() == 0){
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Driver>>(){}.getType();
            drivers = gson.fromJson(reader, listType);
            for (Driver d: drivers){
                System.out.println("Parsing object DriverID: " + d.getDriverID());
            }
            if (drivers != null) {
                return drivers;
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e){
            System.out.println("Error reading repo: " + e.getMessage());
        }
        return drivers;
    }

    List<Driver> driverRepo = readDriverRepo();

    public void saveDriverRepo(){
        String currDirectory = new File(".").getAbsolutePath();
        File file = new File(currDirectory + "/src/main/java/com/busdriver/DriverRepository.txt");

        try (Writer writer = new FileWriter(file)){
            Gson gson = new Gson();
            gson.toJson(driverRepo, writer);}
            catch (IOException e){
                System.out.println("Serialisation Failed: " + e.getMessage());
            }

        }
    

    //Add driver, checks if driver id is same as another one
    public void addDriver(Driver driver){
        //Ensures driver ID is unique
        boolean idTaken = driverRepo.stream().anyMatch(d -> d.getDriverID().equals(driver.getDriverID()));

        if (idTaken){
            throw new IllegalArgumentException("DriverID already taken");
        }
            driverRepo.add(driver);
            saveDriverRepo();
    }

    //Get driver object
    public Driver getDriver(String driverID){
        for (Driver driver:driverRepo){
            if (driver.getDriverID().equals(driverID)){
                return driver;
            }
        }
        throw new IllegalArgumentException("Driver not found");
    }

    //Update address
    public void updateDriverAddress(String driverID, String newAddress){
        for (Driver driver: driverRepo){
            if (driver.getDriverID().equals(driverID)){
                driver.updateDriverInfo(driver.getExperienceYears(), driver.getLicenseType(), newAddress, driver.getBirthDate());
                saveDriverRepo();
                return;
            }
        }
        throw new IllegalArgumentException("Driver not found");
    }

    //Update experience and license type
    public void updateDriverExperienceAndLicense(String driverID, int experienceYears, String licenseType){
        for (Driver driver:driverRepo){
            if (driver.getDriverID().equals(driverID)){
                driver.updateDriverInfo(experienceYears, licenseType, driver.getAddress(), driver.getBirthDate());
                saveDriverRepo();
                return;
            }
        }
        throw new IllegalArgumentException("Driver not found");
    }

    //Update birthdate
    public void updateDriverBirthdate(String driverID, String newBirthDate){
        for (Driver driver : driverRepo){
            if (driver.getDriverID().equals(driverID)){
            driver.updateDriverInfo(driver.getExperienceYears(), driver.getLicenseType(), driver.getAddress(), newBirthDate);
            saveDriverRepo();
            return;
            }
        }
    throw new IllegalArgumentException("Driver not found");
    }

    public int getDriverCount(){
        return driverRepo.size();
    }
}

