package src;

import src.com.airtribe.parkinglot.entity.*;
import src.com.airtribe.parkinglot.enums.SpotType;
import src.com.airtribe.parkinglot.enums.VehicleType;
import src.com.airtribe.parkinglot.repository.TicketRepository;
import src.com.airtribe.parkinglot.service.ParkingService;
import src.com.airtribe.parkinglot.strategy.HourlyFeeCalculation;
import src.com.airtribe.parkinglot.strategy.NearestAvailable;

public class Main {
    public static void main(String[] args) throws Exception {
        ParkingLot lot = ParkingLot.Builder.newInstance( "Nexus Mall Parking")
                .addFloor(new ParkingFloor.Builder(1)
                        .addSpot(new ParkingSpot.Builder(1, 1, SpotType.MOTORCYCLE).build())
                        .addSpot(new ParkingSpot.Builder(1, 2, SpotType.COMPACT).build())
                        .addSpot(new ParkingSpot.Builder(1, 3, SpotType.LARGE).build())
                        .build())
                .addFloor(new ParkingFloor.Builder(2)
                        .addSpot(new ParkingSpot.Builder(2, 1, SpotType.COMPACT).build())
                        .addSpot(new ParkingSpot.Builder(2, 2, SpotType.MOTORCYCLE).build())
                        .build())
                .build();



        ParkingService service = new ParkingService(lot, new NearestAvailable(), new HourlyFeeCalculation(), new TicketRepository());

        Vehicle car =new Vehicle(VehicleType.CAR);
        Vehicle car2 = new Vehicle(VehicleType.CAR);
        Vehicle car3 = new Vehicle(VehicleType.CAR);

        Ticket ticket = service.checkIn(car);
        Ticket ticket2 = service.checkIn(car2);
        Ticket ticket3 = service.checkIn(car3);
        System.out.println("Checked in  → Ticket #" + ticket.getTicketId()
                + " | Floor " + ticket.getParkingSpot().getFloorNumber()
                + ", Spot " + ticket.getParkingSpot().getSpotNumber());

        System.out.println("Checked in  → Ticket #" + ticket2.getTicketId()
                + " | Floor " + ticket2.getParkingSpot().getFloorNumber()
                + ", Spot " + ticket2.getParkingSpot().getSpotNumber());

        System.out.println("Checked in  → Ticket #" + ticket3.getTicketId()
                + " | Floor " + ticket3.getParkingSpot().getFloorNumber()
                + ", Spot " + ticket3.getParkingSpot().getSpotNumber());

        Ticket closed = service.checkOut(ticket.getTicketId());
        System.out.println("Checked out → Fee = $" + closed.getParkingFee());
        Ticket closed2 = service.checkOut(ticket2.getTicketId());
        System.out.println("Checked out → Fee = $" + closed2.getParkingFee());
        Ticket closed3 = service.checkOut(ticket3.getTicketId());
        System.out.println("Checked out → Fee = $" + closed3.getParkingFee());
}
}
