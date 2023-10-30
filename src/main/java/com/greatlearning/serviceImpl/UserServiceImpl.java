package com.greatlearning.serviceImpl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.entity.Role;
import com.greatlearning.entity.User;
import com.greatlearning.repository.UserJpaRepository;
import com.greatlearning.service.RoleService;
import com.greatlearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	UserJpaRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RoleService roleService;

	@Override
	public User saveUser(User user) {
		User newUser = new User();
		newUser.setUserName(user.getUserName());
		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		newUser.getRoles().addAll(user.getRoles().stream().map(r -> {
			Role rr = roleService.findRoleById(r.getId());
			rr.getUsers().add(newUser);
			return rr;
		}).collect(Collectors.toList()));
		return userRepository.save(newUser);
	}

}
