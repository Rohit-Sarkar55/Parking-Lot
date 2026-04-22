package src.com.airtribe.parkinglot.entity;

import src.com.airtribe.parkinglot.enums.VehicleType;

import java.util.List;
import java.util.Map;

public class ParkingFloor {
    private int floorId;
    private List<ParkingSpot> spots;
    private Map<VehicleType , List<ParkingSpot>> availableSpots;

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    public Map<VehicleType, List<ParkingSpot>> getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(Map<VehicleType, List<ParkingSpot>> availableSpots) {
        this.availableSpots = availableSpots;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }


}
