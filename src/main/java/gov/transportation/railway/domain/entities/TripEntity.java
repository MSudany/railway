package gov.transportation.railway.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trips")
public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_id_seq")
    private Long id;
    private String pick_up_location;
    private String destination_location;
    private LocalDateTime departure;
    private Float price;
    private String n_class;

}
