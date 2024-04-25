package gov.transportation.railway.Controller;

import gov.transportation.railway.Enum.Location;
import gov.transportation.railway.Record.Trip;
import gov.transportation.railway.Repository.TripRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/trips")
public class TripController {


    private final TripRepo tripRepo;

    public TripController(TripRepo tripRepo){
        this.tripRepo = tripRepo;
    }

    @GetMapping("")
    List<Trip> findAll(){
        return tripRepo.findAll();
    }

    @GetMapping("/departure/{departure}/pickUp/{pickUp}/destination/{destination}")
    public List<Trip> findTrips(
            @PathVariable LocalDateTime departure,
            @PathVariable Location pickUp,
            @PathVariable Location destination) {
        List<Trip> matchingTrips = tripRepo.findTrips(departure, pickUp, destination);
        if (matchingTrips.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return matchingTrips;
    }

    @GetMapping("/departure/{departure}")
    public List<Trip> findByDate(@PathVariable String departure) {
        LocalDateTime departureTime = LocalDateTime.parse(departure);
        List<Trip> tripsOnDate = tripRepo.findByDate(departureTime);
        if (tripsOnDate.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return tripsOnDate;
    }

    @GetMapping("/pickUp/{pickUp}")
    public List<Trip> findByPickUp(@PathVariable Location pickUp) {
        List<Trip> tripsAtPickUp = tripRepo.findByPickUp(pickUp);
        if (tripsAtPickUp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return tripsAtPickUp;
    }

    @GetMapping("/destination/{destination}")
    public List<Trip> findByDestination(@PathVariable Location destination) {
        List<Trip> tripsToDestination = tripRepo.findByDestination(destination);
        if (tripsToDestination.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return tripsToDestination;
    }


}
