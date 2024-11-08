package com.kh.damgarak.tableReservation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.kh.damgarak.tableReservation.choiceTableReservation.model.dto.ChoiceTableReservationDTO;
import com.kh.damgarak.tableReservation.service.TableReservationService;

@WebMvcTest(TableReservationController.class)
public class TableReservationControllerTest {
	
	@MockBean
	private TableReservationService tableReservationService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void tableReservationTest() throws Exception {
		ChoiceTableReservationDTO data = new ChoiceTableReservationDTO();
		
		mockMvc.perform(
				post("/tableReservation")
					.content(new Gson().toJson(data))
			).andExpect(status().isOk());
	}
	
}
