package com.silentium.repository;

import com.silentium.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
	@Query("select r from Role r where r.role=:role")
	Role findByRole(@Param("role") String role);

}