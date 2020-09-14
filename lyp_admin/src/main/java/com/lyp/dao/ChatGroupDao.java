package com.lyp.dao;

import com.lyp.entity.ChatGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatGroupDao extends JpaRepository<ChatGroup, Long> {
}
