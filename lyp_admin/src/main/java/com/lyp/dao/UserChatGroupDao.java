package com.lyp.dao;

import com.lyp.entity.UserChatGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChatGroupDao extends JpaRepository<UserChatGroup, Long> {
}
