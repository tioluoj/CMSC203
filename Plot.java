package application;


public class Plot {
    private int x;      // X-coordinate of the upper-left corner
    private int y;      // Y-coordinate of the upper-left corner
    private int width;  // Width of the plot
    private int depth;  // Depth (height) of the plot

    // Default constructor, initializes plot at (0, 0) with width and depth of 1
    public Plot() {
        this.x = 0;
        this.y = 0;
        this.width = 1;
        this.depth = 1;
    }

    // Parameterized constructor to initialize plot with specific coordinates and size
    public Plot(int x, int y, int width, int depth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.depth = depth;
    }

    // Copy constructor to create a new Plot instance based on an existing Plot
    public Plot(Plot otherPlot) {
        this.x = otherPlot.x;
        this.y = otherPlot.y;
        this.width = otherPlot.width;
        this.depth = otherPlot.depth;
    }

    // Getter and Setter methods for x, y, width, and depth
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getDepth() { return depth; }
    public void setDepth(int depth) { this.depth = depth; }

    // Method to check if this plot overlaps with another plot
    public boolean overlaps(Plot otherPlot) {
        return !(otherPlot.x >= x + width || otherPlot.x + otherPlot.width <= x ||
                 otherPlot.y >= y + depth || otherPlot.y + otherPlot.depth <= y);
    }

    // Method to check if this plot encompasses another plot (inclusive of edges)
    public boolean encompasses(Plot otherPlot) {
        return otherPlot.x >= x && otherPlot.y >= y &&
               (otherPlot.x + otherPlot.width) <= (x + width) &&
               (otherPlot.y + otherPlot.depth) <= (y + depth);
    }

    // Override toString to represent the plot in the format [x],[y],[width],[depth]
    @Override
    public String toString() {
        return x + "," + y + "," + width + "," + depth;
    }
}
