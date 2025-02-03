package weather.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiClient {

   private final OkHttpClient client;

    public ApiClient(OkHttpClient client) {
        this.client = new OkHttpClient();
    }

    public String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Request API error " + response.code());
            }
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
