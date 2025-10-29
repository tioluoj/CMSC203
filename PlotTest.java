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

package application;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlotTest {
    
    // Test constructors, methods, and any edge cases for Plot class

    @Test
    public void testOverlap() {
        Plot plot1 = new Plot(0, 0, 5, 5);
        Plot plot2 = new Plot(4, 4, 5, 5);
        assertTrue(plot1.overlaps(plot2));
    }

    @Test
    public void testEncompasses() {
        Plot plot1 = new Plot(0, 0, 10, 10);
        Plot plot2 = new Plot(2, 2, 5, 5);
        assertTrue(plot1.encompasses(plot2));
    }

    @Test
    public void testToString() {
        Plot plot = new Plot(1, 2, 3, 4);
        assertEquals("1,2,3,4", plot.toString());
    }
}
