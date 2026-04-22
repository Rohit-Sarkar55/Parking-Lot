package src.com.airtribe.parkinglot.entity;

import java.util.List;

public class ParkingLot {
    private int parkingLotId;
    private String name;
    private List<ParkingFloor> floors;

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public ParkingLot(int parkingLotId, String name) {
        this.parkingLotId = parkingLotId;
        this.name = name;
    }
}
