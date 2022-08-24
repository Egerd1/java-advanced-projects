package vehicleIO.model;

public class Motorcycle extends Vehicle{
    private int topSpeed;
    private MotorcycleShape shape;

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public MotorcycleShape getShape() {
        return shape;
    }

    public void setShape(MotorcycleShape shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Motorcycle [" +
                "topSpeed=" + topSpeed +
                ", shape=" + shape +
                ']';
    }
}
