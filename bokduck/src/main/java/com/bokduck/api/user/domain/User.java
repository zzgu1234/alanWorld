package com.bokduck.api.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCrypt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

	/*
	 * 사용자 고유번호
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_NO", nullable = false)
	private Long userNo;

	/*
	 * 사용자 아이디
	 */
	@Column(name = "id", nullable = false)
	private String id;

	/*
	 * 사용자 이름
	 */
	@Column(name = "name", nullable = false)
	private String name;


	/*
	 * 사용자 암호(BCrypt)
	 */
	@Column(name = "password", nullable = false)
	private BCrypt password;


	/*
	 * 사용자 이메일
	 */
	@Column(name = "email", nullable = false)
	private String email;

	/*
	 * 사용자 휴대폰
	 */
	@Column(name = "mobile", nullable = false)
	private String mobile;

	@Builder
	public User(@NotNull Long userNo,
			@NotNull String id,
			@NotNull String name,
			@NotNull String email
			,@NotNull String mobile) {

		this.userNo = userNo;
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}

}
