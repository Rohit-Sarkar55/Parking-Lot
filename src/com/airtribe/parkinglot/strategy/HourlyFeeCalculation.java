package src.com.airtribe.parkinglot.strategy;

import src.com.airtribe.parkinglot.entity.Ticket;
import src.com.airtribe.parkinglot.enums.VehicleType;

import java.time.Duration;
import java.util.Map;

public class HourlyFeeCalculation implements FeeCalculationStrategy{
    private static final Map<VehicleType, Double> RATE_PER_HOUR = Map.of(
            VehicleType.MOTORCYCLE, 10.0,
            VehicleType.CAR,        20.0,
            VehicleType.BUS,        40.0
    );

    @Override
    public double calculateFee(Ticket ticket) {
        long duration_minutes = Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toMinutes();
        long duration_hours = (long)(Math.ceil(duration_minutes/60.0));
        duration_hours = Math.max(1l, duration_hours);
        double rate = RATE_PER_HOUR.get(ticket.getVehicle().getVehicleType());
        return duration_hours * rate;
    }
}
