package gov.transportation.railway.services.Implementation;

import gov.transportation.railway.domain.entities.TripEntity;
import gov.transportation.railway.repositories.TripRepository;
import gov.transportation.railway.services.TripService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TripServiceImplementation implements TripService {

    private TripRepository tripRepository;

    public TripServiceImplementation(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public TripEntity save(TripEntity tripEntity) {
        return tripRepository.save(tripEntity);
    }

    @Override
    public List<TripEntity> findAll() {
        return StreamSupport.stream(tripRepository
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TripEntity> findOne(Long id) {
        return tripRepository.findById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return tripRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        tripRepository.deleteById(id);
    }


}
