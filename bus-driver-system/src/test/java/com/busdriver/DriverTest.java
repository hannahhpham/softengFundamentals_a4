package com.busdriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// TEST ISSUES
// 1. Tests for address is not in the correct format, so I will need to correct that 
//    and potentially fix the driver.java code to match the correct format.
// 2. Tests for immutable fields is also not in the correct format, and should
//    match tests 1-3 in terms of updating unimmutable fields. (eg. remove setter methods)
// 3. Tests will need to be run, ensuring correct outputs
// 4. I need to add one more test to reach the required 15 test threshold, which will
//    most likely just be a normal validation check   

//activity 1.1
public class DriverTest {
     @Test // test 1: a valid Driver ID is created 
     void shouldCreateDriverID() {
         Driver result = assertDoesNotThrow(() -> {
                return new Driver(
                "23ab#$XYZA",
                "John Doe",
                5,
                "Heavy",
                "123|Main Street|Melbourne|VIC|Australia",
                "01-01-1980",
                55
            );
        });

       assertNotNull(result);
       assertEquals("23ab#$XYZA", result.getDriverID());
       assertEquals("John Doe", result.getName());
       assertEquals(5, result.getExperienceYears());
       assertEquals("Heavy", result.getLicenseType());

      System.out.println("Test 1 results:");
      System.out.println("Test passed: Valid Driver was created successfully.");
      System.out.println("Driver ID: " + result.getDriverID());
     }

     @Test // test 2: an invalid Driver ID is not created
     void shouldThrowErrorInvalidDriverID() {
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new Driver(
                "invalidID",
                "Jane Doe",
                3,
                "Light",
                "456|Elm Street|Melbourne|VIC|Australia",
                "02-02-1990",
                55
            );
        });

        System.out.println("Exception thrown: " + error.getMessage());
    }     

     @Test // test 3: boundary value test for driver ID length (exactly 10 characters, and exactly 2 special characters)
     void driverIDShouldPass() {
        Driver result = assertDoesNotThrow(() -> {
            return new Driver(
                "24ab!@CDXY",
                "Alice Smith",
                10,
                "Medium",
                "789|Oak Street|Melbourne|VIC|Australia",
                "03-03-1975",
                55
            );
        });

        assertNotNull(result);
     }

     @Test // test 4: Valid address should pass
        void shouldCreateDriverWithValidAddress() {
        Driver result = assertDoesNotThrow(() -> {
            return new Driver(
                "26ef!@GHAB",
                "Diana Evans",
                8,
                "PublicTransport",
                "321|Pine Street|Melbourne|VIC|Australia",
                "04-04-1985",
                55
            );
        });

        assertNotNull(result);
        assertEquals("321|Pine Street|Melbourne|VIC|Australia", result.getAddress());
        }

      @Test // test 5: Invalid address should throw an error
        void shouldThrowErrorInvalidAddress() {
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new Driver(
                "27gh#$IJCD",
                "Ethan Foster",
                12,
                "Heavy",
                "Invalid Address",
                "05-05-1970",
                55
            );
        });

        System.out.println("Exception thrown: " + error.getMessage());
        }

        @Test // test 6: Very short address should pass (edge case)
        void shouldCreateDriverWithShortAddress() {
        Driver result = assertDoesNotThrow(() -> {
            return new Driver(
                "28ij!@KLEF",
                "Fiona Green",
                15,
                "Medium",
                "1|Main Street|Melbourne|VIC|Australia",
                "06-06-1965",
                55
            );
        });

        assertNotNull(result);
         }

         @Test // test 7: Valid birthdate should pass
            void shouldCreateDriverWithValidBirthdate() {
        Driver result = assertDoesNotThrow(() -> {
            return new Driver(
                "29kl#$MNGH",
                "George Harris",
                20,
                "Light",
                "654|Cedar Street|Melbourne|VIC|Australia",
                "07-07-1955",
                55
            );
        });

        assertNotNull(result);
        assertEquals("07-07-1955", result.getBirthdate());
            }
    
        @Test // test 8: Invalid birthdate should throw an error
          void shouldThrowErrorInvalidBirthdate() {
          Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new Driver(
                "32mn!@OPIJ",
                "Hannah Johnson",
                25,
                "PublicTransport",
                "987|Spruce Street|Melbourne|VIC|Australia",
                "invalidDate",
                55
            );
          });

        System.out.println("Exception thrown: " + error.getMessage());
            }

        @Test // test 9: Edge case for birthdate (e.g., very old birthdate) should pass
            void shouldCreateDriverWithOldBirthdate() {
          Driver result = assertDoesNotThrow(() -> {
            return new Driver(
                "34op#$QRKL",
                "Ian King",
                30,
                "Heavy",
                "321|Birch Street|Melbourne|VIC|Australia",
                "01-01-1900",
                55
            );
        });

        assertNotNull(result);
             }

        @Test // Test 10: Driver with 10 or less years of experience can change lisense
          void shouldAllowLicenseChange() {
          Driver driver = new Driver(
              "35qr!@STMN",
              "Jack Lee",
              10,
              "Light",
              "123|Maple Street|Melbourne|VIC|Australia",
              "08-08-1980",
              55
          );

          assertDoesNotThrow(() -> {
              driver.updateDriverInfo(
                  10,
                  "Heavy",
                  "456|New Street|Melbourne|VIC|Australia",
                  "08-08-1980"
              );
          });

        assertEquals("Heavy", driver.getLicenseType());
            }

        @Test // Test 11: Driver with more than 10 years of experience cannot change license
            void showErrorLicenseChange() {
        Driver driver = new Driver(
            "36st#$UVOQ",
            "Liam Martin",
            11,
            "Medium",
            "456|Walnut Street|Melbourne|VIC|Australia",
            "09-09-1975",
            55
        );

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            driver.updateDriverInfo(
                11,
                "Heavy",
                "456|Walnut Street|Melbourne|VIC|Australia",
                "09-09-1975"
            );
        });

        System.out.println("Exception thrown: " + error.getMessage());
            }

        @Test // test 12: Driver with exactly 10 years of experience should be able to change license
            void shouldAllowLicenseChangeAt10Years() {
        Driver driver = new Driver(
            "37uv!@WXRS",
            "Mia Wilson",
            10,
            "PublicTransport",
            "789|Willow Street|Melbourne|VIC|Australia",
            "10-10-1985",
            55
        );

        assertDoesNotThrow(() -> {
            driver.updateDriverInfo(
                10,
                "Medium",
                "789|Willow Street|Melbourne|VIC|Australia",
                "10-10-1985"
            );
        });

        assertEquals("Medium", driver.getLicenseType());
            }

        @Test // test 12: Updating non-immutable fields should pass
            void shouldUpdateAddress() {
        Driver driver = new Driver(
            "38wx#$YZTU",
            "Noah Taylor",
            5,
            "Heavy",
            "123|Old Street|Melbourne|VIC|Australia",
            "01-01-1980",
            55
        );

        driver.updateDriverInfo(
            5,
            "Heavy",
            "456|New Street|Melbourne|VIC|Australia",
            "01-01-1980"
        );

        assertEquals("456|New Street|Melbourne|VIC|Australia", driver.getAddress());
    }

        @Test // test 13: Changing driver name should fail (immutable field)
        void shouldNotUpdateNameOrDriverIDDuringUpdate() {
          Driver driverTest = new Driver(
        "49mn!@PQRS",
        "John Doe",
        5,
        "Heavy",
        "123|Main Street|Melbourne|VIC|Australia",
        "01-01-1980",
        55
        );

        driverTest.updateDriverInfo(
        6,
        "Medium",
        "456|New Street|Melbourne|VIC|Australia",
        "02-02-1980"
    );

      assertEquals("49mn!@PQRS", driverTest.getDriverID());
      assertEquals("John Doe", driverTest.getName());

      assertEquals(6, driverTest.getExperienceYears());
      assertEquals("Medium", driverTest.getLicenseType());
      assertEquals("456|New Street|Melbourne|VIC|Australia", driverTest.getAddress());
      assertEquals("02-02-1980", driverTest.getBirthdate());

      }

    }

    

            



                    
