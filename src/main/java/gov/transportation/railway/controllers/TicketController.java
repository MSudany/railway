package gov.transportation.railway.controllers;

import gov.transportation.railway.domain.dto.TicketDto;
import gov.transportation.railway.domain.dto.TripDto;
import gov.transportation.railway.domain.entities.TicketEntity;
import gov.transportation.railway.domain.entities.TripEntity;
import gov.transportation.railway.mappers.Mapper;
import gov.transportation.railway.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private Mapper<TicketEntity, TicketDto> ticketMapper;
    private TicketService ticketService;

    public TicketController(Mapper<TicketEntity, TicketDto> ticketMapper, TicketService ticketService){
        this.ticketMapper = ticketMapper;
        this.ticketService = ticketService;
    }

    @PostMapping("")
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto){
//        if(ticketService.seatExists(ticketDto.getSeat_number())){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        TicketEntity ticketEntity = ticketMapper.mapFrom(ticketDto);
        TicketEntity savedTicketEntity = ticketService.save(ticketEntity);
        TicketDto savedTicketDto = ticketMapper.mapTo(savedTicketEntity);
        return new ResponseEntity<>(savedTicketDto, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<TicketDto> findAll(){
        List<TicketEntity> tickets = ticketService.findAll();
        return tickets.stream()
                .map(ticketMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable("id") Long id){
        Optional<TicketEntity> foundTicket = ticketService.findOne(id);
        return foundTicket.map(ticketEntity -> {
            TicketDto ticketDto = ticketMapper.mapTo(ticketEntity);
            return ResponseEntity.ok(ticketDto);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    ResponseEntity<TicketDto> fullUpdateTicket(@Valid @RequestBody TicketDto ticketDto,
                                           @PathVariable("id") Long id) throws Exception {
        if (!ticketService.isExist(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketDto.setId(id);
        TicketEntity ticketEntity = ticketMapper.mapFrom(ticketDto);
        TicketEntity savedTicketEntity = ticketService.save(ticketEntity);
        return new ResponseEntity<>(
                ticketMapper.mapTo(savedTicketEntity),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTicket(@PathVariable("id") Long id){
        ticketService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
