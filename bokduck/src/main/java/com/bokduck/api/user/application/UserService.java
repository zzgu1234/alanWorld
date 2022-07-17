package com.bokduck.api.user.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bokduck.api.user.application.dto.UserCreateDto;
import com.bokduck.api.user.domain.User;
import com.bokduck.api.user.infra.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Transactional
	public Long create(UserCreateDto createDto) {

		User user = createDto.toUser();

		userRepository.save(user);

		if( user == null ) {
			throw new Exception("Can not create user.");
		} else {

			return createDto.get
	}

}
