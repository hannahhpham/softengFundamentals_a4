package com.busdriver;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BusRepository {
    // Add (), Update (), Retrieve (), Count () functions
    //busID needs to be unique. bus capacity can't increase during update but can decrease.
     //check spec for more details on restrictions
    
    ArrayList<Bus> busRepo = new ArrayList<Bus>();
    
    //Add bus check all details (if bus id is the same as another etc, )
    public void addBus(String busID, int capacity, double fuelLevel, String fuelType){

        //Check if any bus id in the repo share with the added one
        boolean idTaken = busRepo.stream().anyMatch(bus -> bus.getBusID().equals(busID));

        if (idTaken){
            throw new IllegalArgumentException("BusID already taken");
        }
        else {
            Bus newBus = new Bus(busID, capacity, fuelLevel, fuelType);
            busRepo.add(newBus);
        }
    }

    //Retrieve bus object
    public Bus getBus(String busID){
        //Loops through each obj in bus array
        for (Bus bus : busRepo){
            if (bus.getBusID().equals(busID)){
                return bus;
            }
            else {
                throw new IllegalArgumentException("Bus not found");
            }
        }
        return null;
    }

    //Update based on field
    public void updateBusCapacity(String busID, int capacity){
        //Find bus with id
        //Loops through each obj in bus array
        for (Bus bus : busRepo){
            if (bus.getBusID().equals(busID)){
                //checks if new capacity is larger than old one, if so then we cant update it
                if (capacity > bus.getCapacity()){
                    throw new IllegalArgumentException("Capacity can not be larger than previous capacity");
                }
                else {
                    bus.setCapacity(capacity);
                }
            }
            else {
                throw new IllegalArgumentException("Bus not found");
            }
        }        
    }

    public void updateBusFuelType(String busID, String fuelType){
        //Find bus with id
        //Loops through each obj in bus array
        for (Bus bus : busRepo){
            if (bus.getBusID().equals(busID)){
                bus.setFuelType(fuelType);
            }
            else {
                throw new IllegalArgumentException("Bus not found");
            }
        }        
    }

    public void updateBusFuelLevel(String busID, double fuelLevel){
        //Find bus with id
        //Loops through each obj in bus array
        for (Bus bus : busRepo){
            if (bus.getBusID().equals(busID)){
                bus.setFuelLevel(fuelLevel);
            }
            else {
                throw new IllegalArgumentException("Bus not found");
            }
        }        
    }    

    public int getBusCount(){
        return busRepo.size();
    }


}
