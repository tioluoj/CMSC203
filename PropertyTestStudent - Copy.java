package application;

/*
 * Class: CMSC203 
 * Instructor:Ahmed Tarek
 * Description: (Property management interface)
 * Due: 
 * Platform/compiler:eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: _____samuella Helha_____
*/
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTestStudent {

    @Test
    public void testParameterizedConstructor() {
        Property property = new Property("Belmar", "Silver Spring", 1200, "John Smith", 1, 1, 5, 5);
        assertEquals("Belmar", property.getPropertyName());
        assertEquals("Silver Spring", property.getCity());
        assertEquals(1200, property.getRentalAmount());
        assertEquals("John Smith", property.getOwner());
        assertEquals("1,1,5,5", property.getPlot().toString());
    }

    @Test
    public void testConstructorWithDefaultPlot() {
        Property property = new Property("Camden Lakeway", "Rockville", 2450, "Ann Taylor");
        assertEquals("Camden Lakeway", property.getPropertyName());
        assertEquals("Rockville", property.getCity());
        assertEquals(2450, property.getRentalAmount());
        assertEquals("Ann Taylor", property.getOwner());
        assertEquals("0,0,1,1", property.getPlot().toString()); // Default plot values
    }

    @Test
    public void testCopyConstructor() {
        Property originalProperty = new Property("Hamptons", "Rockville", 1250, "Rick Steves", 1, 1, 3, 3);
        Property copyProperty = new Property(originalProperty);
        assertEquals("Hamptons", copyProperty.getPropertyName());
        assertEquals("Rockville", copyProperty.getCity());
        assertEquals(1250, copyProperty.getRentalAmount());
        assertEquals("Rick Steves", copyProperty.getOwner());
        assertEquals("1,1,3,3", copyProperty.getPlot().toString());
    }

    @Test
    public void testToString() {
        Property property = new Property("Belmar", "Silver Spring", 1200, "John Smith");
        assertEquals("Belmar,Silver Spring,John Smith,1200.0", property.toString());
    }
}

