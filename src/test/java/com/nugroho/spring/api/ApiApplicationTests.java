package com.nugroho.spring.api;

import java.util.Collection;

import com.nugroho.spring.api.utility.Authentication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "test")
class ApiApplicationTests {

	@Value("${app.secret}")
	private String secret;

	@Test
	void contextLoads() {
		var dummyUserDetail = new UserDetails() {

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return null;
			}

			@Override
			public String getPassword() {
				return null;
			}

			@Override
			public String getUsername() {
				return "Tets Username";
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return false;
			}

			@Override
			public boolean isAccountNonLocked() {
				return false;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return false;
			}

			@Override
			public boolean isEnabled() {
				return false;
			}

		};

		var token = Authentication.Bearer.generateToken(secret, dummyUserDetail);
		System.out.println("Then token is : " + token);
	}

}
