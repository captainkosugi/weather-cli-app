package weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import weather.api.ApiClient;
import weather.dto.CoordinatesDTO;

import java.io.IOException;
import java.util.ResourceBundle;


public class Coordinates {

   private final ResourceBundle bundle = ResourceBundle.getBundle("application");
   private final String GEOCODER_URL = bundle.getString("url.geocoder");
   private final ApiClient client;
   private final ObjectMapper mapper;


    public Coordinates(ApiClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public String getLocationInfo(String address) throws IOException {
       String url = GEOCODER_URL
               + "?apikey=" + bundle.getString("api.geocoder")
               + "&geocode=" + address
               + "&format=json";

       return client.get(url);
   }

   public CoordinatesDTO getCoordinates(String locationInfo) throws JsonProcessingException {
       JsonNode node = mapper.readTree(locationInfo);
       JsonNode pointNode = node.at
               ("/response/GeoObjectCollection/featureMember/0/GeoObject/Point/pos");

       String[] coordinates = pointNode.asText().split(" ");
       return new CoordinatesDTO(Float.parseFloat(coordinates[0]),
               Float.parseFloat(coordinates[1]));
   }

}
