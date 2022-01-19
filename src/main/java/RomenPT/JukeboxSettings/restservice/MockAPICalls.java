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
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * A method to get the body of an API call response from a specified URI
     * @param uri URI to make call to
     * @return body of the response
     * @throws ExecutionException if this future completed exceptionally
     * @throws InterruptedException if the current thread was interrupted while waiting
     */
    private static String getApiResponseBody(String uri) throws ExecutionException, InterruptedException {

        //create new HTTP client
        var client = HttpClient.newHttpClient();

        //create new HTTP request to jukes API
        var request = HttpRequest.newBuilder(
                        URI.create(uri))
                .header("accept", "application/json")
                .build();

        //make asynchronous call to API
        var responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        //get response body
        return responseFuture.get().body();
    }
    /**
     * Getter for all the jukeboxes returned by the API
     * @return a list of all jukeboxes returned by the API
     * @throws ExecutionException if this future completed exceptionally
     * @throws InterruptedException if the current thread was interrupted while waiting
     * @throws JsonProcessingException if there is a problem generating or parsing JSON
     */
    public static List<Jukebox> getJukeboxes() throws JsonProcessingException, ExecutionException, InterruptedException {

        //convert body to jukeboxes
        List<Jukebox> allJukeboxes = objectMapper.readValue(getApiResponseBody("http://my-json-server.typicode.com/touchtunes/tech-assignment/jukes"), new TypeReference<>(){});

        //Create a set of all components of the jukebox, and set it
        for (Jukebox j : allJukeboxes) {

            //convert component object to json and then into Hashmaps
            String componentJson = objectMapper.writeValueAsString(j.getComponentObject());
            List<HashMap<String, String>> allComponents = objectMapper.readValue(componentJson, new TypeReference<>(){});

            //create set of all components, no duplicates
            Set<String> componentNames = new TreeSet<>();
            for (HashMap<String, String> h : allComponents) {
                componentNames.add(h.get("name"));
            }
            j.setComponents(componentNames);
        }
        return allJukeboxes;
    }

    /**
     * Getter for all the settings available to the jukeboxes
     * @return a list of all the settings available to the jukeboxes
     * @throws ExecutionException if this future completed exceptionally
     * @throws InterruptedException if the current thread was interrupted while waiting
     * @throws JsonProcessingException if there is a problem generating or parsing JSON
     */
    public static List<JukeboxSetting> getSettings() throws ExecutionException, InterruptedException, JsonProcessingException {

        //convert body to a list of settings
        HashMap<String, List<Object>> settingMap = objectMapper.readValue(getApiResponseBody("http://my-json-server.typicode.com/touchtunes/tech-assignment/settings"), new TypeReference<>(){});
        List<JukeboxSetting> allSettings =  objectMapper.readValue(objectMapper.writeValueAsString(settingMap.get("settings")), new TypeReference<>(){});

        //create a set of all requirements and set it to the setting, no duplicates
        for (JukeboxSetting s : allSettings) {
            String requirementJson = objectMapper.writeValueAsString(s.getRequirementObject());
            Set<String> settingNames = objectMapper.readValue(requirementJson, new TypeReference<>(){});
            s.setRequirements(settingNames);
        }
        return allSettings;
    }


}
