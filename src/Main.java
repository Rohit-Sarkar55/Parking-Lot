package src;

import src.com.airtribe.parkinglot.entity.*;
import src.com.airtribe.parkinglot.enums.SpotType;
import src.com.airtribe.parkinglot.enums.VehicleType;
import src.com.airtribe.parkinglot.repository.TicketRepository;
import src.com.airtribe.parkinglot.service.ParkingService;
import src.com.airtribe.parkinglot.strategy.HourlyFeeCalculation;
import src.com.airtribe.parkinglot.strategy.NearestAvailable;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.Builder.newInstance("Nexus Mall Parking")
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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== " + lot.getName() + " ===");
            System.out.println("1. Check In");
            System.out.println("2. Check Out");
            System.out.println("3. Check Open Tickets");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> handleCheckIn(service, scanner);
                case "2" -> handleCheckOut(service, scanner);
                case "3" -> handleTicketingService(service);
                case "4" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void handleCheckIn(ParkingService service, Scanner scanner) {
        System.out.println("\nVehicle type:");
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Bus");
        System.out.print("Enter choice: ");

        VehicleType type = switch (scanner.nextLine().trim()) {
            case "1" -> VehicleType.CAR;
            case "2" -> VehicleType.MOTORCYCLE;
            case "3" -> VehicleType.BUS;
            default -> null;
        };

        if (type == null) {
            System.out.println("Invalid vehicle type.");
            ;
        }

        Ticket ticket = service.checkIn(new Vehicle(type));
        if (ticket != null) {
            System.out.println("Checked in → Ticket #" + ticket.getTicketId()
                    + " | Floor " + ticket.getParkingSpot().getFloorNumber()
                    + ", Spot " + ticket.getParkingSpot().getSpotNumber());
        }
    }

    private static void handleCheckOut(ParkingService service, Scanner scanner) {
        System.out.print("\nEnter ticket ID: ");
        try {
            int ticketId = scanner.nextInt();
            scanner.nextLine();

            Ticket closed = service.checkOut(ticketId);
            System.out.println("Checked out → Fee = $" + closed.getParkingFee());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ticket ID.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleTicketingService(ParkingService service){
        List<Ticket> openedTickets  = service.getAllOpenTickets();
        for (Ticket ticket: openedTickets){
            System.out.println(ticket);
        }
    }
}
