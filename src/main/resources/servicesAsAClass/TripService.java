//package gov.transportation.railway.services;
//
//import gov.transportation.railway.domain.entities.TripEntity;
//import gov.transportation.railway.repositories.TripRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TripService {
//
//    private final TripRepository tripRepository;
//
//    public TripService(TripRepository tripRepository) {
//        this.tripRepository = tripRepository;
//    }
//
//    // POST (Create Trip)
//
//    public void create(TripEntity tripEntity) throws Exception {
//        try {
//            tripRepository.save(tripEntity);
//        } catch (Exception ex) {
//            // Log the error or perform any necessary error handling
//            throw new Exception("Failed to create trip", ex);
//        }
//    }
//
//
//    // READ OPERATIONS
//
//    List<Trip> findAll(){
//        return tripRepository.findAll();
//    }
//
//    Trip findById(Integer tripId) {
//        return tripRepository.findById(tripId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }
//
//    List<Trip> findAllByPickUp(String pickUp){
//        return tripRepository.findAllByPickUp(pickUp);
//    }
//
//    List<Trip> findAllByDestination(String destination){
//        return tripRepository.findAllByDestination(destination);
//    }
//
//    List<Trip> findAllByDeparture(String departure){
//        return tripRepository.findAllByDeparture(departure);
//    }
//
//
//    // PUT (Update Trip)
//
//    public Integer update(Trip updatedTrip, Integer tripId) throws Exception {
//        // Check if the trip with the specified ID exists in the database
//        if (!tripRepository.existsById(tripId)) {
//            throw new Exception("Trip with ID " + tripId + " not found");
//        }
//
//        try {
//            // Delete the existing trip with the given ID
//            tripRepository.deleteById(tripId);
//            // Save the updated trip as a new record
//            tripRepository.save(updatedTrip);
//        } catch (Exception ex) {
//            throw new Exception("Failed to update trip", ex);
//        }
//        return updatedTrip.tripId();
//    }
//
//
//    // DELETE (Delete Trip)
//
//    void delete(Integer tripId) throws Exception {
//        try{
//            tripRepository.delete(tripRepository.findById(tripId).get());
//        } catch (Exception ex) {
//            throw new Exception("Failed to delete trip", ex);
//        }
//    }
//}
