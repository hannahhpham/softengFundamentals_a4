package com.busdriver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class BusRepository {
    // Add (), Update (), Retrieve (), Count () functions
    //busID needs to be unique. bus capacity can't increase during update but can decrease.
     //check spec for more details on restrictions
    
     //writing to files: https://www.youtube.com/watch?v=ScUJx4aWRi0&t=267s
    //Reading comma seperated files: https://stackoverflow.com/questions/10960213/how-can-i-read-comma-separated-values-from-a-text-file-in-java

    //Get list from driver repository
    List<Bus> busRepo = readBusRepo();    

    //Parses the file into a list 
    public List<Bus> readBusRepo(){
        String currDirectory = new File(".").getAbsolutePath();
        File file = new File(currDirectory + "/src/main/java/com/busdriver/BusRepository.txt");
        List<Bus> buses = new ArrayList<>();

        if (file.length() == 0 || file.length() < 0) {
            return new ArrayList<>();
        }        
        //read repos
        //add to line until end
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(","); 
                //if not enough fields so it wont cause index out of range erorr
                if (splitLine.length != 4){
                }
                else {
                    String currBusId = splitLine[0];
                    Integer currBusCapacity = Integer.parseInt(splitLine[1]);
                    Double currBusFuelLevel = Double.parseDouble(splitLine[2]);
                    String currBusFuelType = splitLine[3];
                    Bus newBus = new Bus(currBusId, currBusCapacity, currBusFuelLevel, currBusFuelType);
                    buses.add(newBus);                    
                }
            }                
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading repo: " + e.getMessage());
        }
        return buses;
    }

    //save bus when updating
    public void saveBusRepo(){
        String currDirectory = new File(".").getAbsolutePath();
        File file = new File(currDirectory + "/src/main/java/com/busdriver/BusRepository.txt");        

        try (PrintWriter writer = new PrintWriter(file)){
            for (Bus currBus : busRepo){
                writer.println(currBus.getBusID() + "," + currBus.getCapacity() + "," + currBus.getFuelLevel() + "," + currBus.getFuelType());
            }
        } catch (IOException e){
            System.out.println("Failed to save buses: " + e.getMessage());
        }
    }

    //Add bus check all details (if bus id is the same as another etc, )
    public void addBus(Bus bus){
        boolean idTaken = busRepo.stream().anyMatch(b -> b.getBusID().equals(bus.getBusID()));
        if (idTaken){
            throw new IllegalArgumentException("BusID already taken");
        }

        busRepo.add(bus);

        String currDirectory = new File(".").getAbsolutePath();
        File file = new File(currDirectory + "/src/main/java/com/busdriver/BusRepository.txt");        

        //busRepo.add(newBus);
        try (PrintWriter writer = new PrintWriter(file);){
            for (Bus currBus : busRepo){
                writer.println(currBus.getBusID() + "," + currBus.getCapacity() + "," + currBus.getFuelLevel() + "," + currBus.getFuelType());
            }
        } catch (IOException e) {
            System.out.println("Failed to add buses: " + e.getMessage());
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
                    else if (0 >= capacity){
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
            } catch (Exception e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
        }    
        throw new IllegalArgumentException("Bus not found");
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
            } catch (Exception e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
        }        
        throw new IllegalArgumentException("Bus not found");

    }    

    public int getBusCount(){
        return this.busRepo.size();
    }


}
