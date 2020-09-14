package com.lyp.service.impl;

import com.lyp.dao.UserDao;
import com.lyp.dao.UserRoleDao;
import com.lyp.entity.User;
import com.lyp.service.UserService;
import com.lyp.utils.ChatHash;
import com.lyp.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 用户登录
     */
    @Override
    public String loginUser(Subject subject, String username, String password, Boolean remember, String verifyString, HttpSession session) {

        if (remember == null) {
            remember = false;
        }

        try {
            //尝试登陆
            subject.login(new UsernamePasswordToken(username, password, remember));
        } catch (AuthenticationException authenticationException) {
            authenticationException.printStackTrace();
        }

        if (subject.isAuthenticated()) {
            session.removeAttribute("verifyCode");//登录成功后删除验证码
            return "登陆成功";
        } else {
            session.setAttribute("verifyCode", VerifyCodeUtils.generateVerifyCode(4));//登录失败更新验证码
            return "登陆失败";
        }
    }

    /**
     * 用户注册
     *
     * @param username     注册用户名
     * @param password     注册密码
     * @param verifyString 注册验证码（必须)
     */
    @Override
    public String logonUser(String username, String password, String verifyString, HttpSession session) {
        if (StringUtils.hasText(String.valueOf(session.getAttribute("verifyCode")))
                && !String.valueOf(session.getAttribute("verifyCode")).toLowerCase().equals(verifyString)) {
            session.setAttribute("verifyCode", VerifyCodeUtils.generateVerifyCode(4));//注册失败更新验证码
            return "验证码错误";
        } else if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(verifyString)) {
            return "提交信息不全";
        } else if (userDao.findByUsernameEquals(username) != null) {

            return "用户名已存在";
        }

        logger.info("username:" + username);
        logger.info("password:" + password);
        String encodePassword = ChatHash.usernamePasswordHash(username, password).toHex();

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodePassword);
        user.setCreateDate(Timestamp.from(Instant.now()));
        user.setModifyDate(Timestamp.from(Instant.now()));

        if (user.getUserRoleList() == null) {
            user.setUserRoleList(new ArrayList<>());
        }

        user.getUserRoleList().add(userRoleDao.findByRoleNameEquals("admin"));

        userDao.save(user);

        SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, false));
        return "注册成功";
    }
}
