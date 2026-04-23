package src.com.airtribe.parkinglot.entity;

import src.com.airtribe.parkinglot.enums.TicketStatus;
import src.com.airtribe.parkinglot.util.IdGenerator;

import java.time.LocalDateTime;

public class Ticket {

    private int ticketId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;
    private TicketStatus ticketStatus;
    double parkingFee;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Ticket( ParkingSpot parkingSpot, Vehicle vehicle) {
        this.ticketId = IdGenerator.getTicketId();
        this.entryTime = LocalDateTime.now();
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
        this.ticketStatus = TicketStatus.ACTIVE;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public double getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(double parkingFee) {
        this.parkingFee = parkingFee;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", entryTime=" + entryTime +
                ", parkingSpot=" + parkingSpot +
                ", vehicle=" + vehicle +
                ", ticketStatus=" + ticketStatus +
                '}';
    }
}
