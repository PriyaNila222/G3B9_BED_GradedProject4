package com.greatlearning.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.greatlearning.entity.Role;
import com.greatlearning.entity.User;
import com.greatlearning.repository.RoleJpaRepository;
import com.greatlearning.repository.UserJpaRepository;

@Component
public class BootstrapApUtil {
	@Autowired
	UserJpaRepository userRepository;

	@Autowired
	RoleJpaRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	public void insertUsers(ApplicationReadyEvent event) {
		User priya = new User();
		priya.setUserName("admin");
		priya.setPassword(passwordEncoder.encode("admin"));
		userRepository.save(priya);

		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");

		roleRepository.save(adminRole);

		priya.addRole(adminRole);

		userRepository.save(priya);

	}

}
