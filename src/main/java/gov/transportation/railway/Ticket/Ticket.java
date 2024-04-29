package gov.transportation.railway.Ticket;

import gov.transportation.railway.Trip.Trip;
import gov.transportation.railway.User.User;

public record Ticket(
        int id,
        User passenger,
        Trip trip
) {
}
