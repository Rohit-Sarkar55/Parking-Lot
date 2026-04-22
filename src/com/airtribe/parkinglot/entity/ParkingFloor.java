package src.com.airtribe.parkinglot.entity;


import java.util.List;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;

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
}
