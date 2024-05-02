package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonDataSaver {

//    private static final Logger logger = LoggerFactory.getLogger(JsonDataSaver.class);
//
//    @Autowired
//    private final ObjectMapper objectMapper;
//
//    @Autowired
//    private final TripRepo tripRepo;
//
//    public JsonDataSaver(ObjectMapper objectMapper, TripRepo tripRepo) {
//        this.objectMapper = objectMapper;
//        this.tripRepo = tripRepo;
//    }
//
//    @PreDestroy
//    public void saveDataToJson() {
//        List<Trip> trips = tripRepo.findAll();
//        File jsonFile = new File("src/main/resources/data/trips.json");
//
//        try {
//            objectMapper.writeValue(jsonFile, trips);
//            logger.info("Data saved to trips.json successfully.");
//        } catch (IOException e) {
//            logger.error("Failed to save data to trips.json: {}", e.getMessage(), e);
//        }
//    }
}

