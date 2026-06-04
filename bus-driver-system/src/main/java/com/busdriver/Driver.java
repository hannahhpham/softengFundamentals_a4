package com.busdriver;

import java.util.HashSet;
import java.util.Set;

public class Driver { 

    private String driverID; 
    private String name; 
    private int experienceYears; 
    private String licenseType; // Light, Medium, Heavy, PublicTransport 
    private String address; 
    private String birthDate; 
    private int age;


    // D1: driverID must be unique
    private static transient Set<String> existingDriverIDs = new HashSet<>();

    public Driver(String driverID, String name, int experienceYears, String licenseType, String address, String birthDate, int age) {
        
        // Validate and set normal fields
        setName(name);
        setExperienceYears(experienceYears);
        setLicenseType(licenseType);
        setAddress(address);
        setBirthDate(birthDate);
        // as per bus guideline
        setAge(age);

        // Set driverID last so it only gets added if everything else is valid
        setDriverID(driverID);
    }

    // getter methods

    public String getDriverID() {
       return driverID;

     }
      public String getName() {
        return name;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return age;
    }

    // D5: driverID cannot be modified after creation
    private void setDriverID(String driverID) {
        if (!isValidDriverID(driverID)) {
            throw new IllegalArgumentException("Invalid Driver ID.");
        }

        if (existingDriverIDs.contains(driverID)) {
            throw new IllegalArgumentException("Driver ID already exists.");
        }

        this.driverID = driverID;
        existingDriverIDs.add(driverID);
    }

    // D5: name cannot be modified after creation
    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        this.name = name;
    }

    // Private setters for fields that can be updated  

    private void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            throw new IllegalArgumentException("Experience years cannot be negative.");
        }

        this.experienceYears = experienceYears;
    }

    private void setLicenseType(String licenseType) {
        if (!isValidLicenseType(licenseType)) {
            throw new IllegalArgumentException("Invalid license type.");
        }

        this.licenseType = licenseType;
    }

    private void setAddress(String address) {
        if (!isValidAddress(address)) {
            throw new IllegalArgumentException("Invalid address format.");
        }

        this.address = address;
    }

    private void setBirthDate(String birthDate) {
        if (!isValidBirthDate(birthDate)) {
            throw new IllegalArgumentException("Invalid birthdate format.");
        }

        this.birthDate = birthDate;
    }

    private void setAge(int age) {
       if (age < 0) {
           throw new IllegalArgumentException("Age cannot be negative.");
       }

        this.age = age;
     }

    // D4: License Update Restriction
    public void updateDriverInfo(int experienceYears, String licenseType,
                                 String address, String birthDate) {

        // If current driver has more than 10 years experience,
        // their license type cannot be changed
        if (this.experienceYears > 10 && !this.licenseType.equals(licenseType)) {
            throw new IllegalArgumentException(
                "License type cannot be updated for drivers with more than 10 years of experience."
            );
        }

        setExperienceYears(experienceYears);
        setLicenseType(licenseType);
        setAddress(address);
        setBirthDate(birthDate);
    }

    // D1: Driver ID validation
    private boolean isValidDriverID(String driverID) {
        // Must be exactly 10 characters
        if (driverID == null || driverID.length() != 10) {
            return false;
        }

        // First two characters must be digits between 2 and 9
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

        // Last two characters must be uppercase letters A-Z
        for (int i = 8; i < 10; i++) {
            char c = driverID.charAt(i);

            if (c < 'A' || c > 'Z') {
                return false;
            }
        }

        return true;
    }

    // D2: Address Format
    private boolean isValidAddress(String address) {
        if (address == null || address.isEmpty()) {
            return false;
        }

        // Format: Street Number | Street Name | City | State | Country
        String[] parts = address.split("\\s*\\|\\s*", -1);

        if (parts.length != 5) {
            return false;
        }

        String streetNumber = parts[0];
        String streetName = parts[1];
        String city = parts[2];
        String state = parts[3];
        String country = parts[4];

        // Street number must be numeric
        if (!streetNumber.matches("\\d+")) {
            return false;
        }

        // Other fields must not be empty
        if (streetName.isEmpty() || city.isEmpty() ||
            state.isEmpty() || country.isEmpty()) {
            return false;
        }

        return true;
    }

    // D3: Birthdate Format
    private boolean isValidBirthDate(String birthDate) {
        if (birthDate == null || birthDate.isEmpty()) {
            return false;
        }

        // Format: DD-MM-YYYY
        return birthDate.matches("\\d{2}-\\d{2}-\\d{4}");
    }

    private boolean isValidLicenseType(String licenseType) {
        if (licenseType == null) {
            return false;
        }

        return licenseType.equals("Light") ||
               licenseType.equals("Medium") ||
               licenseType.equals("Heavy") ||
               licenseType.equals("PublicTransport");
    }

    // Sanika's added method for the integration tests?
    public static void clearExistingIDs(){
        existingDriverIDs.clear();
    }
}
        
        
