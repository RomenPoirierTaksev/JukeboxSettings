package RomenPT.JukeboxSettings.restservice;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Dictionary;


public class Jukebox {

    private final String id;
    private final String model;
    private final Dictionary<String, String> components;

    public Jukebox(@JsonProperty("id") String id,@JsonProperty("model") String model,@JsonProperty("components") Dictionary<String, String> components) {
        this.id = id;
        this.model = model;
        this.components = components;
    }

    public String getId() {
        return id;
    }

    public String getModel(){
        return model;
    }

    public Dictionary<String, String> getComponents(){
        return components;
    }

}
