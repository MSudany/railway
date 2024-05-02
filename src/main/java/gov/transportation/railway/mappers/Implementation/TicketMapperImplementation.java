package gov.transportation.railway.mappers.Implementation;

import gov.transportation.railway.domain.dto.TicketDto;
import gov.transportation.railway.domain.entities.TicketEntity;
import gov.transportation.railway.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketMapperImplementation implements Mapper<TicketEntity, TicketDto> {

    private ModelMapper modelMapper;

    public TicketMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public TicketDto mapTo(TicketEntity ticketEntity) {
        return modelMapper.map(ticketEntity, TicketDto.class);
    }

    @Override
    public TicketEntity mapFrom(TicketDto ticketDto) {
        return modelMapper.map(ticketDto, TicketEntity.class);
    }
}
