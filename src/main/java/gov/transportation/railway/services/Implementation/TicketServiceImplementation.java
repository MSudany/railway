package gov.transportation.railway.services.Implementation;

import gov.transportation.railway.domain.dto.TripDto;
import gov.transportation.railway.domain.entities.TicketEntity;
import gov.transportation.railway.domain.entities.TripEntity;
import gov.transportation.railway.repositories.TicketRepository;
import gov.transportation.railway.services.TicketService;
import gov.transportation.railway.services.TripService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TicketServiceImplementation implements TicketService {

    private TicketRepository ticketRepository;

    TicketServiceImplementation(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TicketEntity save(TicketEntity ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<TicketEntity> findAll() {
        return StreamSupport
                .stream(ticketRepository
                        .findAll()
                        .spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TicketEntity> findOne(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return ticketRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

//    @Override
//    public boolean seatExists(Long seatNumber) {
//        return ticketRepository.seatNumberExists(seatNumber);
//    }
}
