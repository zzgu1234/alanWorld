package com.bokduck.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class TestUtil {

	private static ObjectMapper objectMapper;

	public TestUtil(@Autowired ObjectMapper objectMapper) {

		objectMapper.registerModule(new JavaTimeModule());
		TestUtil.objectMapper = objectMapper;
	}

	public static <T> T resultToObject(MvcResult result, Class<T> valueType) throws Exception {
	    return objectMapper.readValue(result.getResponse().getContentAsString(), valueType);
	}

	public static <T> T resultToTypedObject(MvcResult result, TypeReference<T> typeReference) throws Exception{
		return objectMapper.readValue(
				result.getResponse().getContentAsString(), typeReference);
	}

	public static <T> List<T> resultToList(MvcResult result, Class<T> valueType) throws Exception {
		return objectMapper.readValue(result.getResponse().getContentAsString(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
	}

	public static String createAccessToken(MockMvc mockMvc, String param) throws Exception {
	    // when
		MvcResult result = mockMvc.perform(post("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(param))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andReturn();

	    Assertions.assertNotNull(result.getResponse().getContentAsString());

	    JSONObject loginInfo = new JSONObject(result.getResponse().getContentAsString());
	    Assertions.assertNotNull(loginInfo);

	    String assessToken = loginInfo.getJSONObject("result").getString("accessToken");
	    Assertions.assertNotNull(assessToken);

	    return "Bearer " + assessToken;
	  }

	public static HttpHeaders getLoginHeader(MockMvc mockMvc) throws Exception{
		String param = "{\"id\":\"ggzu1234\",\"pw\":\"abcd1234#\",\"loginKeep\":\"N\",\"ispc\":\"P\"}";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(TestProperties.AuthHeaderName, createAccessToken(mockMvc, param));

		return httpHeaders;
	  }

}
