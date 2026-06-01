package com.busdriver;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
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

        if (file.length() == 0) {
            return new ArrayList<>();
        }        
        //read repos
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            while (true) {
                try {
                    try {
                        //store as objs and then append to the arraylist
                        Bus currBus = (Bus) ois.readObject();
                        buses.add(currBus);
                        //loop through objects and notify
                        for (Bus b : buses) {
                            System.out.println("Parsing object BusId: " + b.getBusID());
                        }
                        //end of file reached
                    } catch (EOFException e) {
                        break;
                    }                      
                    
            } catch (ClassNotFoundException e) {
                System.out.print("Class not found: " + e.getMessage());
            }                        

            }                
        } catch (IOException e) {
            System.out.println("Error reading repo: " + e.getMessage());
        }
        return buses;
    }

     //Get list from driver repository
    List<Bus> busRepo = readBusRepo();    


    //save bus when updating
    public void saveBusRepo(){
        String currDirectory = new File(".").getAbsolutePath();
        File file = new File(currDirectory + "/src/main/java/com/busdriver/BusRepository.txt");        

        //busRepo.add(newBus);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));){
            for (Bus currBus : busRepo){
                oos.writeObject(currBus);
            }
        } catch (IOException e) {
            System.out.println("Serialisation Failed: " + e.getMessage());
        }
    }

    //Add bus check all details (if bus id is the same as another etc, )
    public void addBus(Bus bus){
        List<String> fuelTypes = List.of("diesel", "electric", "hybrid");
        boolean idTaken = busRepo.stream().anyMatch(b -> b.getBusID().equals(bus.getBusID()));
        if (idTaken){
            throw new IllegalArgumentException("BusID already taken");
        }

        busRepo.add(bus);

        String currDirectory = new File(".").getAbsolutePath();
        File file = new File(currDirectory + "/src/main/java/com/busdriver/BusRepository.txt");        

        //busRepo.add(newBus);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));){
            for (Bus currBus : busRepo){
                oos.writeObject(currBus);
            }
        } catch (IOException e) {
            System.out.println("Serialisation Failed: " + e.getMessage());
        }

    }

    //Retrieve bus object
    public Bus getBus(String busID){
        //Loops through each obj in bus array
        for (Bus bus : busRepo){
            if (bus.getBusID().equals(busID)){
                return bus;           
        }
       }
        throw new IllegalArgumentException("Bus not found");
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
                    else if (0 > capacity){
                        throw new IllegalArgumentException("Capacity can not be negative");
                    }
                    else {
                        bus.setCapacity(capacity);
                        saveBusRepo();
                        return; 
                    }
                }
            
            } catch (IllegalArgumentException e) {
                //prints out message from the exceptions 
                System.out.println("Exception caught: " + e.getMessage());
            }
        }        
        throw new IllegalArgumentException("Bus not found");

    }

    public void updateBusFuelType(String busID, String fuelType){
        //Find bus with id
        //Loops through each obj in bus array
        for (Bus bus : busRepo){
            try {
                if (bus.getBusID().equals(busID)){
                    bus.setFuelType(fuelType);
                    saveBusRepo();
                    return;                     
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
                    saveBusRepo();
                    return;                     
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
        return this.busRepo.size();
    }


}
