package com.adidas.backend.publicservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.adidas.backend.publicservice.exceptions.ResponseErrorException;
import com.adidas.backend.publicservice.exceptions.SubscriptionExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

@ExtendWith(SpringExtension.class)
@WebMvcTest({SubscriptionRestController.class})
class SubscriptionRestControllerTest {

	private static final String mockedMail = "thecoolestshoesfromgamoraareadidas@adiclub.com";
	private static final String invalidMockedMail = "VenomIsDangerous";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SubscriptionRestController subscriptionRestController;

	@Test
	void testHappyPath() throws Exception {
		final ResultActions result =
				this.mockMvc.perform(
						post("/subscription")
								.content("{\"userMail\": \"" + mockedMail + "\"}")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}

	@Test
	void testInvalidInputParamNonValidMail() throws Exception {
		final ResultActions result =
				this.mockMvc.perform(
						post("/subscription")
								.content("{\"userMail\": \"" + invalidMockedMail + "\"}")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isBadRequest());
	}

	@Test
	void testInvalidInputParamNoMail() throws Exception {
		// No email
		final ResultActions result =
				this.mockMvc.perform(
						post("/subscription")
								.content("{\"userMail\": \"\"}")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isBadRequest());
	}

	@Test
	void testInvalidInputParamMailWithoutExtension() throws Exception {
		final ResultActions result =
				this.mockMvc.perform(
						post("/subscription")
								.content("{\"userMail\": \"" + mockedMail.replace(".com", "") + "\"}")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isBadRequest());
	}

	@Test
	void testEmptyInputParam() throws Exception {
		final ResultActions result =
				this.mockMvc.perform(
						post("/subscription")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isBadRequest());
	}

	@Test
	void changeName() throws Exception {
		final MockMvc customMockMvc = MockMvcBuilders.standaloneSetup(subscriptionRestController)
				.setControllerAdvice(new SubscriptionExceptionHandler())
				.addInterceptors(new AsyncHandlerInterceptor(){
					@Override
					public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handle){
						throw new ResponseErrorException("Test exception");
					}
				})
				.build();

		final ResultActions result =
				customMockMvc.perform(
						post("/subscription")
								.content("{\"userMail\": \"" + mockedMail + "\"}")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isUnprocessableEntity());
	}

}
