// Generic object
public abstract class GenObject{
    protected boolean identified = false;
    protected boolean active = false;

    // All measured in pixels (on the screen)
    public int x = 0; //perpendicular to driver row
    public int y = 0; //parallel to driver row
    public int w = 0; // width
    public int h = 0; // height

    // GenObject constructor
    // Used by element-specific child classes (through the keyword "super")
    public GenObject(String name) {
        this.name = name;
    }

    // Objects should be active before they can be used in a meaningful way
    public void activate() {
        if (!active) {
            active = true;
        }
    }

    public void deactivate() {
        if (active) {
            active = false;
        }
    }

    // True if val is between min and max
    public boolean inRange(double val, double min, double max) {
        return (min <= val && val <= max);
    }

    public boolean isActive() {
        return active;
    }

    public boolean isIdentified() {
        return identified;
    }

    // A test to verify the approximate size of an object
    // Should be different for each child class
    abstract boolean isReasonable(int x, int y, int w, int h);

    public void setTargetX(int targetX) {
        this.targetX = targetX;
        resetPIDs();
    }

    public void setToNotIdentified() {
        this.x = 0;
        this.y = 0;
        this.w = 0;
        this.h = 0;
        identified = false;
    }

    // This displays a bunch of useful information for telemetry
    @Override
    public String toString() {
        String s = name.toUpperCase() + " (x = " + x + ", y = " + y + ", w = " + w + ", h = " + h + ")";
        if (!active) {
            s = "NOT ACTIVE: " + s;
        }
        else if (!identified) {
            s = "NOT IDENTIFIED: " + s;
        } else {
            s = "OBJECT: " + s;
        }
        return s;
    }

    // Updates coordinates and identified boolean
    // This method is constantly called by the object's pipeline as long as it is active
    public void updateData();
}