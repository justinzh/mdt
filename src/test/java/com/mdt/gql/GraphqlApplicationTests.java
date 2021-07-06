package com.mdt.gql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
// (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GraphqlApplicationTests {

	@Autowired
	MockMvc mvc;

	@Test
	void createPod() throws Exception {
		mvc.perform(post("/api/v1/pod").content(
				"{\"mutation\":\"{createPod(name:\"creation\", type:\"TEST\", description:\"unit test case for pod creation\") {id name type description creation_time}\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void updatePod() throws Exception {
		mvc.perform(post("/api/v1/pod").content(
				"{\"mutation\":\"{updatePod(id: \"12\", description:\"unit test case for pod update\") { id name type description creation_time }\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void deletePod() throws Exception {
		mvc.perform(post("/api/v1/pod").content("{\"mutation\":\"{deletePod(id: \"12\")}\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void searchPod() throws Exception {
		mvc.perform(post("/api/v1/pod").content("{\"mutation\":\"{searchPod(type: \"TEST\")}\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();
	}
}
