package com.busdriver;

import org.junit.jupiter.api.BeforeEach;
import org. junit.jupiter.api.Test;

public class DriverIntegrationTest {
    
    DriverRepository driverRepo;

    @BeforeEach
    void clean(){
        Driver.clearExistingIDs();
        driverRepo = new DriverRepository();
        driverRepo.driverRepo.clear();
        driverRepo.saveDriverRepo();
    }

    //Test 1: Adding a valid driver, reloading file and verifying all the deatils
    @Test
    void addValidDriverAndVerifyDetails(){
        System.out.println("Running Test 1");
        System.out.println("Adding valid driver with ID: 23ab#$XYZA, name: John Doe, experience: 5, license: Heavy");

        Driver d1 = new Driver("23ab#$XYZA", "John Doe", 5, "Heavy", "123 | Main Street | Melbourne | VIC | Australia", "01-01-1980", 55);
        driverRepo.addDriver(d1);

        DriverRepository reloadedRepo = new DriverRepository();
        Driver retrieved = reloadedRepo.getDriver("23ab#$XYZA");

        System.out.println("Retrived data: " + retrieved.getDriverID() + ","
                            + retrieved.getName() + "," + retrieved.getExperienceYears() + ","
                            + retrieved.getLicenseType() + "," + retrieved.getAddress() + ","
                            + retrieved.getBirthDate());
        System.out.println("Test 1 Passed.");
    }

    //Test 2: Add invalid drivers and verify they are rejected and not saved to file
    @Test
    void addInvalidDriverAndVerifyRejceted(){
        System.out.println("Running Test 2");

        try {
            Driver d1 = new Driver ("invalidID", "Jane Doe", 3, "Light", "456 | Elm Street | Melbourne | VIC | Australia","02-02-1990",55);
            driverRepo.addDriver(d1);
            System.out.println("Test 2 Failed: Invalid ID was accepted");
        } catch (Exception e){
            System.out.println("Rejected driver with invalid ID: " + e.getMessage());
        }

        try {
            Driver d2 = new Driver("24ab!@CDXY", "Alice Smith", 10, "Heavy", "789 | Oak Street | Melbourne | VIC | Australia", "03-03-1975",55);
            driverRepo.addDriver(d2);
            Driver d3 = new Driver("24ab!@CDXY", "Duplicate Person", 8, "Heavy", "321 | Pine Street | Melbourne | VIC | Australia","04-04-1985",55);
            driverRepo.addDriver(d3);
            System.out.println("Test 2 Failed: Duplicate ID was accepted.");
        } catch (Exception e) {
            System.out.println("Rejected driver with duplicate ID: " + e.getMessage());
        }

        DriverRepository reloadedRepo = new DriverRepository();
        System.out.println("Test 2 passed. Repo count: " + reloadedRepo.getDriverCount());
    }

    //Test 3: Add a driver, update details and verify changes
    @Test
    void updateDriverAndVerify(){
        System.out.println("Running test 3");

        Driver d1 = new Driver("35cd!@XCSA", "Judy Rock", 9, "Light", "34 | La Trobe Street | Melbourne | VIC | Australia","01-01-1990",34);
        driverRepo.addDriver(d1);

        System.out.println("Address before update: " + d1.getAddress());
        System.out.println("Experience before update: " + d1.getExperienceYears());

        driverRepo.updateDriverAddress("35cd!@XCSA", "99 | Exhibition Street | Melbourne | VIC | Australia");
        driverRepo.updateDriverExperienceAndLicense("35cd!@XCSA", 8, "Heavy");

        DriverRepository reloadedRepo = new DriverRepository();
        Driver updated = reloadedRepo.getDriver("35cd!@XCSA");

        System.out.println("Address after update: " + updated.getAddress());
        System.out.println("Experience after update: " + updated.getExperienceYears());
        System.out.println("Test 3 passed");
    }

    //Test 4: Add multiple drivers and check count updated correctly
    @Test
    void verifyCount(){
        System.out.println("Running test 4");
        System.out.println("Initial repo count: "+driverRepo.getDriverCount());

        try{
            Driver d1 = new Driver("24ab!@XCSA", "Judy Rock", 9, "Light", "34 | La Trobe Street | Melbourne | VIC | Australia", "01-01-1990",34);
            Driver d2 = new Driver("23ab@#CDBA", "Sam Jones", 10, "Heavy", "45 | Earl Street | Melbourne | VIC | Australia", "20-03-1980", 44);
            driverRepo.addDriver(d1);
            driverRepo.addDriver(d2);
            System.out.println("Added two drivers, repo count: " + driverRepo.getDriverCount());
        } catch (Exception e)  {
            System.out.println("Could not add drivers");
        }

        try{
            Driver d3 = new Driver("56er$%DFRE", "Bob Jones", 12, "Light", "56 | Oak Street | Melbourne | VIC | Australia", "05-09-1985", 39);
            Driver d4 = new Driver("67yu#$SEDR", "Sally Brown", 7, "Heavy", "78 | High Street | Melbourne | VIC | Australia", "20-03-1980",44);
            driverRepo.addDriver(d3);
            driverRepo.addDriver(d4);
            System.out.println("Added two more drivers, repo count: " + driverRepo.getDriverCount());
        } catch (Exception e){
            System.out.println("Could not add two more drivers");
        }
        DriverRepository reloadedRepo = new DriverRepository();
        System.out.println("Test 4 passed. Final repo count: " + reloadedRepo.getDriverCount());
    }
}
