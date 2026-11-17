package lab08.beans;
public class RectangleBean {
    private double length;
    private double width;

    public RectangleBean() { }
    public RectangleBean(double length, double width) {
        setLength(length);
        setWidth(width);
    }
    public double getLength() { return length; }
    public void setLength(double length) {
        checkDimension(length, "length");
        this.length = length;
    }
    public double getWidth() { return width; }
    public void setWidth(double width) {
        checkDimension(width, "width");
        this.width = width;
    }
    public double calculateArea() { return length * width; }
    public double calculatePerimeter() { return 2 * (length + width); }

    private static void checkDimension(double value, String name) {
        if (!Double.isFinite(value) || value < 0) {
            throw new IllegalArgumentException(name + " must be finite and non-negative");
        }
    }
}
