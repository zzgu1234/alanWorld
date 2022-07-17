package com.bokduck.api.user.application;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bokduck.api.user.application.dto.UserCreateDto;
import com.bokduck.api.user.application.dto.UserLoginDto;
import com.bokduck.api.user.domain.User;
import com.bokduck.api.user.infra.UserRepository;
import com.bokduck.api.user.ui.dto.UserLoginResponse;
import com.bokduck.component.JwtManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	@Autowired
    ObjectMapper objectMapper;

	@Autowired
	JwtManager jwt;


	private final UserRepository userRepository;
	private final BCryptPasswordEncoder pwEncoder;

	// 회원가입
	@Transactional
	public String regist(UserCreateDto createDto) throws Exception {

		User user = User.builder()
				.id(createDto.getId())
				.name(createDto.getName())
				.password(pwEncoder.encode(createDto.getPassword()))
				.email(createDto.getEmail())
				.mobile(createDto.getMobile())
				.build();

		Optional<User> info = userRepository.findById(createDto.getId());
		if( !info.isEmpty() ) {
			throw new Exception("Dupplicated id.");
		}

		if( user == null ) {
			throw new Exception("Can not Regist.");
		} else {
			userRepository.save(user);
			return createDto.getId();
		}
	}

	public UserLoginResponse login(UserLoginDto loginDto) throws Exception {

		Optional<User> info = userRepository.findById(loginDto.getId());
		if( pwEncoder.matches(loginDto.getPassword(), info.get().getPassword() ) == false ) {
			throw new Exception("Password is Different.");
		}

		Optional<User> loginInfo = userRepository.findByIdAndPassword(loginDto.getId(), pwEncoder.encode(loginDto.getPassword()));
		if( loginInfo == null ) {
			throw new Exception("Login Information not Found.");
		}

		Map<String, Object> tokenMap = objectMapper.convertValue(loginDto, Map.class);
		String accessToken = jwt.makeJwtString(tokenMap, "ACCESS");
		String tokenType = "bearer";

		Map<String, Object> result = new LinkedHashMap<String, Object>();
		result.put("accessToken", accessToken);
		result.put("tokenType", tokenType);


		return UserLoginResponse.builder()
				.id(loginDto.getId())
				.token(accessToken)
				.build();
	}

}
