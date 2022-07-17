package com.bokduck.room;

import java.net.http.HttpHeaders;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bokduck.config.BokduckApplication;

@DisplayName("Room Controller")
@SpringBootTest(classes = {BokduckApplication.class})
@AutoConfiguration
@Transactional
public class RoomControllerTest {

//	@Autowired
//	MockMvc mockMvc;
//	@Autowired
//	ObjectMapper objectMapper;

	private static HttpHeaders authHeaders;
	private static HttpHeaders adminHeaders;
	private final String domainUrl = "/api";

}
