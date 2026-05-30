package com.busdriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class BusRepository {
    // Add (), Update (), Retrieve (), Count () functions
    //busID needs to be unique. bus capacity can't increase during update but can decrease.
     //check spec for more details on restrictions
    
    //Refrences write to file https://www.youtube.com/watch?v=KAWoOgKsQns

    //Parses the file into a list 
    public List<Bus> readBusRepo(){
        String currDirectory = new File(".").getAbsolutePath();
        File file = new File(currDirectory + "/src/main/java/com/busdriver/BusRepository.txt");
        List<Bus> buses = new ArrayList<>();
        //read repos
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            while (true) { 
                try {
                    //store as objs and then append to the arraylist
                    Bus currBus = (Bus) ois.readObject();
                    buses.add(currBus);
                    //loop through objects and notify
                    for (Bus b : buses) {
                        System.out.println("Parsing object BusId: " + b.getBusID());
                    }


                } catch (ClassNotFoundException e) {
                    System.out.print("Class not found: " + e.getMessage());
                }                
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return buses;
    }

     //Get list from driver repository
    List<Bus> busRepo = readBusRepo();    


    //Add bus check all details (if bus id is the same as another etc, )
    public void addBus(Bus bus){
        String currDirectory = new File(".").getAbsolutePath();
        File file = new File(currDirectory + "/src/main/java/com/busdriver/BusRepository.txt");

        //Test if file is found
        try {
            Scanner scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Exception thrown, file not found");
        }

        try {
            //Check if any bus id in the repo share with the added one
            //change this line as arraylist must be read from file whihc hasnt been done yet
            String busId = bus.getBusID();
            boolean idTaken = busRepo.stream().anyMatch(b -> b.getBusID().equals(busId));

            if (idTaken){
                throw new IllegalArgumentException("BusID already taken");
            }
            else {
                //busRepo.add(newBus);
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));){
                    oos.writeObject(bus);
                    oos.close();                    
                } catch (IOException e) {
                    System.out.println("Serialisation Failed: " + e.getMessage());
                }

            }            
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    //Retrieve bus object
    public Bus getBus(String busID){
        //Loops through each obj in bus array
        for (Bus bus : busRepo){
            try {
                if (bus.getBusID().equals(busID)){
                    return bus;
                }
                else {
                    throw new IllegalArgumentException("Bus not found");
                }                
            } catch (Exception e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
        }
        return null;
    }

    //Update based on field
    public void updateBusCapacity(String busID, int capacity){
        //Find bus with id
        //Loops through each obj in bus array
        for (Bus bus : busRepo){
            try {
                if (bus.getBusID().equals(busID)){
                    //checks if new capacity is larger than old one, if so then we cant update it
                    if (capacity > bus.getCapacity()){
                        throw new IllegalArgumentException("Capacity can not be larger than previous capacity");
                    }
                    //if bus capacity is negative
                    else if (0 < capacity){
                        throw new IllegalArgumentException("Capacity can not be negative");
                    }
                    else {
                        bus.setCapacity(capacity);
                    }
                }
                else {
                    throw new IllegalArgumentException("Bus not found");
                }                
            } catch (IllegalArgumentException e) {
                //prints out message from the exceptions 
                System.out.println("Exception caught: " + e.getMessage());
            }
        }        
    }

    public void updateBusFuelType(String busID, String fuelType){
        //Find bus with id
        //Loops through each obj in bus array
        for (Bus bus : busRepo){
            try {
                if (bus.getBusID().equals(busID)){
                    bus.setFuelType(fuelType);
                }
                else {
                    throw new IllegalArgumentException("Bus not found");
                }                
            } catch (Exception e) {
                System.out.println("Exception caught: " + e.getMessage());
            }

        }        
    }

    public void updateBusFuelLevel(String busID, double fuelLevel){
        //Find bus with id
        //Loops through each obj in bus array
        for (Bus bus : busRepo){
            try {
                if (bus.getBusID().equals(busID)){
                    bus.setFuelLevel(fuelLevel);
                }
                else {
                    throw new IllegalArgumentException("Bus not found");
                }                
            } catch (Exception e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
        }        
    }    

    public int getBusCount(){
        return busRepo.size();
    }


}
