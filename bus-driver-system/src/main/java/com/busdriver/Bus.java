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
            throw new IllegalArgumentException("Capacity must be positive.");
        }

        if (fuelLevel >= 0.0) {
            this.fuelLevel = fuelLevel;
        }
        else {
            throw new IllegalArgumentException("Fuel must be empty or positive.");
        }

        if (fuelType == "Diesel" || fuelType == "Hybrid" || fuelType == "Electricity") {
            this.fuelType = fuelType;
        }
        else {
            throw new IllegalArgumentException("Fuel must be either electricity, hybrid, or diesel.");
        }
    }

    //getter methods. this one gets all bus details
    public String getBusDetails() {
        return "f";

    }

    //setter methods with validation

    //check if driver can drive this bus - here or in bus repo?


}
