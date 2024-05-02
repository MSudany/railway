package gov.transportation.railway.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor // Used by Jackson to create a java object
@Builder
public class TripDto {

    private Long id;
    private String pick_up_location;
    private String destination_location;
    private LocalDateTime departure;
    private Float price;
    private String n_class;
}
