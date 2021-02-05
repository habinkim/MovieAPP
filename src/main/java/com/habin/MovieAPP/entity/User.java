package com.habin.MovieAPP.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.habin.MovieAPP.entity.enums.Role;
import com.habin.MovieAPP.entity.history.BaseEntity;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 사용자 Entity 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 19
* @Time : 12:31:43
*/

@SuperBuilder(toBuilder = true)
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USER")
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = -8093442765318688538L;

	@Id
	@Column(length = 20)
    private String userId; // 아이디
    
    @Column(nullable = false, length = 100)
    private String userPw; // 비밀번호
    
    @Column(nullable = false, length = 5)
    private String userNm; // 이름

    @ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "VIEW_ROLE")
	@Builder.Default
	@Column(nullable = false, length = 15)
	@Enumerated(EnumType.STRING)
	private List<Role> roles = new ArrayList<>();
 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(r -> new SimpleGrantedAuthority(r.getValue())).collect(Collectors.toList());
	}

    @Override
	public String getUsername() {
		return this.userNm;
	}

	@Override
	public String getPassword() {
		return this.userPw;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonExpired() { // 계정 만료 여부
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonLocked() { // 계정 잠금 여부
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isCredentialsNonExpired() { // 계정 패스워드 만료 여부
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isEnabled() { // 계정 사용 가능 여부
		return true;
    }
}