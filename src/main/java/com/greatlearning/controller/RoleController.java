package com.greatlearning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.entity.Role;
import com.greatlearning.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String addRoles(@RequestBody Role role) {
		roleService.saveRole(role);
		return "Roles saved successfully";
	}

}
