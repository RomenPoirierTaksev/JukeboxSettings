package RomenPT.JukeboxSettings.restservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

public class JukeboxSetting {

    private final String id;
    private Set<String> requirements;
    private final Object requirementObject;

    /**
     * Jukebox setting constructor
     * @param id the id of the setting
     * @param requirementObject the component requirements of the setting as an object
     */
    public JukeboxSetting(@JsonProperty("id") String id, @JsonProperty("requires") Object requirementObject){
        this.id = id;
        this.requirementObject = requirementObject;
    }

    /**
     * Getter for the id of the setting
     * @return the setting id as a string
     */
    public String getId(){
        return id;
    }

    /**
     * Getter for the component requirements of the setting
     * @return a list of component requirements as strings
     */
    public Set<String> getRequirements(){
        return requirements;
    }

    /**
     * Getter for the requirement object of the setting
     * @return the requirement object
     */
    public Object getRequirementObject(){
        return requirementObject;
    }

    /**
     * sets the requirements to the passed list
     * @param r a list of requirements as strings
     */
    public void setRequirements(Set<String> r){
        requirements = r;
    }

}
