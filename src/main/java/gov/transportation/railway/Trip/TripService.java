package gov.transportation.railway.Trip;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TripService {

    private final TripRepo tripRepo;

    public TripService(TripRepo tripRepo) {
        this.tripRepo = tripRepo;
    }

    // POST (Create Trip)

    void create(Trip trip) throws Exception {
        try {
            tripRepo.save(trip);
        } catch (Exception ex) {
            // Log the error or perform any necessary error handling
            throw new Exception("Failed to create trip", ex);
        }
    }


    // READ OPERATIONS

    List<Trip> findAll(){
        return tripRepo.findAll();
    }

    Trip findById(Integer tripId) {
        return tripRepo.findById(tripId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    List<Trip> findAllByPickUp(String pickUp){
        return tripRepo.findAllByPickUp(pickUp);
    }

    List<Trip> findAllByDestination(String destination){
        return tripRepo.findAllByDestination(destination);
    }

    List<Trip> findAllByDeparture(String departure){
        return tripRepo.findAllByDeparture(departure);
    }


    // PUT (Update Trip)

    public Integer update(Trip updatedTrip, Integer tripId) throws Exception {
        // Check if the trip with the specified ID exists in the database
        if (!tripRepo.existsById(tripId)) {
            throw new Exception("Trip with ID " + tripId + " not found");
        }

        try {
            // Delete the existing trip with the given ID
            tripRepo.deleteById(tripId);
            // Save the updated trip as a new record
            tripRepo.save(updatedTrip);
        } catch (Exception ex) {
            throw new Exception("Failed to update trip", ex);
        }
        return updatedTrip.tripId();
    }


    // DELETE (Delete Trip)

    void delete(Integer tripId) throws Exception {
        try{
            tripRepo.delete(tripRepo.findById(tripId).get());
        } catch (Exception ex) {
            throw new Exception("Failed to delete trip", ex);
        }
    }
}
