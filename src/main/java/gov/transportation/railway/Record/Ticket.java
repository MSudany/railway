package gov.transportation.railway.Record;

import gov.transportation.railway.Enum.Location;
import gov.transportation.railway.Repository.TripRepo;

import java.time.LocalDateTime;

public record Ticket(
        int id,
        Passenger passenger,
        Trip trip
) {
}
