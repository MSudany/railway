package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class JsonDataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(JsonDataLoader.class);
    @Autowired
    private final TripRepo tripRepo;
    @Autowired
    private final ObjectMapper objectMapper;

    public JsonDataLoader(TripRepo tripRepo, ObjectMapper objectMapper) {
        this.tripRepo = tripRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (tripRepo.count() == 0) {
            try (InputStream inputStream = JsonDataLoader.class.getResourceAsStream("/data/trips.json")) {
                List<Trip> allTrips = objectMapper.readValue(inputStream, new TypeReference<List<Trip>>() {});
                logger.info("Reading {} trips from JSON data and saving to the database.", allTrips.size());
                tripRepo.saveAll(allTrips);
            } catch (IOException e) {
                logger.error("Failed to read JSON data", e);
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            logger.info("Not loading trips from JSON data because the database already contains data.");
        }
    }
}
