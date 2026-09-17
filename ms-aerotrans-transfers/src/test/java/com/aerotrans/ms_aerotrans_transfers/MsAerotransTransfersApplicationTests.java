package com.aerotrans.ms_aerotrans_transfers;

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
class MsAerotransTransfersApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void transferLifecycleShouldFilterByClientAndUpdateStatus() throws Exception {
		String payload = """
				{"origen":"SCL","destino":"AEROPUERTO","zonaId":10,"categoriaId":2,"fechaServicio":"2026-09-20T10:00:00","clienteOid":"client-oid"}
				""";

		String created = mockMvc.perform(post("/api/transfers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(status().isCreated())
				.andReturn().getResponse().getContentAsString();

		assertThat(created).contains("\"clienteOid\":\"client-oid\"")
				.contains("\"estado\":\"PENDIENTE\"");

		String transfers = mockMvc.perform(get("/api/transfers")
				.queryParam("cliente_oid", "client-oid"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertThat(transfers).contains("client-oid");
		Long id = 1L;

		String updated = mockMvc.perform(put("/api/transfers/{id}/status", id)
				.queryParam("status", "CONFIRMADO"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertThat(updated).contains("\"estado\":\"CONFIRMADO\"");
	}

	@Test
	void unknownTransferShouldReturnNotFound() throws Exception {
		mockMvc.perform(get("/api/transfers/999"))
				.andExpect(status().isNotFound());
	}

}
