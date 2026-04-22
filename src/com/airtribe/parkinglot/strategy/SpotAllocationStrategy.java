package src.com.airtribe.parkinglot.strategy;

import src.com.airtribe.parkinglot.entity.ParkingFloor;
import src.com.airtribe.parkinglot.entity.ParkingSpot;
import src.com.airtribe.parkinglot.entity.Vehicle;
import src.com.airtribe.parkinglot.exceptions.ParkingFullException;

import java.util.List;

public interface SpotAllocationStrategy {
    ParkingSpot allocate(Vehicle vehicle, List<ParkingFloor> parkingFloors) throws ParkingFullException;
}
