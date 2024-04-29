package gov.transportation.railway.Trip;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/trips")
public class TripController {


    private final TripService tripService;

    public TripController(TripService tripService){
        this.tripService = tripService;
    }


    // POST (Create Trip)

    @PostMapping("")
    public ResponseEntity<Void> createTrip(@Valid @RequestBody Trip trip) throws Exception {
        tripService.create(trip);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Return 201 Created
    }


    // READ OPERATIONS

    @GetMapping("")
    ResponseEntity<List<Trip>> findAll(){
        List<Trip> trips = tripService.findAll();
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/{tripId}")
    ResponseEntity<Trip> findById(@PathVariable Integer tripId) {
        Trip trip = tripService.findById(tripId);
        return ResponseEntity.ok(trip);
    }

    @GetMapping("/pickup/{pickUp}")
    ResponseEntity<List<Trip>> findAllByPickUp(@PathVariable String pickUp){
        List<Trip> trips = tripService.findAllByPickUp(pickUp);
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/destination/{destination}")
    ResponseEntity<List<Trip>> findAllByDestination(@PathVariable String destination){
        List<Trip> trips = tripService.findAllByDestination(destination);
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/departure/{departure}")
    ResponseEntity<List<Trip> >findAllByDeparture(@PathVariable String departure){
        List<Trip> trips = tripService.findAllByDeparture(departure);
        return ResponseEntity.ok(trips);
    }


    // PUT (Update Trip)

    @PutMapping("/update/{tripId}")
    ResponseEntity<String> update(@Valid @RequestBody Trip updatedTrip, @PathVariable Integer tripId) throws Exception {
        tripService.update(updatedTrip, tripId);
        return ResponseEntity.ok().body("Trip successfully updated. ID has been changed!");
    }


    // DELETE (Delete Trip)

    @DeleteMapping("/delete/{tripId}")
    ResponseEntity<Void> delete(@PathVariable Integer tripId) throws Exception {
        tripService.delete(tripId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
