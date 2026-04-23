# ParkingLot

ParkingLot is a Java-based parking lot management system designed to simulate real-world parking operations. The system is built with modularity, scalability, and maintainability in mind, adhering to SOLID principles and leveraging design patterns such as Builder and Strategy for flexibility.

---

## Features

- Multi-floor parking lot with configurable spots per floor
- Support for three vehicle types: **Car**, **Motorcycle**, and **Bus**
- Spot types matched to vehicle size: **Compact**, **Motorcycle**, and **Large**
- Automatic spot allocation using the **Nearest Available** strategy
- Ticket issuance on check-in with entry time and spot details
- Fee calculation on check-out using an **Hourly Rate** strategy
- In-memory ticket repository for active ticket tracking
- Interactive menu-driven CLI interface

---

## Design Patterns Used

| Pattern | Where |
|---|---|
| **Builder** | `ParkingLot`, `ParkingFloor`, `ParkingSpot` — fluent construction of the lot hierarchy |
| **Strategy** | `SpotAllocationStrategy`, `FeeCalculationStrategy` — swappable allocation and pricing logic |

---

## Project Structure

```
src/
├── Main.java                          # Entry point — menu-driven CLI
└── com/airtribe/parkinglot/
    ├── entity/
    │   ├── ParkingLot.java            # Top-level lot; holds floors
    │   ├── ParkingFloor.java          # Floor; holds spots
    │   ├── ParkingSpot.java           # Individual spot with occupancy state
    │   ├── Vehicle.java               # Vehicle with type and auto-generated ID
    │   └── Ticket.java                # Issued on check-in; closed on check-out
    ├── service/
    │   └── ParkingService.java        # Core business logic: check-in and check-out
    ├── repository/
    │   └── TicketRepository.java      # In-memory ticket store
    ├── strategy/
    │   ├── SpotAllocationStrategy.java
    │   ├── NearestAvailable.java      # Allocates the first compatible free spot
    │   ├── FeeCalculationStrategy.java
    │   └── HourlyFeeCalculation.java  # Fee = ceil(hours) × rate per vehicle type
    ├── enums/
    │   ├── VehicleType.java           # CAR, MOTORCYCLE, BUS
    │   ├── SpotType.java              # COMPACT, MOTORCYCLE, LARGE
    │   └── TicketStatus.java          # ACTIVE, CLOSED
    ├── exceptions/
    │   ├── ParkingFullException.java
    │   ├── SpotAlreadyOccupied.java
    │   ├── TicketNotFoundException.java
    │   └── TicketClosedException.java
    └── util/
        └── IdGenerator.java           # Auto-incremented IDs for lots, vehicles, tickets
```

---

## How to Run

No build tool is configured. Open the project in **IntelliJ IDEA** (JDK 23) and run `Main.java` directly.

Once started, the CLI presents a menu:

```
=== Nexus Mall Parking ===
1. Check In
2. Check Out
3. Exit
Enter choice:
```

- **Check In** — select a vehicle type; the system allocates the nearest available spot and prints the ticket ID.
- **Check Out** — enter the ticket ID; the system releases the spot and prints the parking fee.

---

## Hourly Rates

| Vehicle | Rate per hour |
|---|---|
| Motorcycle | $10 |
| Car | $20 |
| Bus | $40 |

---

## Contact

For any inquiries or support, please contact:

**Email:** sarkarrohit650@gmail.com
