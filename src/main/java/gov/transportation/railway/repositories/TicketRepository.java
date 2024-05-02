package gov.transportation.railway.repositories;

import gov.transportation.railway.domain.entities.TicketEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, Long> {
//    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM TicketEntity t WHERE t.seatNumber = :seatNumber")
//    boolean seatNumberExists(@Param("seatNumber") Long seatNumber);
}
