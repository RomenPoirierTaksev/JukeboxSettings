package RomenPT.JukeboxSettings.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class JukeboxAPIController {

    @GetMapping("/jukebox")
    public List<Jukebox> jukebox(@RequestParam(value = "id") String settingId,
                                 @RequestParam(value = "model", defaultValue = "") String model,
                                 @RequestParam(value = "offset", defaultValue = "") String offset,
                                 @RequestParam(value = "limit", defaultValue = "") String limit) throws ExecutionException, JsonProcessingException, InterruptedException {
        return MockAPICalls.getJukeboxes();
    }

    @GetMapping("/settings")
    public List<JukeboxSetting> settings() throws ExecutionException, InterruptedException, JsonProcessingException {
        return MockAPICalls.getSettings();
    }
}
