package RomenPT.JukeboxSettings.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
        List<JukeboxSetting> allSettings = MockAPICalls.getSettings();
        boolean notInSettings = true;
        for (JukeboxSetting j : allSettings) {
            if(!j.getId().equals(settingId)){
                notInSettings = false;
                break;
            }
        }
        if(notInSettings){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No setting with matching id.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(MockAPICalls.getJukeboxes().toString());
    }

    @GetMapping("/settings")
    public List<JukeboxSetting> settings() throws ExecutionException, InterruptedException, JsonProcessingException {
        return MockAPICalls.getSettings();
    }
}
