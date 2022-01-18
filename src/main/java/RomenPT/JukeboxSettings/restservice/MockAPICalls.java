package RomenPT.JukeboxSettings.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.ExecutionException;
public class MockAPICalls {

    //object mapper can JSON -> Java Object or Java Object -> JSON
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Getter for all the jukeboxes returned by the API
     * @return a list of all jukeboxes returned by the API
     * @throws ExecutionException if this future completed exceptionally
     * @throws InterruptedException if the current thread was interrupted while waiting
     * @throws JsonProcessingException if there is a problem generating or parsing JSON
     */
    public static List<Jukebox> getJukeboxes() throws ExecutionException, InterruptedException, JsonProcessingException {

        //create new HTTP client to make calls to
        var client = HttpClient.newHttpClient();

        //create new HTTP request to jukes API
        var request = HttpRequest.newBuilder(
                        URI.create("http://my-json-server.typicode.com/touchtunes/tech-assignment/jukes"))
                .header("accept", "application/json")
                .build();

        //make asynchronous call to API
        var responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        //get response body
        String response = responseFuture.get().body();

        //convert body to jukeboxes
        List<Jukebox> listJukebox = objectMapper.readValue(response, new TypeReference<>(){});

        for (Jukebox j : listJukebox) {

            //convert component object to json and then into Hashmaps
            Object components = j.getComponentObject();
            String componentJson = objectMapper.writeValueAsString(components);
            List<HashMap<String, String>> cc = objectMapper.readValue(componentJson, new TypeReference<>(){});

            //create set of all components, no duplicates
            Set<String> componentNames = new TreeSet<>();
            for (HashMap<String, String> h : cc) {
                componentNames.add(h.get("name"));
            }

            //set set of components to jukebox
            j.setComponents(componentNames);
        }
        return listJukebox;
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
