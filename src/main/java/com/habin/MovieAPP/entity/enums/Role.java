package com.habin.MovieAPP.entity.enums;

import org.springframework.security.core.GrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role implements GrantedAuthority {
	ADMIN("ROLE_ADMIN"),
	EMPLOYEE("ROLE_EMPLOYEE"),
	USER("ROLE_USER"),
	VIP("ROLE_USER_VIP");

	public static class ROLES {
		public static final String ADMIN = "ROLE_ADMIN";
		public static final String EMPLOYEE = "ROLE_EMPLOYEE";
		public static final String USER = "ROLE_USER";
		public static final String VIP = "ROLE_USER_VIP";
	}

    private String value;

	@Override
	public String getAuthority() {
		return value;
	}
}