package gov.transportation.railway.mappers.Implementation;

import gov.transportation.railway.domain.dto.TripDto;
import gov.transportation.railway.domain.entities.TripEntity;
import gov.transportation.railway.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TripMapperImplementation implements Mapper<TripEntity, TripDto> {

    private ModelMapper modelMapper;

    public TripMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TripDto mapTo(TripEntity tripEntity) {
        return modelMapper.map(tripEntity, TripDto.class);
    }

    @Override
    public TripEntity mapFrom(TripDto tripDto) {
        return modelMapper.map(tripDto, TripEntity.class);
    }
}
