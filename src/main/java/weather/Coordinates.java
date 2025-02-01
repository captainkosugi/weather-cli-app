package weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class Coordinates {

   private float latitude;
   private float longitude;
   private final ResourceBundle bundle = ResourceBundle.getBundle("application");
   private final String GEOCODER_URL = bundle.getString("url.geocoder");
   private final OkHttpClient client = new OkHttpClient();
   private final ObjectMapper mapper = new ObjectMapper();


   public String getLocationInfo(String address) throws IOException {
       Request request = new Request.Builder()
               .url(GEOCODER_URL
               + "?apikey=" + bundle.getString("api.geocoder")
               + "&geocode=" + address
               + "&format=json")
               .build();

       try (Response response = client.newCall(request).execute()){
           return response.body().string();
       }
   }

   public Map<String, Float> getCoordinates(String locationInfo) throws JsonProcessingException {
       Map<String, Float> location = new HashMap<>();
       JsonNode node = mapper.readTree(locationInfo);
       String coordinates = node.get("response")
               .get("GeoObjectCollection")
               .get("featureMember")
               .get(0)
               .get("GeoObject")
               .get("Point")
               .get("pos").asText();

       setLatitude(Float.parseFloat(coordinates.split(" ")[0]));
       setLongitude(Float.parseFloat(coordinates.split(" ")[1]));

       location.put("latitude", getLatitude());
       location.put("longitude", getLongitude());
       return location;
   }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
