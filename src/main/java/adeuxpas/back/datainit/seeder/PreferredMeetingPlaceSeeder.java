package adeuxpas.back.datainit.seeder;

import adeuxpas.back.entity.PreferredMeetingPlace;
import adeuxpas.back.entity.User;
import adeuxpas.back.repository.PreferredMeetingPlaceRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

/**
 * A component responsible for generating preferred meeting places for a user
 * by fetching addresses from the data.gouv.fr API, based on the user's postal code.
 */
@Component
public class PreferredMeetingPlaceSeeder {

    private final PreferredMeetingPlaceRepository preferredMeetingPlaceRepository;
    private final Logger logger = LoggerFactory.getLogger(PreferredMeetingPlaceSeeder.class);


    /**
     * Constructs a new PreferredMeetingPlaceSeeder with the specified PreferredMeetingPlaceRepository.
     * @param preferredMeetingPlaceRepository The repository for preferred meeting places.
     */
    public PreferredMeetingPlaceSeeder(@Autowired PreferredMeetingPlaceRepository preferredMeetingPlaceRepository){
        this.preferredMeetingPlaceRepository = preferredMeetingPlaceRepository;
    }

    /**
     * Fetches addresses from the data.gouv.fr api and returns them as a list of PreferredMeetingPlace objects.
     */
    public void generatePreferredMeetingPlacesForUser(User user){
        String userPostalCode = user.getPostalCode();
        try {
            // Construct URI for API endpoint
            URI uri = new URI("https://api-adresse.data.gouv.fr/search/?q=8+bd+du+port&postcode=" + userPostalCode);

            // Open connection to the API
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Check the response code
            int responsecode = connection.getResponseCode();

            if (responsecode != 200) {
                logger.atError().log("HTTP request failed with response code: " + responsecode);
            } else {
                // Simplify reading text from a character input stream
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                // String concatenation
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Parse JSON response
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(response.toString());
                JsonNode addresses = jsonResponse.get("features");
                for (JsonNode address : addresses) {
                    JsonNode addressDetails = address.get("properties");

                    // Extract data and create PreferredMeetingPlace object
                    String name = addressDetails.get("street").asText();
                    String street = addressDetails.get("name").asText();
                    String city = addressDetails.get("city").asText();
                    String postalCode = addressDetails.get("postcode").asText();

                    PreferredMeetingPlace preferredMeetingPlace = new PreferredMeetingPlace(name, street, city, postalCode, user);
                    this.preferredMeetingPlaceRepository.save(preferredMeetingPlace);
                }
            }
        } catch (Exception e) {
            logger.atError().log("Error occurred while fetching data from the API: " + e.getMessage());
        }
    }
}
