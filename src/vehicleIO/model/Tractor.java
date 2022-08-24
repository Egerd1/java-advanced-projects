package vehicleIO.model;

public class Tractor extends Vehicle{
    private int maxPulledWeight;

    public int getMaxPulledWeight() {
        return maxPulledWeight;
    }

    public void setMaxPulledWeight(int maxPulledWeight) {
        this.maxPulledWeight = maxPulledWeight;
    }

    @Override
    public String toString() {
        return "Tractor [" +
                "maxPulledWeight=" + maxPulledWeight +
                ']';
    }
}
