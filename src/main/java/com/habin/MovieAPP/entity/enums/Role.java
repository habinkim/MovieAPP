package com.habin.MovieAPP.entity.enums;

import org.springframework.security.core.GrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role implements GrantedAuthority {
	ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

	public static class ROLES{
		public static final String ADMIN = "ROLE_ADMIN";
		public static final String USER = "ROLE_USER";
	}

    private String value;

	@Override
	public String getAuthority() {
		return value;
	}
}