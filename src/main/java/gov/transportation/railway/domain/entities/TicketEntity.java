package gov.transportation.railway.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @SequenceGenerator(name="seq",sequenceName="ticket_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    private Long id;

    @Column(unique = true)
    private Long seat_number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id")
    private TripEntity trip;

}
