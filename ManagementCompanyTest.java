package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManagementCompanyTest {
    private ManagementCompany managementCompany;
    private Property property1, property2, property3, propertyOutOfBounds;

    @BeforeEach
    public void setUp() {
        managementCompany = new ManagementCompany("MyCompany", "12345", 10.0);
        property1 = new Property("BlueHouse", "Los Angeles", 1500.0, "Alice Brown", 1, 1, 2, 2);
        property2 = new Property("RedHouse", "San Francisco", 2500.0, "Bob Smith", 3, 3, 2, 2);
        property3 = new Property("YellowHouse", "Seattle", 1000.0, "Charlie Day", 5, 5, 2, 2);
        propertyOutOfBounds = new Property("OutOfBoundsHouse", "Miami", 3000.0, "Dana White", 10, 10, 3, 3);
    }

    @Test
    public void testAddPropertyWithinBounds() {
        assertEquals(0, managementCompany.addProperty(property1));  // First property, index 0
        assertEquals(1, managementCompany.addProperty(property2));  // Second property, index 1
        assertEquals(2, managementCompany.addProperty(property3));  // Third property, index 2
    }

    @Test
    public void testAddPropertyOutOfBounds() {
        // Adding a property that is outside the management company's plot bounds
        assertEquals(-3, managementCompany.addProperty(propertyOutOfBounds));  // Out of bounds
    }

    @Test
    public void testAddPropertyArrayFull() {
        // Filling up the properties array to its maximum
        managementCompany.addProperty(property1);
        managementCompany.addProperty(property2);
        managementCompany.addProperty(property3);
        managementCompany.addProperty(new Property("GreenHouse", "Portland", 1200.0, "Eve Davis", 6, 6, 2, 2));
        managementCompany.addProperty(new Property("PurpleHouse", "Austin", 1300.0, "Frank Miller", 7, 7, 2, 2));
        
        // Adding another property should fail because the array is full
        Property additionalProperty = new Property("OrangeHouse", "Denver", 1400.0, "Grace Hopper", 8, 8, 2, 2);
        assertEquals(-1, managementCompany.addProperty(additionalProperty), "Adding property beyond capacity should return -1");
    }

    @Test
    public void testTotalRentCalculation() {
        managementCompany.addProperty(property1);
        managementCompany.addProperty(property2);
        managementCompany.addProperty(property3);
        double expectedTotalRent = property1.getRentalAmount() + property2.getRentalAmount() + property3.getRentalAmount();
        assertEquals(expectedTotalRent, managementCompany.getTotalRent(), 0.001);  // Tolerance for floating-point comparison
    }

    @Test
    public void testGetHighestRentProperty() {
        // Add several properties and verify that the highest rent property is identified correctly
        managementCompany.addProperty(new Property("Property1", "City1", 1000, "Owner1", 0, 0, 2, 2));
        managementCompany.addProperty(new Property("Property2", "City2", 3000, "Owner2", 3, 3, 2, 2));  // Highest rent
        managementCompany.addProperty(new Property("Property3", "City3", 2000, "Owner3", 6, 6, 2, 2));
        
        Property highestRentProperty = managementCompany.getHighestRentProperty();
        assertEquals("City2", highestRentProperty.getCity());  // Verifying city of highest rent property
        assertEquals(3000.0, highestRentProperty.getRentalAmount(), 0.0);  // Verifying rent amount
    }

    @Test
    public void testGetPropertiesCount() {
        managementCompany.addProperty(property1);
        managementCompany.addProperty(property2);
        managementCompany.addProperty(property3);
        assertEquals(3, managementCompany.getPropertiesCount());  // Should return 3 as there are 3 properties added
    }

    @Test
    public void testToString() {
        managementCompany.addProperty(property1);
        managementCompany.addProperty(property2);
        managementCompany.addProperty(property3);

        String expectedOutput = "List of the properties for MyCompany, taxID: 12345\n" +
                "\n" +
                property1.toString() + "\n" +
                property2.toString() + "\n" +
                property3.toString() + "\n" +
                "\n" +
                "Total management Fee: " + (managementCompany.getMgmtFee() * managementCompany.getTotalRent() / 100) + "\n";

        assertEquals(expectedOutput.trim(), managementCompany.toString().trim());  // Ensuring the output matches
    }
}