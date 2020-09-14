package com.lyp.dao;

import com.lyp.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionDao extends JpaRepository<UserPermission, Long> {
}
