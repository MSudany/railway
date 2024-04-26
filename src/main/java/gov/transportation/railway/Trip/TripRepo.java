package gov.transportation.railway.Trip;

import gov.transportation.railway.Enum.Class;
import gov.transportation.railway.Enum.Location;
import gov.transportation.railway.Record.Trip;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class TripRepo {

    private List<Trip> trips = new ArrayList<>();

    //CREATE OPERATION
    void createTrip(Trip trip){
        trips.add(trip);
    }

    //READ OPERATIONS

    public List<Trip> findAll(){
        return trips;
    }

    public List<Trip> findTrips(LocalDateTime departure, Location pickUp, Location destination) {
        List<Trip> matchingTrips = new ArrayList<>();
        for (Trip trip : trips) {
            if (trip.departure().equals(departure) &&
                    trip.pickUp().equals(pickUp) &&
                    trip.destination().equals(destination)) {
                matchingTrips.add(trip);
            }
        }
        return matchingTrips;
    }

    public Optional<Trip> findById(int tripId){
        return  trips.stream()
                .filter(trip -> trip.tripId() == tripId)
                .findFirst();
    }

    public List<Trip> findByDate(LocalDateTime departure) {
        List<Trip> tripsOnDate = new ArrayList<>();
        for (Trip trip : trips) {
            if (trip.departure().isEqual(departure)) {
                tripsOnDate.add(trip);
            }
        }
        return tripsOnDate;
    }

    public List<Trip> findByPickUp(Location pickUp) {
        List<Trip> tripsAtPickUp = new ArrayList<>();
        for (Trip trip : trips) {
            if (trip.pickUp().equals(pickUp)) {
                tripsAtPickUp.add(trip);
            }
        }
        return tripsAtPickUp;
    }

    public List<Trip> findByDestination(Location destination) {
        List<Trip> tripsToDestination = new ArrayList<>();
        for (Trip trip : trips) {
            if (trip.destination().equals(destination)) {
                tripsToDestination.add(trip);
            }
        }
        return tripsToDestination;
    }

    //UPDATE OPERATION

    void updateTrip(Trip trip, int tripId){
        Optional<Trip> existingTrip = findById(tripId);
        if(existingTrip.isPresent()){
            trips.set(trips.indexOf(existingTrip.get()), trip);
        }
    }

    //DELETE OPERATION
    void deleteTrip(int tripId){
        trips.removeIf(trip -> trip.tripId() == tripId);
    }

    //INITIALIZATION
    @PostConstruct
    private void init() {
        Random random = new Random();
        LocalDateTime now = LocalDateTime.now();
        int currentYear = now.getYear();
        int tripIdCounter = 1; // Start tripId counter from 1
        for (int i = 0; i < 200; i++) {
            Location pickUp = Location.values()[random.nextInt(Location.values().length)];
            Location destination;
            do {
                destination = Location.values()[random.nextInt(Location.values().length)];
            } while (destination == pickUp); // Ensure pickUp is different from destination

            int month = 4 + random.nextInt(5); // April to August
            int day = 1 + random.nextInt(30); // Any day within the month
            int hour = 7 + random.nextInt(6); // Exact hour (7, 8, ..., 12)

            LocalDateTime departure = LocalDateTime.of(currentYear, month, day, hour, 0); // No minutes or seconds
            double price = Math.round((50 + random.nextDouble() * 300) * 100.0) / 100.0; // Round price to 2 decimal places
            Class nClass = Class.values()[random.nextInt(Class.values().length)];

            // Generate unique tripId
            int tripId = tripIdCounter++;

            Trip trip = new Trip(tripId, pickUp, destination, departure, price, nClass);
            trips.add(trip);
        }
    }




}
