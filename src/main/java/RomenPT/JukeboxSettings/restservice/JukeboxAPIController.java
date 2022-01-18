package RomenPT.JukeboxSettings.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class JukeboxAPIController {

    @GetMapping("/jukebox")
    public ArrayList<Jukebox> jukebox(@RequestParam(value = "settingId") String settingId,
                                      @RequestParam(value = "model", defaultValue = "") String model,
                                      @RequestParam(value = "offset", defaultValue = "") int offset,
                                      @RequestParam(value = "limit", defaultValue = "") int limit){
        return new ArrayList<Jukebox>();
    }
}
