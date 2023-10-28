package com.greatlearning.service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.entity.User;
import com.greatlearning.repository.UserJpaRepository;

@Service
public class DomainUserDetailsService implements UserDetailsService {

	@Autowired
	UserJpaRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByUserName(username);

		if (!optionalUser.isPresent()) {
			throw new UsernameNotFoundException("Bad crdentials");
		}

		User user = optionalUser.get();
		return new DomainUserDetails(user);
	}

}

class DomainUserDetails implements UserDetails {

	private User user;

	public DomainUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = this.user.getRoles().stream().map(role -> role.getName())
				.map(strRole -> new SimpleGrantedAuthority(strRole)).collect(Collectors.toSet());
		return authorities;

	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
