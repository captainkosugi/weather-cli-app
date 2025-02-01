package weather;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ResourceBundle;


public class Coordinates {

   private float latitude;
   private float longitude;
   private final ResourceBundle bundle = ResourceBundle.getBundle("application");
   private final String GEOCODER_URL = bundle.getString("url.geocoder");


   public String getLocationInfo(String address) throws IOException {
       OkHttpClient client = new OkHttpClient();
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
