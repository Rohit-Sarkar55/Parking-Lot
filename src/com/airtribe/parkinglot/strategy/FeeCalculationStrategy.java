package src.com.airtribe.parkinglot.strategy;

import src.com.airtribe.parkinglot.entity.Ticket;

public interface FeeCalculationStrategy {
    double calculateFee(Ticket ticket);
}
