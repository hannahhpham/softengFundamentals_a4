package com.busdriver;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

//references used:
//- https://medium.com/@alxkm/writing-unit-tests-with-junit-5-in-java-a-practical-guide-f2b2df05cb03
//- https://docs.junit.org/5.0.1/api/org/junit/jupiter/api/Assertions.html 

//activity 1.1
public class BusTest {
    
    @Test //test 1 description: a new Bus object should NOT be created with incorrect parameters
    void shouldThrowErrorEverythingInvalid() {
        Exception error = assertThrows(IllegalArgumentException.class, 
            () -> new Bus("0", -1, -1.0, "incorrect") );
        System.out.println("Exception thrown: " + error.getMessage());
      
    }

    @Test //test 2 description: a new Bus object should  be created with correct parameters
    void shouldCreateBus() {

        Bus result = assertDoesNotThrow(() -> {
            return new Bus("12345678", 20, 20.0, "diesel");
        });

        if (result != null) {
            System.out.println("Bus was successfully created");
        } 
        else {
            System.out.println("Bus was not created");
        }
      
    }

    @Test //test 3 description: bus object shouldn't be created with invalid parameters but correct bus id
    void shouldThrowErrorCapacityInvalid() {
        Exception error = assertThrows(IllegalArgumentException.class, 
            () -> new Bus("88888888", -100, -100.10, "diesal") );
        System.out.println("Exception thrown: " + error.getMessage());
      
    }

    @Test //test 4 description: bus object with invalid id and all other parameters valid should NOT be created
    void shouldThrowErrorBusIDInvalid() {
        Exception error = assertThrows(IllegalArgumentException.class, 
            () -> new Bus("incorrectID", 200, 155.55, "hybrid") );
        System.out.println("Exception thrown: " + error.getMessage());
    }

    @Test //test 5 description: capacity should not update if new capacity is larger than old capacity
    void shouldThrowErrorCapacityLarger() {
        Bus busTest = new Bus("99999999", 75, 50, "Electricity");

        Exception error = assertThrows(IllegalArgumentException.class,
            () -> busTest.setCapacity(76));
        System.out.println("Exception thrown: " + error.getMessage());
    }

    @Test //test 6 description: capacity should update if the new capacity is smaller than or equal to old capacity
    void shouldUpdateCapacity() {
        Bus busTest = new Bus("11223344", 100, 99.44, "hybrid");
        
        busTest.setCapacity(99);

        assertEquals(99, busTest.getCapacity());
        System.out.println("Capacity was successfully updated");
        
    }

    @Test //test 7 description: capacity should update if the new capacity is smaller than or equal to old capacity
    void shouldUpdateCapacity2() {
        Bus busTest = new Bus("11223344", 200, 99.44, "hybrid");
        
        busTest.setCapacity(200);

        assertEquals(200, busTest.getCapacity());
        System.out.println("Capacity was kept the same.");
        
    }

    //PLEASE NOTE: the following unit tests use the Driver class.

    @Test //test 8 description: drivers less than 50 should  be able to drive buses with capacity 50 or more.
    void shouldApproveDriverAge() {
        Driver driverTest = new Driver("29-1111-AZ", "Sally", 10, "PublicTransport",  
            "101 | Collins St | Melbourne | Victoria | Australia" , "01-05-2000", 49);

        Bus busTest = new Bus("11223344", 50, 99.44, "hybrid");
        
        assertTrue(busTest.checkDriverAge(driverTest));
        System.out.println("Bus driver aged 49 is able to drive a bus with capacity 50");

    }

    @Test //test 9 description: drivers older than 50 should not be able to drive buses with capacity 50 or more.
    void shouldApproveDriverAge2() {
        Driver driverTest = new Driver("39-1111-AZ", "Sally", 10, "PublicTransport",  
            "101 | Collins St | Melbourne | Victoria | Australia" , "01-05-2000", 50);

        Bus busTest = new Bus("11223344", 52, 99.44, "hybrid");
        
        assertTrue(busTest.checkDriverAge(driverTest));
        System.out.println("Bus driver aged 50 is able to drive a bus with capacity 52");

    }

    @Test //test 10 description: drivers that are 50 should not be able to drive buses with capacity 50 or more.
    void shouldNotApproveDriverAge() {
        Driver driverTest = new Driver("49-1111-AZ", "Sally", 10, "PublicTransport",  
            "101 | Collins St | Melbourne | Victoria | Australia" , "01-05-2000", 51);

        Bus busTest = new Bus("11223344", 51, 99.44, "hybrid");
        
        assertFalse(busTest.checkDriverAge(driverTest));
        System.out.println("Bus driver aged 51 is not able to drive a bus with capacity 50");

    }

    @Test //test 11 description: checks if drivers with 5 years of experience are approved to drive electric buses
    void shouldApproveDriverExp() {
        Driver driverTest = new Driver("49-1a11-AZ", "Sally", 5, "PublicTransport",  
            "101 | Collins St | Melbourne | Victoria | Australia" , "01-05-2000", 51);

        Bus busTest = new Bus("11223344", 51, 99.44, "electricity");
        
        assertTrue(busTest.checkExperience(driverTest));
        System.out.println("Bus driver with 5 years of experience is able to drive the electric bus");
    }

    @Test //test 12 description: checks if drivers with less than 5 years of experience are not approved to drive electric buses
    void shouldApproveDriverExp2() {
        Driver driverTest = new Driver("49-1b11-AZ", "Sally", 6, "PublicTransport",  
            "101 | Collins St | Melbourne | Victoria | Australia" , "01-05-2000", 51);

        Bus busTest = new Bus("11223344", 51, 99.44, "electricity");
        
        assertTrue(busTest.checkExperience(driverTest));
        System.out.println("Bus driver with 6 years of experience is able to drive the electric bus");
    }

    @Test //test 13 description: checks if drivers with more than 5 years of experience are approved to drive electric buses
    void shouldNotApproveDriverExp() {
        Driver driverTest = new Driver("49-1c11-AZ", "Sally", 4, "PublicTransport",  
            "101 | Collins St | Melbourne | Victoria | Australia" , "01-05-2000", 50);

        Bus busTest = new Bus("11223344", 51, 99.44, "electricity");
        
        assertFalse(busTest.checkExperience(driverTest));
        System.out.println("Bus driver with 4 years of experience is not able to drive the electric bus");
    }

    @Test //test 14 description: checks if drivers with Heavy licenses are approved to drive electric buses
    void shouldApproveHeavyLicense() {
        Driver driverTest = new Driver("49-1111-AA", "Sally", 6, "Heavy",  
            "101 | Collins St | Melbourne | Victoria | Australia" , "01-05-2000", 50);

        Bus busTest = new Bus("11223344", 51, 99.44, "electricity");
        
        assertTrue(busTest.checkLicense(driverTest));
        System.out.println("Bus driver with Heavy license is able to drive electric bus");
    }

    @Test //test 15 description: checks if drivers with Public Transport licenses are approved to drive hybrid buses
    void shouldApprovePublicTransportLicense() {
        Driver driverTest = new Driver("49-1111-AY", "Sally", 6, "PublicTransport",  
            "101 | Collins St | Melbourne | Victoria | Australia" , "01-05-2000", 50);

        Bus busTest = new Bus("11223344", 51, 99.44, "electricity");
        
        assertTrue(busTest.checkLicense(driverTest));
        System.out.println("Bus driver with Public Transport license is able to drive hybrid bus");
    }

    @Test //test 16 description: checks if drivers with Light licenses are NOT approved to drive electric buses
    void shouldNotApproveLightLicense() {
        Driver driverTest = new Driver("49-1111-AB", "Sally", 6, "Light",  
            "101 | Collins St | Melbourne | Victoria | Australia" , "01-05-2000", 50);

        Bus busTest = new Bus("11223344", 51, 99.44, "electricity");
        
        assertFalse(busTest.checkLicense(driverTest));
        System.out.println("Bus driver with Light license is not able to drive electric bus");
    }

    @Test //test 17 description: checks if drivers with Medium licenses are NOT approved to drive hybrid buses
    void shouldNotApproveMediumLicense() {
        Driver driverTest = new Driver("49-1111-AC", "Sally", 6, "Medium",  
            "101 | Collins St | Melbourne | Victoria | Australia" , "01-05-2000", 50);

        Bus busTest = new Bus("11223344", 51, 99.44, "hybrid");
        
        assertFalse(busTest.checkLicense(driverTest));
        System.out.println("Bus driver with Medium license is not able to drive hybrid bus");
    }


    
}
