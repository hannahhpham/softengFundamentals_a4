package com.busdriver;

import org.junit.jupiter.api.Test;

public class BusRepositoryTest {
    //Instaniate the Repository
    BusRepository busRepo = new BusRepository();

    @Test //Test 1 Add buses with valid id (8 Characters) to the repository
    void AddValidBus(){
        System.out.println("Running Test 1, adding a valid bus");
        Bus b1 = new Bus("12345678", 20, 20.0, "diesel");    
        try {
            busRepo.addBus(b1);            
        } catch (Exception e) {
            System.out.println("Test 1 Failed");
        }
    }
    @Test //Fill in repo with buses
    void AddValidBus1(){
        System.out.println("Running Test 1.1, adding a valid bus");
        Bus b1 = new Bus("20262025", 50, 45.0, "hybrid");    
        try {
            busRepo.addBus(b1);            
        } catch (Exception e) {
            System.out.println("Test 1.1 Failed");
        }
    }    

}
