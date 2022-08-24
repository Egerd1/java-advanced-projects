package vehicleIO.model;

public class Car extends Vehicle {
    private int topSpeed;
    private Transmission transmission;
    private CarShape shape;

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public CarShape getShape() {
        return shape;
    }

    public void setShape(CarShape shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Car [" +
                "topSpeed=" + topSpeed +
                ", transmission=" + transmission +
                ", shape=" + shape +
                ']';
    }
}
