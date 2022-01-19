package RomenPT.JukeboxSettings;

import RomenPT.JukeboxSettings.restservice.JukeboxAPIController;
import RomenPT.JukeboxSettings.restservice.JukeboxSetting;
import RomenPT.JukeboxSettings.restservice.JukeboxSettingsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
	@Test
	public void settingWithProvidedIdExists() throws Exception{
		String id = "e9869bbe-887f-4d0a-bb9d-b81eb55fbf0a";
		this.mvc.perform(get("/jukebox?id="+id)).andExpect(status().isOk());
	}
	@Test
	public void settingWithProvidedIdDoesNotExist() throws Exception{
		String id = "e9869bbe-887f-4d0a-bb9d-b81eb55fbf0c";
		this.mvc.perform(get("/jukebox?id="+id)).andExpect(status().isNotFound());
	}
	@Test
	public void modelIsValid() throws Exception{
		String id = "86506865-f971-496e-9b90-75994f251459";
		String model = "fusion";
		this.mvc.perform(get("/jukebox?id="+id+"&model="+model)).andExpect(status().isOk()).andDo(print());
	}
	@Test
	public void modelIsNotValid() throws Exception{
		String id = "e9869bbe-887f-4d0a-bb9d-b81eb55fbf0c";
		String model = "bottle";
		this.mvc.perform(get("/jukebox?id="+id+"&model="+model)).andExpect(status().isNotFound());
	}
	@Test
	public void getJukeboxesWithIdAndGetNothing() throws Exception{
		String id = "2f58dbd4-47cb-4eef-bb72-623f4aa4fe5d";
		this.mvc.perform(get("/jukebox?id="+id)).andExpect(content().string(containsString("[]")));
	}
	@Test
	public void getJukeboxesWithIdAndGetExpectedJukeboxes1() throws Exception{
		String id = "b43f247a-8478-4f24-8e28-792fcfe539f5";
		this.mvc.perform(get("/jukebox?id="+id)).andExpect(content().string(containsString("[5ca94a8ab592da6c6f2d562e]")));
	}
	@Test
	public void getJukeboxesWithIdAndGetExpectedJukeboxes2() throws Exception{
		String id = "86506865-f971-496e-9b90-75994f251459";
		this.mvc.perform(get("/jukebox?id="+id)).andExpect(content().string(containsString("[5ca94a8a0735998f945f7276, 5ca94a8a13385f0c82aa9f2e, 5ca94a8a1639eb9ea30609f0, 5ca94a8a18f5576210fd012e, 5ca94a8a1d1bc6d59afb9392, 5ca94a8a20905ffff6f0561c, 5ca94a8a2c516506b1f49500, 5ca94a8a3227b0a360f41078, 5ca94a8a4aeb7ab33a5e1047, 5ca94a8a59b8061f89644f43, 5ca94a8a72473ac501b99033, 5ca94a8a75c231bb18715112, 5ca94a8a77e20d15a7d16d0a, 5ca94a8a8b58770bb38055a0, 5ca94a8aa2330a0762019ac0, 5ca94a8aafb9d8c4e4fddf02, 5ca94a8ab2c1285e53a89991, 5ca94a8ab592da6c6f2d562e, 5ca94a8ac3f21c47a72473ec, 5ca94a8ac470d3e47cd4713c, 5ca94a8ac5f85b8a59f9e3c8, 5ca94a8acc046e7aa8040605, 5ca94a8acfdeb5e01e5bdbe8, 5ca94a8ad2d584257d25ae50, 5ca94a8ad82e60f2448d2fc9, 5ca94a8adb81479f94dda744, 5ca94a8ae2b3a4fb2f0cfd78, 5ca94a8af0853f96c44fa858, 5ca94a8af9985926172d6e8d, 5ca94a8afa2bc9887b28ce87]")));
	}
	@Test
	public void getJukeboxesWithIdAndGetExpectedJukeboxes3() throws Exception{
		String id = "3a6423cf-f226-4cb1-bf51-2954bc0941d1";
		this.mvc.perform(get("/jukebox?id="+id)).andExpect(content().string(containsString("[5ca94a8acc046e7aa8040605]")));
	}
	@Test
	public void getJukeboxesWithIdAndModelAndGetExpectedJukeboxes() throws Exception{
		String id = "86506865-f971-496e-9b90-75994f251459";
		String model = "fusion";
		this.mvc.perform(get("/jukebox?id="+id+"&model="+model)).andExpect(content().string(containsString("[5ca94a8a0735998f945f7276, 5ca94a8a3227b0a360f41078, 5ca94a8a59b8061f89644f43, 5ca94a8a75c231bb18715112, 5ca94a8ab2c1285e53a89991, 5ca94a8ab592da6c6f2d562e, 5ca94a8ac470d3e47cd4713c, 5ca94a8ad2d584257d25ae50, 5ca94a8adb81479f94dda744, 5ca94a8ae2b3a4fb2f0cfd78]")));
	}




}
