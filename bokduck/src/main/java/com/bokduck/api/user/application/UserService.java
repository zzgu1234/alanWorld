package com.bokduck.api.user.application;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bokduck.api.user.application.dto.UserCreateDto;
import com.bokduck.api.user.application.dto.UserLoginDto;
import com.bokduck.api.user.domain.User;
import com.bokduck.api.user.infra.UserRepository;
import com.bokduck.api.user.ui.dto.UserCreateResponse;
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
	public UserCreateResponse regist(UserCreateDto createDto) throws Exception {

		//암호화를 위한 설정
		User user = User.builder()
				.id(createDto.getId())
				.name(createDto.getName())
				.password(pwEncoder.encode(createDto.getPassword()))
				.email(createDto.getEmail())
				.mobile(createDto.getMobile())
				.build();

		// request값이 비어있을경우
		if( createDto.getId() == null
				|| createDto.getName() == null
				|| createDto.getPassword() == null
				|| createDto.getEmail() == null
				|| createDto.getMobile() == null
				) {

			return UserCreateResponse.builder()
					.id(createDto.getId())
					.code(HttpStatus.BAD_REQUEST.value())
					.msg("No Request Information.")
					.build();
		}

		Optional<User> info = userRepository.findById(createDto.getId());
		if( !info.isEmpty() ) {
			return UserCreateResponse.builder()
					.id(createDto.getId())
					.code(HttpStatus.CONFLICT.value())
					.msg("Dupplicated id.")
					.build();
		}

		if( user == null ) {
			return UserCreateResponse.builder()
					.id(createDto.getId())
					.code(HttpStatus.NO_CONTENT.value())
					.msg("Can not Regist.")
					.build();
		} else {
			userRepository.save(user);
			return UserCreateResponse.builder()
					.id(createDto.getId())
					.code(HttpStatus.CREATED.value())
					.msg("Success")
					.build();
		}
	}

	public UserLoginResponse login(UserLoginDto loginDto) throws Exception {

		// 로그인 입력값이 없을경우
		if( loginDto.getId() == null || loginDto.getPassword() == null ) {
			return UserLoginResponse.builder()
					.code(HttpStatus.BAD_REQUEST.value())
					.msg("No Request Information.")
					.build();
		}

		Optional<User> info = userRepository.findById(loginDto.getId());

		// 해당 ID가 존재하지 않을경우
		if( info.isEmpty() ) {
			return UserLoginResponse.builder()
					.code(HttpStatus.NOT_FOUND.value())
					.msg("User Id not Found.")
					.build();
		}

		// 비밀번호가 다를경우
		if( pwEncoder.matches(loginDto.getPassword(), info.get().getPassword() ) == false ) {
			return UserLoginResponse.builder()
					.code(HttpStatus.NO_CONTENT.value())
					.msg("Password is Different.")
					.build();
		}

		// 로그인
		Optional<User> loginInfo = userRepository.findByIdAndPassword(loginDto.getId(), pwEncoder.encode(loginDto.getPassword()));

		// 아이디와 비밀번호를 검증하였기에 로그인 정보가 null이 될수 없다.
//		if( loginInfo == null ) {
//			return UserLoginResponse.builder()
//					.code(HttpStatus.NOT_FOUND.value())
//					.msg("Login Information not Found.")
//					.build();
//		}

		Map<String, Object> tokenMap = objectMapper.convertValue(loginDto, Map.class);
		String accessToken = jwt.makeJwtString(tokenMap, "ACCESS");
		String tokenType = "bearer";

		Map<String, Object> result = new LinkedHashMap<String, Object>();
		result.put("accessToken", accessToken);
		result.put("tokenType", tokenType);


		return UserLoginResponse.builder()
				.id(loginDto.getId())
				.code(HttpStatus.OK.value())
				.msg("Success")
				.token(accessToken)
				.build();
	}

}
