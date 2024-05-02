package gov.transportation.railway.services;

import gov.transportation.railway.domain.entities.TripEntity;
import gov.transportation.railway.repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TripService {

    TripEntity save(TripEntity tripEntity);

    List<TripEntity> findAll();

    Optional<TripEntity> findOne(Long id);

    boolean isExist(Long id);

    void delete(Long id);
}
