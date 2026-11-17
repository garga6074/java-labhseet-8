package lab08.beans;
public class CircleBean {
    private double radius;

    public CircleBean() { }
    public double getRadius() { return radius; }
    public void setRadius(double radius) {
        if (!Double.isFinite(radius) || radius < 0) {
            throw new IllegalArgumentException("Radius must be finite and non-negative");
        }
        this.radius = radius;
    }
    public double getArea() { return Math.PI * radius * radius; }
    public double getCircumference() { return 2 * Math.PI * radius; }
}
