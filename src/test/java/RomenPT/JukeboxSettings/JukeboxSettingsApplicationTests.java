package RomenPT.JukeboxSettings;

import RomenPT.JukeboxSettings.restservice.JukeboxAPIController;
import RomenPT.JukeboxSettings.restservice.JukeboxSetting;
import RomenPT.JukeboxSettings.restservice.JukeboxSettingsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes=JukeboxSettingsApplication.class)
@AutoConfigureMockMvc
public class JukeboxSettingsApplicationTests{

	@Autowired
	private JukeboxAPIController controller;

	@Test
	void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

	@Autowired
	private MockMvc mvc;
	@Test
	public void idIsValid() throws Exception {
		String id = "e9869bbe-887f-4d0a-bb9d-b81eb55fbf0a";
		this.mvc.perform(get("/jukebox?id="+id)).andExpect(status().isOk());
	}
	@Test
	public void idNotLongEnough() throws Exception{
		String id = "e9869bbe-887f-4d0a-bb9d-b81eb55fbf0";
		this.mvc.perform(get("/jukebox?id="+id)).andExpect(status().isNotFound());
	}
	@Test
	public void idHasNonAlphanumericCharacters() throws Exception{
		String id = "e9869bbe-887f-4d0a-bb9d-b81eb55fbf#";
		this.mvc.perform(get("/jukebox?id="+id)).andExpect(status().isNotFound());
	}
	@Test
	public void idHasWrongFormat() throws Exception{
		String id = "e9869bbe-887f-4d0a-bb9db81eb55fbf0";
		this.mvc.perform(get("/jukebox?id="+id)).andExpect(status().isNotFound());
	}



}
