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
import src.com.airtribe.parkinglot.repository.TicketRepository;
import src.com.airtribe.parkinglot.strategy.FeeCalculationStrategy;
import src.com.airtribe.parkinglot.strategy.SpotAllocationStrategy;

import java.time.LocalDateTime;
import java.util.List;


public class ParkingService {
    private ParkingLot parkingLot;
    private SpotAllocationStrategy spotAllocationStrategy;
    private FeeCalculationStrategy feeCalculationStrategy;
    private TicketRepository ticketRepository;

    public ParkingService(ParkingLot parkingLot, SpotAllocationStrategy spotAllocationStrategy, FeeCalculationStrategy feeCalculationStrategy, TicketRepository ticketRepository) {
        this.parkingLot = parkingLot;
        this.spotAllocationStrategy = spotAllocationStrategy;
        this.feeCalculationStrategy = feeCalculationStrategy;
        this.ticketRepository = ticketRepository;
    }

    /**
     * Parks a vehicle by allocating the nearest available compatible spot and issuing a ticket.
     *
     * @param vehicle the vehicle to park
     * @return the issued {@link Ticket}, or {@code null} if the lot is full or the spot is already occupied
     */
    public Ticket checkIn(Vehicle vehicle){
        try {
           ParkingSpot spot=spotAllocationStrategy.allocate(vehicle, parkingLot.getFloors());
           spot.occupy(vehicle);
           Ticket ticket = new Ticket(spot, vehicle);
           ticketRepository.save(ticket);
           return ticket;
        } catch (ParkingFullException | SpotAlreadyOccupied e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Processes the exit of a parked vehicle: releases the spot, calculates the fee, and closes the ticket.
     *
     * @param ticketId the ID of the ticket issued at check-in
     * @return the closed {@link Ticket} with the parking fee populated
     * @throws TicketNotFoundException if no ticket exists for the given ID
     * @throws TicketClosedException   if the ticket has already been closed
     */
    public Ticket checkOut(int ticketId) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("No Ticket Found With this ID : " + ticketId));
        if(ticket.getTicketStatus() == TicketStatus.CLOSED){
            throw new TicketClosedException("Ticket already closed exceptions: "+ticketId);
        }

        ParkingSpot spot = ticket.getParkingSpot();
        spot.release();
        ticket.setExitTime(LocalDateTime.now());
        double fee = feeCalculationStrategy.calculateFee(ticket);
        ticket.setParkingFee(fee);
        ticket.setTicketStatus(TicketStatus.CLOSED);
        ticketRepository.save(ticket);
        return ticket;
    }

    /**
     * Returns all tickets that are currently active (vehicle still parked).
     *
     * @return list of active {@link Ticket}s
     */
    public List<Ticket> getAllOpenTickets(){
        return ticketRepository.findAllTicketWithStatus(TicketStatus.ACTIVE);
    }
}
