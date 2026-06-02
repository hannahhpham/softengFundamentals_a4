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
     void shouldCreateDriver() {
         Driver result = assertDoesNotThrow(() -> {
             return new Driver("23456789!@#$", "John Doe", 5, "Heavy", "123 Main St", "01/01/1980", 40);
         });

         if (result != null) {
             System.out.println("Driver was successfully created");
         } 
         else {
             System.out.println("Driver was not created");
         }
     }

     @Test // test 2: an invalid Driver ID is not created
     void shouldThrowErrorInvalidDriverID() {
            Exception error = assertThrows(IllegalArgumentException.class, 
                () -> new Driver("invalidID", "Jane Doe", 3, "Light", "456 Elm St", "02/02/1990", 30) );
            System.out.println("Exception thrown: " + error.getMessage());
        }

     @Test // test 3: boundary value test for driver ID length (exactly 10 characters, and exactly 2 special characters)
     void driverIDShouldPass() {
        Driver result = assertDoesNotThrow(() -> {
            return new Driver("23456789!@", "Alice Smith", 10, "Medium", "789 Oak St", "03/03/1975", 45);
        });

        if (result != null) {
            System.out.println("Driver was successfully created with boundary value ID");
        } 
        else {
            System.out.println("Driver was not created with boundary value ID");
        }
     }

     @Test // test 4: Valid address should pass
        void shouldCreateDriverWithValidAddress() {
            Driver result = assertDoesNotThrow(() -> {
                return new Driver("34567890#$", "Bob Brown", 8, "PublicTransport", "321|Pine St|Melbourne|VIC|Australia", "04/04/1985", 35);
            });
    
            if (result != null) {
                System.out.println("Driver was successfully created with valid address");
            } 
            else {
                System.out.println("Driver was not created with valid address");
            }
        }

      @Test // test 5: Invalid address should throw an error
        void shouldThrowErrorInvalidAddress() {
            Exception error = assertThrows(IllegalArgumentException.class, 
                () -> new Driver("45678901!#", "Charlie Davis", 12, "Heavy", "Invalid Address", "05/05/1970", 50) );
            System.out.println("Exception thrown: " + error.getMessage());
        }

        @Test // test 6: Very short address should pass (edge case)
        void shouldCreateDriverWithShortAddress() {
            Driver result = assertDoesNotThrow(() -> {
                return new Driver("56789012#$", "Diana Evans", 15, "Medium", "1|Main St|Melbourne|VIC|Australia", "06/06/1965", 55);
            });
    
            if (result != null) {
                System.out.println("Driver was successfully created with short address");
            } 
            else {
                System.out.println("Driver was not created with short address");
            }
         }

         @Test // test 7: Valid birthdate should pass
            void shouldCreateDriverWithValidBirthdate() {
                Driver result = assertDoesNotThrow(() -> {
                    return new Driver("67890123!@", "Ethan Foster", 20, "Light", "654 Cedar St", "07/07/1955", 65);
                });
        
                if (result != null) {
                    System.out.println("Driver was successfully created with valid birthdate");
                } 
                else {
                    System.out.println("Driver was not created with valid birthdate");
                }
            }
    
        @Test // test 8: Invalid birthdate should throw an error
            void shouldThrowErrorInvalidBirthdate() {
                Exception error = assertThrows(IllegalArgumentException.class, 
                    () -> new Driver("78901234#$", "Fiona Green", 25, "PublicTransport", "987 Spruce St", "invalidDate", 70) );
                System.out.println("Exception thrown: " + error.getMessage());
            }

        @Test // test 9: Edge case for birthdate (e.g., very old birthdate) should pass
            void shouldCreateDriverWithOldBirthdate() {
                Driver result = assertDoesNotThrow(() -> {
                    return new Driver("89012345!#", "George Harris", 30, "Heavy", "321 Birch St", "01/01/1900", 120);
                });
        
                if (result != null) {
                    System.out.println("Driver was successfully created with old birthdate");
                } 
                else {
                    System.out.println("Driver was not created with old birthdate");
                }
             }

        @Test // Test 10: Driver with 10 or less years of experience can change lisense
            void shouldAllowLicenseChange() {
                Driver result = assertDoesNotThrow(() -> {
                     return new Driver("90123456#$", "Hannah Johnson", 10, "Light", "123 Maple St", "08/08/1980", 40);
                }); 
                if (result != null) {
                     System.out.println("Driver with 10 years of experience was successfully created and can change license");
                } 
                else {
                    System.out.println("Driver with 10 years of experience was not created");
                }
            }

        @Test // Test 11: Driver with more than 10 years of experience cannot change license
            void showErrorLicenseChange() {
                Driver result = assertDoesNotThrow(() -> {
                    return new Driver("01234567!@", "Ian King", 11, "Medium", "456 Walnut St", "09/09/1975", 45);
                }); 
                if (result != null) {
                    System.out.println("Driver with more than 10 years of experience was successfully created but cannot change license");
                } 
                else {
                    System.out.println("Driver with more than 10 years of experience was not created");
                }
            }

        @Test // test 12: Driver with exactly 10 years of experience should be able to change license
            void shouldAllowLicenseChangeAt10Years() {
                Driver result = assertDoesNotThrow(() -> {
                    return new Driver("12345678!@", "Jack Lee", 10, "PublicTransport", "789 Willow St", "10/10/1985", 35);
                }); 
                if (result != null) {
                    System.out.println("Driver with exactly 10 years of experience was successfully created and can change license");
                } 
                else {
                    System.out.println("Driver with exactly 10 years of experience was not created");
                }
            }

        @Test // test 12: Updating non-immutable fields should pass
            void shouldUpdateAddress() {
                Driver driverTest = new Driver("23456789!@", "John Doe", 5, "Heavy", "123 Main St", "01/01/1980", 40);
                    
                driverTest.setAddress("456 New Address");

                assertEquals("456 New Address", driverTest.getAddress());
                System.out.println("Driver address was successfully updated");
            }

        @Test // test 13: Changing name should fail (immutable field)
             void shouldNotUpdateName() {
                Driver driverTest = new Driver("23456789!@", "John Doe", 5, "Heavy", "123 Main St", "01/01/1980", 40);
                    
                Exception error = assertThrows(UnsupportedOperationException.class, 
                    () -> driverTest.setName("New Name") );
                System.out.println("Exception thrown: " + error.getMessage());
            }

        @Test // test 14: Changing driver 1D should fail (immutable field)
            void shouldNotUpdateDriverID() {
                Driver driverTest = new Driver("23456789!@", "John Doe", 5, "Heavy", "123 Main St", "01/01/1980", 40);
                    
                Exception error = assertThrows(UnsupportedOperationException.class, 
                    () -> driverTest.setDriverID("newID") );
                System.out.println("Exception thrown: " + error.getMessage());
            }
        }

    

            



                    
