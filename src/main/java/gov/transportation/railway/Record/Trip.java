package gov.transportation.railway.Record;

import gov.transportation.railway.Enum.Class;
import gov.transportation.railway.Enum.Location;

import java.time.LocalDateTime;

public record Trip(
        int tripId,
        Location pickUp,
        Location destination,
        LocalDateTime departure,
        double price,
        Class nClass
) {

}
