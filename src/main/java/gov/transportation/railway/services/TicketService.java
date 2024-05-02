package gov.transportation.railway.services;

import gov.transportation.railway.domain.entities.TicketEntity;
import gov.transportation.railway.domain.entities.TripEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TicketService {

    TicketEntity save(TicketEntity ticket);

    List<TicketEntity> findAll();

    Optional<TicketEntity> findOne(Long id);

    boolean isExist(Long id);

    void delete(Long id);

//    boolean seatExists(Long seatNumber);
}
