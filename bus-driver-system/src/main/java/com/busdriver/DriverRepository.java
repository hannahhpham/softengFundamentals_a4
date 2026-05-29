package com.busdriver;

import java.util.ArrayList;

public class DriverRepository {
    // Add (), Update (), Retrieve (), Count () functions
    //need to check that when adding driver, driverID is unique.
    //driverID and name cant be modified during update
    //check spec for full list of details

    ArrayList<Driver> driverRepo = new ArrayList<Driver>();

    public void addDriver(Driver driver){
        //Ensures driver ID is unique
        boolean idTaken = driverRepo.stream().anyMatch(d -> getDriverID().equals(driver.getDriverID()));

        if (idTaken){
            throw new IllegalArgumentException(s: "DriverID already taken");
        }
        else{
            driverRepo.add(driver);
        }
    }
}
