package src.com.airtribe.parkinglot.entity;


import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    public int getfloorNumber() {
        return floorNumber;
    }

    public void setfloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public static class Builder {
        private final int floorNumber;
        private final List<ParkingSpot> spots = new ArrayList<>();

        public Builder(int floorNumber) {
            this.floorNumber = floorNumber;
        }

        public Builder addSpot(ParkingSpot spot) {
            spots.add(spot);
            return this;
        }

        public ParkingFloor build() {
            ParkingFloor floor = new ParkingFloor(floorNumber);
            spots.forEach(floor::addSpot);
            return floor;
        }
    }
}
