package src.com.airtribe.parkinglot.util;

public class IdGenerator {

    public static int vehicleId = 0;
    public static int ticketId = 0;
    public static int parkingSpotId= 0;
    public static int parkingLotId = 0;

    public static int getVehicleId() {
        vehicleId++;
        return vehicleId;
    }

    public static int getTicketId() {
        ticketId++;
        return ticketId;
    }

    public static int getParkingSpotId() {
        parkingSpotId++;
        return parkingSpotId;
    }

    public static int getParkingLotId() {
        parkingLotId++;
        return parkingLotId;
    }
}
