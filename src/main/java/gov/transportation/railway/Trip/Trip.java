package gov.transportation.railway.Trip;

import gov.transportation.railway.Enum.Class;
import gov.transportation.railway.Enum.Location;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Trip(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer tripId,
        @NotNull(message = "Pick up location can't be empty")
        Location pickUp,
        @NotNull(message = "Destination can't be empty")
        Location destination,
        @Future(message = "Departure time already passed!")
        LocalDateTime departure,
        @Positive(message = "Price can't be negative!")
        double price,
        @NotNull
        Class nClass
) {
    public Trip {
        if(pickUp == destination){
            throw new IllegalArgumentException("Pick up location can't be the same as destination.");
        }

        if(departure.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Departure time already passed!");
        }
    }

    @Override
    public Integer tripId() {
        return tripId;
    }

    @Override
    public Location pickUp() {
        return pickUp;
    }

    @Override
    public Location destination() {
        return destination;
    }

    @Override
    public LocalDateTime departure() {
        return departure;
    }

    @Override
    public double price() {
        return price;
    }

    @Override
    public Class nClass() {
        return nClass;
    }
}
