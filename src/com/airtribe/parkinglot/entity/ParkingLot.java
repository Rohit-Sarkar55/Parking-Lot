package src.com.airtribe.parkinglot.entity;

import src.com.airtribe.parkinglot.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int parkingLotId;
    private String name;
    private List<ParkingFloor> floors;

    public ParkingLot( String name) {
        this.parkingLotId = IdGenerator.getParkingLotId();
        this.name = name;
        this.floors = new ArrayList<>();
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

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

    public static class Builder {
        private final int parkingLotId;
        private final String name;
        private final List<ParkingFloor> floors = new ArrayList<>();

        public Builder( String name) {
            this.parkingLotId = IdGenerator.getParkingLotId();
            this.name = name;
        }
        public static Builder newInstance(String name){
            return new Builder(name);
        }
        public Builder addFloor(ParkingFloor floor) {
            floors.add(floor);
            return this;
        }

        public ParkingLot build() {
            ParkingLot lot = new ParkingLot(name);
            floors.forEach(lot::addFloor);
            return lot;
        }
    }
}
