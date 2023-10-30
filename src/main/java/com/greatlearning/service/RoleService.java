package com.greatlearning.service;

import com.greatlearning.entity.Role;

public interface RoleService {

	Role saveRole(Role role);

	Role findRoleById(long id);

}
