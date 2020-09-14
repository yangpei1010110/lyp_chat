package com.lyp.dao;

import com.lyp.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByRoleNameEquals(String roleName);

    UserRole findByRoleNameEquals(String roleName);



}
