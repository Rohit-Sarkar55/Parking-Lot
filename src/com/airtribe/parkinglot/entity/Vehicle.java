package src.com.airtribe.parkinglot.entity;

import src.com.airtribe.parkinglot.enums.VehicleType;
import src.com.airtribe.parkinglot.util.IdGenerator;

public class Vehicle {
    private int vehicleId;
    private VehicleType vehicleType;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle() {
        this.vehicleId = IdGenerator.getVehicleId();
    }
    public Vehicle(VehicleType type){
        this.vehicleId = IdGenerator.getVehicleId();
        this.vehicleType = type;
    }
}
