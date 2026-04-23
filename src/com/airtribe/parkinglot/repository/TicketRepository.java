package src.com.airtribe.parkinglot.repository;

import src.com.airtribe.parkinglot.entity.Ticket;
import src.com.airtribe.parkinglot.enums.TicketStatus;

import java.util.*;

public class TicketRepository {
    private final Map<Integer, Ticket> store = new HashMap<>();


    public void save(Ticket ticket) {
        store.put(ticket.getTicketId(), ticket);
    }


    public Optional<Ticket> findById(int ticketId) {
        return Optional.ofNullable(store.get(ticketId));
    }


    public List<Ticket> findAll() {
        return store.values().stream().toList();
    }

    public List<Ticket> findAllTicketWithStatus(TicketStatus status){
        return store.values().stream().filter(ticket -> ticket.getTicketStatus().equals(status))
                .toList();
    }
}
