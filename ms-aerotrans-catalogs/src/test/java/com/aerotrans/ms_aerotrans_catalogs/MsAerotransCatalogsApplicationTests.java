package com.aerotrans.ms_aerotrans_catalogs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MsAerotransCatalogsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void categoryLifecycleShouldWork() throws Exception {
		String payload = """
				{"nombre":"SUV","capacidadPasajeros":4,"capacidadMaletas":3}
				""";

		String created = mockMvc.perform(post("/api/catalog/categories")
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(status().isCreated())
				.andReturn().getResponse().getContentAsString();

		assertThat(created).contains("\"nombre\":\"SUV\"");

		String categories = mockMvc.perform(get("/api/catalog/categories"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertThat(categories).contains("SUV");
	}

	@Test
	void updatingMissingCategoryShouldReturnNotFound() throws Exception {
		String payload = """
				{"nombre":"Van","capacidadPasajeros":8,"capacidadMaletas":6}
				""";

		mockMvc.perform(put("/api/catalog/categories/999")
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(status().isNotFound());
	}

}
