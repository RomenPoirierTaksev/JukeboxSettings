package RomenPT.JukeboxSettings.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MockAPICalls {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void getJukeboxes() throws ExecutionException, InterruptedException, JsonProcessingException {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                        URI.create("http://my-json-server.typicode.com/touchtunes/tech-assignment/jukes"))
                .header("accept", "application/json")
                .build();

        var responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        String response = responseFuture.get().body();

        String json = "[{ \"id\": \"5ca94a8ac470d3e47cd4713c\", \"model\": \"fusion\", \"components\": [ { \"name\": \"led_panel\"}, { \"name\": \"amplifier\"}]}]";

        List<Jukebox> listJukebox = objectMapper.readValue(json, new TypeReference<List<Jukebox>>(){});

        System.out.println(Arrays.toString(listJukebox.toArray()));
    }

    public static void getSettings() throws ExecutionException, InterruptedException {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                        URI.create("http://my-json-server.typicode.com/touchtunes/tech-assignment/settings"))
                .header("accept", "application/json")
                .build();

        var responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        var response = responseFuture.get();

        System.out.println(response.body());
    }


}
