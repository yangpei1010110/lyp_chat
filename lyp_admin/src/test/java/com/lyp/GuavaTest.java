package com.lyp;

import com.google.common.util.concurrent.RateLimiter;
import com.lyp.dao.UserDao;
import com.lyp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class GuavaTest {
    @Autowired
    UserDao userDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> {
                RateLimiter rateLimiter = RateLimiter.create(5);
                while (true) {
                    System.out.println("run 0:" + rateLimiter.acquire());
                }
            });
            executorService.execute(() -> {
                RateLimiter rateLimiter = RateLimiter.create(5);
                while (true) {
                    System.out.println("run 1:" + rateLimiter.acquire());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GuavaTestStart() {

        log.info("GuavaTestStart");
    }

    @Test
    public void userCrudTest() {
        PageRequest of = PageRequest.of(1, 2);
        Page<User> all = userDao.findAll(of);

        log.info("userCrudTest : ");
        log.info("TotalPages : " + all.getTotalPages());
        log.info("TotalElements : " + all.getTotalElements());

        all.get()
                .forEach(u -> {
                    log.info("id : " + u.getId());
                    log.info("username : " + u.getUsername());
                    log.info("password : " + u.getPassword());
                    log.info("createUser : " + u.getCreateUser());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    log.info("createDate : " + dateFormat.format(u.getCreateDate()));
                    log.info("createDate : " + u.getCreateDate());

                });

    }
}
