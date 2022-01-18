package RomenPT.JukeboxSettings.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class JukeboxSettingsApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException, JsonProcessingException{

		//SpringApplication.run(JukeboxSettingsApplication.class, args);
		for (Jukebox j:
				MockAPICalls.getJukeboxes()) {
			System.out.println(j.getId() + " " + j.getModel() + " " + j.getComponents());
		}
	}

}
