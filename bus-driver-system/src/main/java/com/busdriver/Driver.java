package com.busdriver;

import java.util.HashSet;
import java.util.Set;

public class Driver { 
    private String driverID; 
    private String name; 
    private int experienceYears; 
    private String licenseType; // Light, Medium, Heavy, PublicTransport 
    private String address; 
    private String birthdate; 

    //NOTE: THIS IS NOT SPECIFIED IN THE SPEC. im adding this purely as a placeholder
    private int age;

    // D1 Driver ID Rules

    // driverID must be unique
    private static Set<String> existingDriverIDs = new HashSet<>();

    public Driver(String driverID, String name, int experienceYears, String licenseType, String address, String birthdate, int age) {
        // Driver ID Rules
        // Driver ID must be unique
        if (!isValidDriverID(driverID)) {
            throw new IllegalArgumentException("Invalid Driver ID.");
        }

        if (existingDriverIDs.contains(driverID)) {
            throw new IllegalArgumentException("Driver ID already exists.");
        }

        this.driverID = driverID;
        this.name = name;
        this.experienceYears = experienceYears;
        this.licenseType = licenseType;
        this.address = address;
        this.birthdate = birthdate;
        this.age = age;

        // Add the new Driver ID after successful validation 
        existingDriverIDs.add(driverID);

    }
    // DriverID must be exactly 10 characters long
    private boolean isValidDriverID(String driverID) {
        if (driverID.length() != 10) {
            return false;
        }

        // The first 2 characters must be digits between 2 and 9 
        for (int i = 0; i < 2; i++) {
            char c = driverID.charAt(i);
            if (c < '2' || c > '9') {
                return false;
            }
        }

        // Characters 3 to 8 must contain at least two special characters
        int specialCharCount = 0;

        for (int i = 2; i < 8; i++) {
            char c = driverID.charAt(i);

            if (!Character.isLetterOrDigit(c)) {
                specialCharCount++;
            }
          }
            if (specialCharCount < 2) {
            return false;
            }

        // The last two characters must be uppercase letters A-Z
        for (int i = 8; i < 10; i++) {
            char c = driverID.charAt(i);

            if (c < 'A' || c > 'Z') {
                return false;
            }
        }

            return true;
        }

        // D2 Address Format 
        private boolean isValidAddress(String address) {
            if (address == null || address.isEmpty()) {
                return false;
            }

            // create array 
            String[] parts = address.split("\\|");
            
            // Street Number | Street Name | City | State | Country
            if (parts.length != 5) {
                return false;
            }

            String streetNumber = parts[0].trim();
            String streetName = parts[1].trim();
            String city = parts[2].trim();
            String state = parts[3].trim();
            String country = parts[4].trim();
    
            // Validate street number (must be numeric)
            if (!streetNumber.matches("\\d+")) {
                return false;
            }

            // Validate street name, city, state, and country (must be non-empty)
            if (streetName.isEmpty() || city.isEmpty() || state.isEmpty() ||
                country.isEmpty()) {
                return false;
            }

            return true;
        }

        // D3 Birthday Format 
        private boolean isValidBirthdate(String birthdate) {
            
            if (birthdate == null || birthdate.isEmpty()) {
                return false;
            }

            // Validate format DD-MM-YYYY
            if (!birthdate.matches("\\d{2}-\\d{2}-\\d{4}")) {
            }
            
            return true;
        }

        // D4 License Update Restriction 
        public void updateDriverInfo(int experienceYears, String licenseType, String address, String birthdate, int age) {
            if (!isValidAddress(address)) {
                throw new IllegalArgumentException("Invalid address format.");
            }

            if (!isValidBirthdate(birthdate)) {
                throw new IllegalArgumentException("Invalid birthdate format.");
            }
          
        
           if (this.experienceYears > 10 && !this.licenseType.equals(licenseType)) {
              throw new IllegalArgumentException(
                 "License type cannot be updated for drivers with more than 10 years of experience."
             );
        }

        this.experienceYears = experienceYears;
        this.licenseType = licenseType;
        this.address = address;
        this.birthdate = birthdate;
        this.age = age;
        }


    
    //note from hannah: im adding these methods in cuz i need them for the bus class
    //this function probably doesn't need to exist the way ive done it (by returning the age variable).
    //in reality, getAge() should probably be currDate-birthdate 
    public int getAge() {
        return this.age;
    }

    public int getExperienceYears() {
        return this.experienceYears;
    }

    public String getLicenseType() {
        return this.licenseType;
    }

    //added methods (sanika)
    public String getDriverID() {
        return driverID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return aaddress;
    }

    public String getBirthDate() {
        return birthdate;
    }
} 