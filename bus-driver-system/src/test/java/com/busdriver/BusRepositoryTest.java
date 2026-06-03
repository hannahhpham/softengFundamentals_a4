package com.busdriver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusRepositoryTest {

    BusRepository busRepo;
    //clean the repo after each test

    @BeforeEach
    void clean(){
        busRepo = new BusRepository();
        busRepo.busRepo.clear();
        busRepo.saveBusRepo();
    }

    @Test //Test 1 Add buses with valid id (8 Characters) to the repository and check it has valid details
    void AddValidBus(){
        System.out.println("Running Test 1");
        System.out.println("Adding a valid bus with ID: 87654321, capacity 50, fuelLevel: 20.0, fuelType: diesel");
        Bus b1 = new Bus("87654321", 50, 20.0, "diesel");    
        try {
            busRepo.addBus(b1);            
            Bus retrieveBus = busRepo.getBus(b1.getBusID());
            System.out.println("Retrieving bus with ID: 87654321");
            System.out.println("BUS ID: " + retrieveBus.getBusID() + ", capacity: " + retrieveBus.getCapacity() + 
            ", fuelLevel: " + retrieveBus.getFuelLevel() + ", fuelType: " + retrieveBus.getFuelType() );            
            System.out.println("Test 1 Passed: Added and retrieved bus\n");
        } catch (Exception e) {
            System.out.println("Test 1 Failed\n");
        }
    }

    @Test //Test 2 add a bus with incorrect details and reject it from the repository
    void addInvalidBus(){
        System.out.println("Running Test 2");
        System.out.println("Creating an invalid bus with ID: 8765432, capacity: -50, fuelLevel: -20.0, fuelType: fiesel");
        try {
            Bus b2 = new Bus("8765432", -50, -20, "fiesel");    
            busRepo.addBus(b2);
        } catch (Exception e) {
            System.out.println("Test 2 Passed: Bus was throw out\n");
        }
    
    }

    @Test //3 add bus and update its details
    void updateBusDetails(){
        //
        System.out.println("Test 3 running:");
        try {
            //Create new bus 
            Bus newBus = new Bus("04052016", 52, 25, "diesel");
            busRepo.addBus(newBus);
            System.out.println("Bus ID: " + newBus.getBusID() + ", capacity: " + newBus.getCapacity() + ", fuelLevel: " + newBus.getFuelLevel() + ", fuelType: " + newBus.getFuelType());
            try {
                busRepo.updateBusCapacity(newBus.getBusID(), 40);
                busRepo.updateBusFuelLevel(newBus.getBusID(), 15);
                busRepo.updateBusFuelType(newBus.getBusID(), "hybrid");
                System.out.println("Updating capacity to 40");
                System.out.println("Updating fuel level to 15");
                System.out.println("Updating fuel type to hybrid");

                Bus b3Updated = busRepo.getBus("04052016");
                System.out.println("UPDATED DETAILS: ");            
                System.out.println("Bus ID: " + b3Updated.getBusID() + ", capacity: " + b3Updated.getCapacity() + ", fuelLevel: " + b3Updated.getFuelLevel() + ", fuelType: " + b3Updated.getFuelType());
                System.out.println("Test 3 passed\n");
            } catch (Exception e) {
                System.out.println("Test 3 failed, unable to update bus\n");

            }

        } catch (Exception e) {
            System.out.println("Test 3 failed, unable to retrieve bus: " + e.getMessage());
        }
    }

    @Test //4 add 2 buses, check the count and add 2 more buses

    void busRepoCount(){
        System.out.println("Test 4 running");

        try {
            Bus b1 = new Bus("04121999", 22, 11, "diesel");
            Bus b2 = new Bus("19962608", 44, 15, "hybrid");
            busRepo.addBus(b1);
            busRepo.addBus(b2);
            System.out.println("Added two initial buses repo count: " + busRepo.getBusCount());

        } catch (Exception e) {
            System.out.println("Could not add initial buses");
        }

            try {
                //Adding 2 more buses
                Bus b3 = new Bus("20030901", 12, 5, "electricity");
                Bus b4 = new Bus("01121997", 100, 100, "hybrid");
                busRepo.addBus(b3);
                busRepo.addBus(b4);

                System.out.println("Added two more buses repo count: " + busRepo.getBusCount() +"\n");
            } catch (Exception e) {
                System.out.println("Error when adding more buses\n");
            }        

    }



}
