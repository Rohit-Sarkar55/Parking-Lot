package src.com.airtribe.parkinglot.strategy;

import src.com.airtribe.parkinglot.entity.ParkingFloor;
import src.com.airtribe.parkinglot.entity.ParkingSpot;
import src.com.airtribe.parkinglot.entity.Vehicle;
import src.com.airtribe.parkinglot.enums.SpotType;
import src.com.airtribe.parkinglot.exceptions.ParkingFullException;

import java.util.List;

public class NearestAvailable implements SpotAllocationStrategy{

    @Override
    public ParkingSpot allocate(Vehicle vehicle, List<ParkingFloor> parkingFloors) throws ParkingFullException {
       for(ParkingFloor floor: parkingFloors){
           for(ParkingSpot spot: floor.getSpots()){
               if(!spot.isOccupied()&&isCompatible(spot, vehicle)){
                   return  spot;
               }
           }
       }
       throw new ParkingFullException("Parking is full no spot available for : "+ vehicle.getVehicleType() + " : "+vehicle.getVehicleId());
    }
    private boolean isCompatible(ParkingSpot spot, Vehicle vehicle){
        return switch (vehicle.getVehicleType()) {
            case MOTORCYCLE -> spot.getSpotType() == SpotType.MOTORCYCLE;
            case CAR        -> spot.getSpotType() == SpotType.COMPACT;
            case BUS        -> spot.getSpotType() == SpotType.LARGE;
        };
    }
}
