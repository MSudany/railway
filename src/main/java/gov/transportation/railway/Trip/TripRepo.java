package gov.transportation.railway.Trip;

import jakarta.validation.Valid;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface TripRepo extends ListCrudRepository<Trip, Integer> {

    List<Trip> findAllByPickUp(String pickUp);

    List<Trip> findAllByDestination(String destination);

    List<Trip> findAllByDeparture(String departure);

    Optional<Trip> findById(Integer tripId);



}
