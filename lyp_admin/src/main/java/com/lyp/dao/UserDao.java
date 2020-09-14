package com.lyp.dao;

import com.lyp.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends PagingAndSortingRepository<User, Long> {
    User findByUsernameEquals(String username);

}
