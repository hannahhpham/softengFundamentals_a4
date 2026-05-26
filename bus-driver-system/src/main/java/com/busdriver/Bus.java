package com.busdriver;

//references:
//“How to Throw Exceptions in Java,” Rollbar, Nov. 29, 2025. https://rollbar.com/guides/java/how-to-throw-exceptions-in-java/ (accessed May 22, 2026).
//helpful to store json data https://stackoverflow.com/questions/58988210/storing-data-in-json-file-using-java

public class Bus {
    private String busID; 
    private int capacity; 
    private double fuelLevel; 
    private String fuelType; // Diesel, Hybrid, Electricity 

    //constructor
    public Bus(String busID, int capacity, double fuelLevel, String fuelType) {
        //constructor checks if data follows validation rules and only then adds it.

        if (busID.matches("[0-9]{8}")) { //regex check
            this.busID = busID;
        }
        else {
            throw new IllegalArgumentException("BusID must be 8 digits and contain only numbers.");
        }

        if (capacity > 0) {
            this.capacity = capacity;
        }
        else {
            throw new IllegalArgumentException("Capacity must be positive and less than/equal to current capacity.");
        }

        if (fuelLevel >= 0.0) {
            this.fuelLevel = fuelLevel;
        }
        else {
            throw new IllegalArgumentException("Fuel must be empty or positive.");
        }

        if (fuelType.toLowerCase().equals("diesel") || 
        fuelType.toLowerCase().equals("hybrid")  || 
        fuelType.toLowerCase().equals("electricity") ) {
            
            this.fuelType = fuelType;
        }
        else {
            throw new IllegalArgumentException("Fuel must be either electricity, hybrid, or diesel.");
        }
    }

    //TODO: get all bus details? or leave this to busRepo
    // public String getBusDetails() {
    //     return "f";

    // }
    
    public String getBusID() {
        return this.busID;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public double getFuelLevel() {
        return this.fuelLevel;
    }

    public String getFuelType() {
        return this.fuelType;
    }

    //setter methods with validation
    //TODO: decide whether we can update all bus' details or just bit by bit
    
    //professor mentioned that not changing the bus id after object construction was ok
    // public void setBusID(String busID) {
    //     if (busID.matches("[0-9]{8}")) {
    //         this.busID = busID;
    //     }
    //     else {
    //         throw new IllegalArgumentException("busID must be exactly 8 numeric characters");
    //     }
    // }

    public void setCapacity(int capacity) {
        if (capacity > 0 && capacity <= this.capacity) {
            this.capacity = capacity;
        }
        else {
            throw new IllegalArgumentException("Capacity must be positive and less than/equal to current capacity.");
        }
    }

    public void setFuelLevel(double fuelLevel) {
        if (fuelLevel >= 0.0) {
            this.fuelLevel = fuelLevel;
        }
        else {
            throw new IllegalArgumentException("Fuel level must be a non-negative number.");
        }
    }

    public void setFuelType(String fuelType) {
        
        if (fuelType.toLowerCase().equals("diesel") || 
        fuelType.toLowerCase().equals("hybrid")  || 
        fuelType.toLowerCase().equals("electricity") ) {
            
            this.fuelType = fuelType;

        }
        else {
            throw new IllegalArgumentException("Fuel type must be either Diesel, Hybrid, or Electricity.");
        }
    }

    //special methods as outlines in restrictions
    public boolean checkDriverAge(Driver driver) {
        if (driver.getAge() > 50 && this.capacity >= 50) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkExperience(Driver driver) {
        if (driver.getExperienceYears() < 5 && this.fuelType.toLowerCase().equals("electricity")) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkLicense(Driver driver) {
        if (this.fuelType.toLowerCase().equals("electricity") || 
        this.fuelType.toLowerCase().equals("hybrid")) {
                
            if (driver.getLicenseType().toLowerCase().equals("heavy") || 
                driver.getLicenseType().toLowerCase().equals("publictransport")) {
                    
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }


}
