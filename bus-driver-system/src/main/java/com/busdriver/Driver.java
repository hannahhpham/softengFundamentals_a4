package com.busdriver;

public class Driver { 
    private String driverID; 
    private String name; 
    private int experienceYears; 
    private String licenseType; // Light, Medium, Heavy, PublicTransport 
    private String address; 
    private String birthdate; 

    //NOTE: THIS IS NOT SPECIFIED IN THE SPEC. im adding this purely as a placeholder
    private int age;

    //drivers need to follow the driver conditions. FIND THESE IN THE SPEC
    
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

} 