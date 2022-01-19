package RomenPT.JukeboxSettings.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class JukeboxSettingsApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException, JsonProcessingException{

		//SpringApplication.run(JukeboxSettingsApplication.class, args);
		for (JukeboxSetting s : MockAPICalls.getSettings()) {
			System.out.println(s.getId() + " " + s.getRequirements());
		}
	}

}
