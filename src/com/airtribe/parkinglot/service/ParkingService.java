package src.com.airtribe.parkinglot.service;

import src.com.airtribe.parkinglot.entity.ParkingLot;
import src.com.airtribe.parkinglot.entity.ParkingSpot;
import src.com.airtribe.parkinglot.entity.Ticket;
import src.com.airtribe.parkinglot.entity.Vehicle;
import src.com.airtribe.parkinglot.enums.TicketStatus;
import src.com.airtribe.parkinglot.exceptions.ParkingFullException;
import src.com.airtribe.parkinglot.exceptions.SpotAlreadyOccupied;
import src.com.airtribe.parkinglot.exceptions.TicketClosedException;
import src.com.airtribe.parkinglot.exceptions.TicketNotFoundException;
import src.com.airtribe.parkinglot.strategy.FeeCalculationStrategy;
import src.com.airtribe.parkinglot.strategy.SpotAllocationStrategy;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class ParkingService {
    private ParkingLot parkingLot;
    private SpotAllocationStrategy spotAllocationStrategy;
    private FeeCalculationStrategy feeCalculationStrategy;
    private Map<Integer, Ticket> activeTickets;

    public ParkingService(ParkingLot parkingLot, SpotAllocationStrategy spotAllocationStrategy,FeeCalculationStrategy feeCalculationStrategy) {
        this.parkingLot = parkingLot;
        this.spotAllocationStrategy = spotAllocationStrategy;
        this.feeCalculationStrategy = feeCalculationStrategy;
        activeTickets = new HashMap<>();
    }

    public Ticket checkIn(Vehicle vehicle){
        try {
           ParkingSpot spot=spotAllocationStrategy.allocate(vehicle, parkingLot.getFloors());
           spot.occupy(vehicle);
           Ticket ticket = new Ticket(spot, vehicle);
           activeTickets.put(ticket.getTicketId(), ticket);
           return ticket;
        } catch (ParkingFullException | SpotAlreadyOccupied e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Ticket checkOut(int ticketId) throws TicketNotFoundException {
        Ticket ticket = activeTickets.get(ticketId);
        if(ticket == null){
            throw new TicketNotFoundException("No Ticket Found With this ID : "+ ticketId);
        }
        if(ticket.getTicketStatus() == TicketStatus.CLOSED){
            throw new TicketClosedException("Ticket already closed exceptions: "+ticketId);
        }

        ParkingSpot spot = ticket.getParkingSpot();
        spot.release();
        ticket.setExitTime(LocalDateTime.now());
        double fee = feeCalculationStrategy.calculateFee(ticket);
        ticket.setParkingFee(fee);
        ticket.setTicketStatus(TicketStatus.CLOSED);
        return ticket;
    }
}
