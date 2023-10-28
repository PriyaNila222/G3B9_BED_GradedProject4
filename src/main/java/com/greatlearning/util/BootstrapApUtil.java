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
		priya.setUserName("Priya");
		priya.setPassword(passwordEncoder.encode("smiley"));

		User nila = new User();
		nila.setUserName("Nila");
		nila.setPassword(passwordEncoder.encode("smiley"));

		userRepository.save(priya);
		userRepository.save(nila);

		Role userRole = new Role();
		userRole.setName("ROLE_USER");

		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");

		roleRepository.save(userRole);
		roleRepository.save(adminRole);

		priya.addRole(adminRole);
		priya.addRole(userRole);
		nila.addRole(userRole);

		userRepository.save(priya);
		userRepository.save(nila);

	}

}
