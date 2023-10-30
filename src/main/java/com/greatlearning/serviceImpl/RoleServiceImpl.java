package com.greatlearning.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.entity.Role;
import com.greatlearning.repository.RoleJpaRepository;
import com.greatlearning.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleJpaRepository roleRepository;

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role findRoleById(long id) {
		return roleRepository.findById(id).get();
	}

}
