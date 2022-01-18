package RomenPT.JukeboxSettings.restservice;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

public class Jukebox {

    private final String id;
    private final String model;
    private final Object componentObject;
    private Set<String> components;

    /**
     * JukeBox constructor
     * @param id the id of the jukebox
     * @param model the model of the jukebox
     * @param componentObject the components of the jukebox
     */
    public Jukebox(@JsonProperty("id") String id,@JsonProperty("model") String model,@JsonProperty("components") Object componentObject) {
        this.id = id;
        this.model = model;
        this.componentObject = componentObject;
    }

    /**
     * Getter for the id of the jukebox
     * @return jukebox id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for the model of the jukebox
     * @return jukebox model
     */
    public String getModel(){
        return model;
    }

    /**
     * Getter for the component object of the jukebox
     * @return jukebox component object
     */
    public Object getComponentObject(){
        return componentObject;
    }

    /**
     * Getter for the components of the jukebox
     * @return jukebox components
     */
    public Set<String> getComponents(){
        return components;
    }

    /**
     * Sets the components of the jukebox
     * @param c a set of all components of the jukebox
     */
    public void setComponents(Set<String> c){
        this.components = c;
    }
}
