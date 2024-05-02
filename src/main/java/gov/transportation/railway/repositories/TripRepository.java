package gov.transportation.railway.repositories;

import gov.transportation.railway.domain.entities.TripEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TripRepository extends CrudRepository<TripEntity, Long> {
    Iterable<TripEntity> departureIsAfter(LocalDateTime departure);
}
