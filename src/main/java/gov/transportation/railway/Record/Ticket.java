package gov.transportation.railway.Record;

public record Ticket(
        int id,
        Passenger passenger,
        Trip trip
) {
}
