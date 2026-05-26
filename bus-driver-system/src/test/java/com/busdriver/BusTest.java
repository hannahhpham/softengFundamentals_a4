package com.busdriver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

//references used:
//- https://medium.com/@alxkm/writing-unit-tests-with-junit-5-in-java-a-practical-guide-f2b2df05cb03


//notes:
//- test descriptions are present in the display name
//activity 1.1
public class BusTest {
    
    @Test //description: a new Bus object should NOT be created with incorrect parameters
    void shouldThrowConstructorError() {
        Exception error = assertThrows(IllegalArgumentException.class, 
                          () -> new Bus("0", -1, -1.0, "incorrect") );
        System.out.println("Exception thrown: " + error.getMessage());
      
    }

    @Test //description: a new Bus object should  be created with correct parameters
    void shouldCreateBus() {
        // Bus busTest = new Bus("12345678", 20, 20.0, "diesel");
        // assertNotNull(busTest);

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
    
}
