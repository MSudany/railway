package gov.transportation.railway.Trip;

import gov.transportation.railway.Enum.Class;
import gov.transportation.railway.Enum.Location;
import gov.transportation.railway.Record.Trip;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class TripRepo {

    private static final Logger log = LoggerFactory.getLogger(TripRepo.class);
    private final JdbcClient jdbcClient;

    public TripRepo(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Trip> findAll(){
        return jdbcClient.sql("select * from trip")
                .query(Trip.class)
                .list();
    }

    public Optional<Trip> findById(Integer tripId) {
        return jdbcClient.sql("SELECT trip_id, pick_up, destination, departure, price, n_class FROM Trip WHERE trip_id = :tripId")
                .param("tripId", tripId)
                .query(Trip.class)
                .optional();
    }


    public void create(Trip trip) {
        var updated = jdbcClient.sql("INSERT INTO Trip(trip_id,pick_up,destination,departure,price,n_class) values(?,?,?,?,?,?)")
                .params(List.of(trip.tripId(),trip.pickUp().toString(),trip.destination().toString(),trip.departure(),trip.price(),trip.nClass().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create trip " + trip.tripId());
    }


    public void update(Trip trip, Integer trip_id) {
        var updated = jdbcClient.sql("update trip set pick_up = ?, destination = ?, departure = ?, price = ?, n_class = ? where trip_id = ?")
                .params(List.of(trip.pickUp().toString(),trip.destination().toString(),trip.departure(),trip.price(),trip.nClass().toString(), trip_id))
                .update();

        Assert.state(updated == 1, "Failed to update trip " + trip.tripId());
    }

    public void delete(Integer tripId) {
        var updated = jdbcClient.sql("delete from trip where trip_id = :tripId")
                .param("tripId", tripId)
                .update();

        Assert.state(updated == 1, "Failed to delete trip " + tripId);
    }

    public int count() {
        return jdbcClient.sql("select * from trip").query().listOfRows().size();
    }

    public void saveAll(List<Trip> trips) {
        trips.stream().forEach(this::create);
    }

//    public List<Trip> findByPickUp(String location) {
//        return jdbcClient.sql("select * from trip where pick_up = :location")
//                .param("location", location)
//                .query(Trip.class)
//                .list();
//    }
}
