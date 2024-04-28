package gov.transportation.railway.Trip;

import gov.transportation.railway.Enum.Location;
import gov.transportation.railway.Record.Trip;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/trips")
public class TripController {


    private final TripRepo tripRepo;

    public TripController(TripRepo tripRepo){
        this.tripRepo = tripRepo;
    }

    // POST (Create Trip)
//    void createTrip(Trip trip){
//        tripRepo.createTrip(trip);
//    }

    // READ OPERATIONS

    @GetMapping("")
    List<Trip> findAll(){
        return tripRepo.findAll();
    }

    @GetMapping("/{tripId}")
    Trip findById(@PathVariable Integer tripId) {
        Optional<Trip> trip = tripRepo.findById(tripId);
        if (trip.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return trip.get();
    }
//
//    @GetMapping("/departure/{departure}/pickUp/{pickUp}/destination/{destination}")
//    public List<Trip> findTrips(
//            @PathVariable LocalDateTime departure,
//            @PathVariable Location pickUp,
//            @PathVariable Location destination) {
//        List<Trip> matchingTrips = tripRepo.findTrips(departure, pickUp, destination);
//        if (matchingTrips.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return matchingTrips;
//    }
//
//    @GetMapping("/departure/{departure}")
//    public List<Trip> findByDate(@PathVariable String departure) {
//        LocalDateTime departureTime = LocalDateTime.parse(departure);
//        List<Trip> tripsOnDate = tripRepo.findByDate(departureTime);
//        if (tripsOnDate.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return tripsOnDate;
//    }
//
//    @GetMapping("/pickUp/{pickUp}")
//    public List<Trip> findByPickUp(@PathVariable Location pickUp) {
//        List<Trip> tripsAtPickUp = tripRepo.findByPickUp(pickUp);
//        if (tripsAtPickUp.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return tripsAtPickUp;
//    }
//
//    @GetMapping("/destination/{destination}")
//    public List<Trip> findByDestination(@PathVariable Location destination) {
//        List<Trip> tripsToDestination = tripRepo.findByDestination(destination);
//        if (tripsToDestination.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return tripsToDestination;
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Trip trip) {
        tripRepo.create(trip);
    }

    // PUT (Update Trip)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{tripId}")
    void update(@Valid @RequestBody Trip trip, @PathVariable Integer tripId){
        tripRepo.update(trip, tripId);
    }

    // DELETE (Delete Trip)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{tripId}")
    void delete(@PathVariable Integer tripId){
        tripRepo.delete(tripId);
    }

//    List<Trip> findByPickUp(@RequestParam String location) {
//        return tripRepo.findByPickUp(location);
//    }

}
