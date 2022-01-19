package RomenPT.JukeboxSettings.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class JukeboxAPIController {

    @GetMapping("/jukebox")
    public ResponseEntity<String> jukebox(@RequestParam(value = "id") String settingId,
                                 @RequestParam(value = "model", defaultValue = "") String model,
                                 @RequestParam(value = "offset", defaultValue = "") String offset,
                                 @RequestParam(value = "limit", defaultValue = "") String limit) throws ExecutionException, JsonProcessingException, InterruptedException {
        Pattern pattern = Pattern.compile("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}");
        Matcher matcher = pattern.matcher(settingId);
        if(!matcher.find()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid setting id.");
        boolean modelSpecified = !model.equals("");
        List<JukeboxSetting> allSettings = MockAPICalls.getSettings();
        JukeboxSetting selectedSetting = null;
        boolean notInSettings = true;
        for (JukeboxSetting s : allSettings) {
            if(s.getId().equals(settingId)){
                notInSettings = false;
                selectedSetting = s;
                break;
            }
        }
        if(notInSettings){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No setting with matching id.");
        }
        List<Jukebox> allJukeboxes = MockAPICalls.getJukeboxes();
        if(!Jukebox.modelTypes.contains(model) && modelSpecified) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid model type.");
        List<String> selectedJukeboxes = new ArrayList<>();
        for (Jukebox j : allJukeboxes) {
            Set<String> jukeboxComponents = j.getComponents();
            if(!modelSpecified && jukeboxComponents.containsAll(selectedSetting.getRequirements())){
                selectedJukeboxes.add(j.getId());
            }
            else if(modelSpecified && j.getModel().equals(model) && jukeboxComponents.containsAll(selectedSetting.getRequirements())){
                selectedJukeboxes.add(j.getId());
            }
        }
        Collections.sort(selectedJukeboxes);
        boolean needLimit = !limit.equals("");
        boolean needOffset = !offset.equals("");
        if(needOffset){
            try{
                if(Integer.parseInt(offset) < 0){
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpStatus.NOT_ACCEPTABLE + ": Offset must greater than 0.");
                }
            }
            catch (Exception e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpStatus.NOT_ACCEPTABLE + ": Offset must be an integer.");
            }

        }
        if(needLimit){
            try{
                if(Integer.parseInt(limit) < 0){
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpStatus.NOT_ACCEPTABLE + ": Limit must greater than 0.");
                }
            }
            catch (Exception e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpStatus.NOT_ACCEPTABLE + ": Limit must be an integer.");
            }
        }
        if(needLimit && needOffset){
            try{
                int newOffset = Integer.parseInt(offset);
                int newLimit = Integer.parseInt(limit);
                return ResponseEntity.status(HttpStatus.OK).body(Arrays.toString(selectedJukeboxes.subList(newOffset, newLimit).toArray()));
            }
            catch(Exception e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpStatus.NOT_ACCEPTABLE + ": Limit or offset is greater than total amount of jukeboxes.");
            }

        }
        if(needLimit){
            try{
                int newLimit = Integer.parseInt(limit);
                return ResponseEntity.status(HttpStatus.OK).body(Arrays.toString(selectedJukeboxes.subList(0, newLimit).toArray()));
            }
            catch(Exception e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpStatus.NOT_ACCEPTABLE + ": Limit is greater than total amount of jukeboxes.");
            }
        }
        if(needOffset){
            try{
                int newOffset = Integer.parseInt(offset);
                return ResponseEntity.status(HttpStatus.OK).body(Arrays.toString(selectedJukeboxes.subList(newOffset, selectedJukeboxes.size()).toArray()));
            }
            catch(Exception e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpStatus.NOT_ACCEPTABLE + ": Offset is greater than total amount of jukeboxes.");
            }

        }
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.toString(selectedJukeboxes.toArray()));
    }
}
