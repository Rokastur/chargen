package com.chargen.api.repository;

import com.chargen.api.entity.ERole;
import com.chargen.api.entity.Role;

public interface RoleRepository extends org.springframework.data.jpa.repository.JpaRepository<Role, Long> {

    Role findByRole(ERole role);
}
