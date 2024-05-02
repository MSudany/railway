package gov.transportation.railway.controllers;

import gov.transportation.railway.domain.dto.TripDto;
import gov.transportation.railway.domain.entities.TripEntity;
import gov.transportation.railway.mappers.Mapper;
import gov.transportation.railway.services.TripService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/trips")
public class TripController {

    private TripService tripService;

    private Mapper<TripEntity, TripDto> tripMapper;

    public TripController(TripService tripService, Mapper<TripEntity, TripDto> tripMapper){
        this.tripService = tripService;
        this.tripMapper = tripMapper;
    }


    // POST (Create Trip)

    @PostMapping("")
    public TripDto create(@Valid @RequestBody TripDto tripDto) throws Exception {
        TripEntity tripEntity = tripMapper.mapFrom(tripDto);
        TripEntity savedTripEntity = tripService.save(tripEntity);
        return tripMapper.mapTo(savedTripEntity);
    }


    // READ OPERATIONS

    @GetMapping("")
    ResponseEntity<List<TripDto>> findAll(){
        List<TripEntity> trips = tripService.findAll();
        return ResponseEntity.ok(trips.stream()
                .map(tripMapper::mapTo)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    ResponseEntity<TripDto> findById(@PathVariable Long id) {
        Optional<TripEntity> foundTrip = tripService.findOne(id);
        return foundTrip.map(tripEntity -> {
            TripDto tripDto = tripMapper.mapTo(tripEntity);
            return ResponseEntity.ok(tripDto);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @GetMapping("/pickup/{pickUp}")
//    ResponseEntity<List<Trip>> findAllByPickUp(@PathVariable String pickUp){
//        List<Trip> trips = tripService.findAllByPickUp(pickUp);
//        return ResponseEntity.ok(trips);
//    }
//
//    @GetMapping("/destination/{destination}")
//    ResponseEntity<List<Trip>> findAllByDestination(@PathVariable String destination){
//        List<Trip> trips = tripService.findAllByDestination(destination);
//        return ResponseEntity.ok(trips);
//    }
//
//    @GetMapping("/departure/{departure}")
//    ResponseEntity<List<Trip> >findAllByDeparture(@PathVariable String departure){
//        List<Trip> trips = tripService.findAllByDeparture(departure);
//        return ResponseEntity.ok(trips);
//    }
//

    // PUT (Update Trip)

    @PutMapping("/{id}")
    ResponseEntity<TripDto> fullUpdateTrip(@Valid @RequestBody TripDto tripDto,
                                           @PathVariable("id") Long id) throws Exception {
        if (!tripService.isExist(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tripDto.setId(id);
        TripEntity tripEntity = tripMapper.mapFrom(tripDto);
        TripEntity savedTripEntity = tripService.save(tripEntity);
        return new ResponseEntity<>(
                tripMapper.mapTo(savedTripEntity),
                HttpStatus.OK
        );
    }


    // DELETE (Delete Trip)

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTrip(@PathVariable("id") Long id) {
        tripService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
