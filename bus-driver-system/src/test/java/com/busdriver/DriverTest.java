package com.busdriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DriverTest {
    @Test // test 1: a valid Driver ID is created 
    void shouldCreateDriverID() {
        Driver result = assertDoesNotThrow(() -> {
            return new Driver (
                "23ab#$XYZA",
                "John Doe",
                5,
                "Heavy",
                "123 | Main Street | Melbourne | VIC | Australia",
                "01-01-1980",
                55
            ); 
        });

       assertNotNull(result);
       assertEquals("23ab#$XYZA", result.getDriverID());
       assertEquals("John Doe", result.getName());
       assertEquals(5, result.getExperienceYears());
       assertEquals("Heavy", result.getLicenseType());

       System.out.println("Test 1 result:");
       System.out.println("Test passed: Valid driver ID was created successfully.");
       System.out.println("Driver ID: " + result.getDriverID());
       System.out.println();
    }

    @Test // test 2: an invalid Driver ID is not created
    void shouldThrowErrorInvalidDriverID() {
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new Driver (
                "invalidID",
                "Jane Doe",
                3,
                "Light",
                "456 | Elm Street | Melbourne | VIC | Australia",
                "02-02-1990",
                55
            );
        });

        System.out.println("Test 2 result:");
        System.out.println("Exception thrown: " + error.getMessage());
        System.out.println();
    }     

    @Test // test 3: boundary value test for driver ID length (exactly 10 characters, and exactly 2 special characters)
    void driverIDShouldPass() {
        Driver result = assertDoesNotThrow(() -> {
            return new Driver (
                "24ab!@CDXY",
                "Alice Smith",
                10,
                "Medium",
                "789 | Oak Street | Melbourne | VIC | Australia",
                "03-03-1975",
                55
            );
        });

        assertNotNull(result);
        System.out.println("Test 3 result:");
        System.out.println("Test passed: Valid driver ID was created successfully.");
        System.out.println("Driver ID: " + result.getDriverID());
        System.out.println();
    }

    @Test // test 4: Valid address should pass
    void shouldCreateDriverWithValidAddress() {
        Driver result = assertDoesNotThrow(() -> {
            return new Driver (
                "26ef!@GHAB",
                "Diana Evans",
                8,
                "PublicTransport",
                "321 | Pine Street | Melbourne | VIC | Australia",
                "04-04-1985",
                55
            );
        });

        assertNotNull(result);
        System.out.println("Test 4 result:");
        System.out.println("Test passed: Valid address was accepted.");
        System.out.println("Address: " + result.getAddress());
        System.out.println();
    }

    @Test // test 5: Invalid address should throw an error
    void shouldThrowErrorInvalidAddress() {
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new Driver (
                "27gh#$IJCD",
                "Ethan Foster",
                12,
                "Heavy",
                "Invalid Address",
                "05-05-1970",
                55
            );
        });

        System.out.println("Test 5 result:");
        System.out.println("Exception thrown: " + error.getMessage());
        System.out.println();
    }

    @Test // test 6: Very short address should pass (edge case)
    void shouldCreateDriverWithShortAddress() {
        Driver result = assertDoesNotThrow(() -> {
            return new Driver (
                "28ij!@KLEF",
                "Fiona Green",
                15,
                "Medium",
                "1 | Main Street | Melbourne | VIC | Australia",
                "06-06-1965",
                55
            );
        });

        assertNotNull(result);
        System.out.println("Test 6 result:");
        System.out.println("Test passed: Valid address was created successfully.");
        System.out.println("Address: " + result.getAddress());
        System.out.println();
    }

    @Test // test 7: Valid birth date should pass
    void shouldCreateDriverWithValidBirthDate() {
        Driver result = assertDoesNotThrow(() -> {
            return new Driver (
                "29kl#$MNGH",
                "George Harris",
                20,
                "Light",
                "654 | Cedar Street | Melbourne | VIC | Australia",
                "07-07-1955",
                55
            );
        });

        System.out.println("Test 7 result:");
        System.out.println("Test passed: Valid birth date was accepted.");
        System.out.println("Birth Date: " + result.getBirthDate());
        System.out.println();
    }
    
    @Test // test 8: Invalid birthdate should throw an error
    void shouldThrowErrorInvalidBirthDate() {
          Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new Driver (
                "32mn!@OPIJ",
                "Hannah Johnson",
                25,
                "PublicTransport",
                "987 | Spruce Street | Melbourne | VIC | Australia",
                "invalidDate",
                55
            );
        });

        System.out.println("Test 8 result:");
        System.out.println("Exception thrown: " + error.getMessage());
        System.out.println();
    }

    @Test // test 9: Edge case for birthdate (e.g. very old birthdate) should pass
    void shouldCreateDriverWithOldBirthDate() {
          Driver result = assertDoesNotThrow(() -> {
            return new Driver (
                "34op#$QRKL",
                "Ian King",
                30,
                "Heavy",
                "321 | Birch Street | Melbourne | VIC | Australia",
                "01-01-1900",
                55
            );
        });

        assertNotNull(result);

        System.out.println("Test 9 result:");
        System.out.println("Test passed: Valid birth date was accepted.");
        System.out.println("Birth Date: " + result.getBirthDate());
        System.out.println();
    }

    @Test // Test 10: Driver with 10 or less years of experience can change license
    void shouldAllowLicenseChange() {
          Driver driver = new Driver(
              "35qr!@STMN",
              "Jack Lee",
              10,
              "Light",
              "123 | Maple Street | Melbourne | VIC | Australia",
              "08-08-1980",
              55
          );

        assertDoesNotThrow(() -> {
            driver.updateDriverInfo(
                10,
                "Heavy",
                "456 | New Street | Melbourne | VIC | Australia",
                "08-08-1980"
            );
        });

        assertEquals("Heavy", driver.getLicenseType());

        System.out.println("Test 10 result:");
        System.out.println("Test passed: License was changed successfully.");
        System.out.println("New License Type: " + driver.getLicenseType());
        System.out.println();
    }

    @Test // Test 11: Driver with more than 10 years of experience cannot change license
    void showErrorLicenseChange() {
        Driver driver = new Driver(
            "36st#$UVOQ",
            "Liam Martin",
            11,
            "Medium",
            "456 | Walnut Street | Melbourne | VIC | Australia",
            "09-09-1975",
            55
        );

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            driver.updateDriverInfo(
                11,
                "Heavy",
                "456 | Walnut Street | Melbourne | VIC | Australia",
                "09-09-1975"
            );
        });

        System.out.println("Test 11 result:");
        System.out.println("Exception thrown: " + error.getMessage());
        System.out.println();
    }

    @Test // test 12: Driver with exactly 10 years of experience should be able to change license
    void shouldAllowLicenseChangeAt10Years() {
        Driver driver = new Driver(
            "37uv!@WXRS",
            "Mia Wilson",
            10,
            "PublicTransport",
            "789 | Willow Street | Melbourne | VIC | Australia",
            "10-10-1985",
            55
        );

        assertDoesNotThrow(() -> {
            driver.updateDriverInfo(
                10,
                "Medium",
                "789 | Willow Street | Melbourne | VIC | Australia",
                "10-10-1985"
            );
        });

        assertEquals("Medium", driver.getLicenseType());
        System.out.println("Test 12 result:");
        System.out.println("Test passed: License was changed successfully.");
        System.out.println("New License Type: " + driver.getLicenseType());
        System.out.println();
    }

    @Test // test 13: Updating non-immutable fields should pass (eg. address)
    void shouldUpdateAddress() {
        Driver driver = new Driver (
            "38wx#$YZTU",
            "Noah Taylor",
            5,
            "Heavy",
            "123 | Old Street | Melbourne | VIC | Australia",
            "01-01-1980",
            55
        );

        driver.updateDriverInfo (
            5,
            "Heavy",
            "456 | New Street | Melbourne | VIC | Australia",
            "01-01-1980"
        );

        assertEquals("456 | New Street | Melbourne | VIC | Australia", driver.getAddress());
        System.out.println("Test 13 result:");
        System.out.println("Test passed: Driver address was updated successfully.");
        System.out.println("New Address: " + driver.getAddress());
        System.out.println();
    }

    @Test // test 14: Changing driver name should fail (immutable field)
    void shouldNotUpdateName() {
        Driver driverTest = new Driver (
        "49mn!@PQRS",
        "John Doe",
        5,
        "Heavy",
        "123 | Main Street | Melbourne | VIC | Australia",
        "01-01-1980",
        55
        );

        driverTest.updateDriverInfo(
        6,
        "Medium",
        "456 | New Street | Melbourne | VIC | Australia",
        "02-02-1980"
        );

       assertEquals("John Doe", driverTest.getName());

       assertEquals(6, driverTest.getExperienceYears());
       assertEquals("Medium", driverTest.getLicenseType());
       assertEquals("456 | New Street | Melbourne | VIC | Australia", driverTest.getAddress());
       assertEquals("02-02-1980", driverTest.getBirthDate());

       System.out.println("Test 14 result:");
       System.out.println("Test failed: Driver name was not updated.");
       System.out.println("Driver Name: " + driverTest.getName());
       System.out.println();
    }

    @Test // test 15: driver ID is not modified (immutable field)
    void shouldNotUpdateID() {
        Driver driverTest = new Driver (
        "45yz#$ABCD",
        "John Doe",
        5,
        "Heavy",
        "123 | Main Street | Melbourne | VIC | Australia",
        "01-01-1980",
        55
        );

        driverTest.updateDriverInfo(
        6,
        "Medium",
        "456 | New Street | Melbourne | VIC | Australia",
        "02-02-1980"
        );

       assertEquals("45yz#$ABCD", driverTest.getDriverID());

       assertEquals(6, driverTest.getExperienceYears());
       assertEquals("Medium", driverTest.getLicenseType());
       assertEquals("456 | New Street | Melbourne | VIC | Australia", driverTest.getAddress());
       assertEquals("02-02-1980", driverTest.getBirthDate());

       System.out.println("Test 15 result:");
       System.out.println("Test failed: Driver ID was not updated.");
       System.out.println("Driver ID: " + driverTest.getDriverID());
       System.out.println();
    }

    @Test // test 16: Invalid ID where last two characters are in lowercase
    // edge case lol
    void shouldRejectInvalidID() {
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new Driver (
                "49mn!@PQrs",
                "John Doe",
                5,
                "Heavy",
                "123 | Main Street | Melbourne | VIC | Australia",
                "01-01-1980",
                55
            );
        });

        System.out.println("Test 16 result:");
        System.out.println("Exception thrown: " + error.getMessage());
        System.out.println();
    }

}

    

            



                    
