package com.bokduck.api.room;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.bokduck.api.room.application.dto.RoomCreateDto;
import com.bokduck.api.room.application.dto.RoomDto;
import com.bokduck.api.room.application.dto.RoomUpdateDto;
import com.bokduck.api.room.value.RoomType;
import com.bokduck.common.PageResult;
import com.bokduck.util.TestConfig;
import com.bokduck.util.TestUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("Room Controller")
@SpringBootTest(classes = {TestConfig.class})
@AutoConfiguration
@Transactional
public class RoomControllerTest {

	@Autowired(required = false)
	MockMvc mockMvc;
	@Autowired(required = false)
	ObjectMapper objectMapper;

	private static HttpHeaders authHeaders;
	private static HttpHeaders adminHeaders;
	private final String domainUrl = "/api";

	@BeforeAll
	static void getAccessToken(@Autowired MockMvc mockMvc) throws Exception {
		authHeaders = TestUtil.getLoginHeader(mockMvc);
	}

	@DisplayName("방 목록")
	@Test
	public void findAll() throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

	    MvcResult result = mockMvc.perform(get("/api/rooms")
	            .headers(authHeaders)
	            .contentType(MediaType.APPLICATION_JSON)
	            .params(params))
		        .andDo(print())
		        .andExpect(status().is2xxSuccessful())
		        .andReturn();
	    // then
	    var response = TestUtil.resultToTypedObject(
	        result, new TypeReference<PageResult<RoomDto>>() {});
	    Assertions.assertNotNull(response);

	}

	@DisplayName("방 상세")
	@Test
	public void find() throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

	    MvcResult result = mockMvc.perform(get("/api/room/5")
	            .headers(authHeaders)
	            .contentType(MediaType.APPLICATION_JSON)
	            .params(params))
		        .andDo(print())
		        .andExpect(status().is2xxSuccessful())
		        .andReturn();
	    // then
	    var response = TestUtil.resultToObject(result, RoomDto.class);
	    Assertions.assertNotNull(response);

	}

	@DisplayName("방 등록")
 	@Test
 	public void createRoom() throws Exception {
		// given
		RoomCreateDto createDto = RoomCreateDto.builder()
				.roomTitle("테스트 방제목")
				.roomLocation("서울시 서초구")
				.roomType(RoomType.TW)
				.build();

		// when
		Assertions.assertNotNull(createDto);
	}

	@DisplayName("방 수정")
 	@Test
 	public void editRoom() throws Exception {
		// given
		RoomUpdateDto editDto = RoomUpdateDto.builder()
				.roomTitle("테스트 방제목")
				.roomLocation("서울시 서초구")
				.roomType(RoomType.TW)
				.build();

		// when
		mockMvc.perform(patch(domainUrl)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(editDto))
	            .headers(adminHeaders))
	        	.andDo(print())
	        	.andExpect(status().is2xxSuccessful());
	}

	@DisplayName("방 삭제")
 	@Test
 	public void deleteRoom() throws Exception {
		// given
		RoomUpdateDto editDto = RoomUpdateDto.builder()
				.roomTitle("테스트 방제목")
				.roomLocation("서울시 서초구")
				.roomType(RoomType.TW)
				.build();

		// when
	    mockMvc.perform(delete(domainUrl+"/{roomNo}", 4)
	            .headers(adminHeaders))
		        .andDo(print())
		        .andExpect(status().is2xxSuccessful());
	}

}
