package com.busdriver;

import java.util.ArrayList;

public class DriverRepository {
    // Add (), Update (), Retrieve (), Count () functions
    //need to check that when adding driver, driverID is unique.
    //driverID and name cant be modified during update
    //check spec for full list of details

    ArrayList<Driver> driverRepo = new ArrayList<Driver>();

    //Add driver, checks if driver id is same as another one
    public void addDriver(Driver driver){
        //Ensures driver ID is unique
        boolean idTaken = driverRepo.stream().anyMatch(d -> d.getDriverID().equals(driver.getDriverID()));

        if (idTaken){
            throw new IllegalArgumentException("DriverID already taken");
        }
        else{
            driverRepo.add(driver);
        }
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
                driver.updateDriverInfo(driver.getExperienceYears(), driver.getLicenseType(), newAddress, driver.getBirthDate() , driver.getAge());
                return;
            }
        }
        throw new IllegalArgumentException("Driver not found");
    }

    //Update experience and license type
    public void updateDriverExperienceAndLicense(String driverID, int experienceYears, String licenseType){
        for (Driver driver:driverRepo){
            if (driver.getDriverID().equals(driverID)){
                driver.updateDriverInfo(experienceYears, licenseType, driver.getAddress(), driver.getBirthDate(), driver.getAge());
                return;
            }
        }
        throw new IllegalArgumentException("Driver not found");
    }

    //Update birthdate
    public void updateDriverBirthdate(String driverID, String newBirthDate){
        for (Driver driver : driverRepo){
            if (driver.getDriverID().equals(driverID)){
            driver.updateDriverInfo(driver.getExperienceYears(), driver.getLicenseType(), driver.getAddress(), newBirthDate, driver.getAge());
            return;
            }
        }
    throw new IllegalArgumentException("Driver not found");
    }

    public int getDriverCount(){
        return driverRepo.size();
    }
}

