package com.lyp.controller;

import com.lyp.service.UserService;
import com.lyp.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 为游客或用户获取一个随机id
     */
    @GetMapping("/uuid")
    private String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 验证码的验证接口
     */
    @GetMapping("/test-verify-code")
    private String testVerifyCode(String verifyCode, HttpSession session) {
        if (StringUtils.isEmpty(verifyCode)) {
            return "verify parameter error";
        } else if (StringUtils.isEmpty(session.getAttribute("verifyCode"))) {
            return "verify not found";
        } else if (session.getAttribute("verifyCode").equals(verifyCode)) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 获取验证码图像
     */
    @RequestMapping("/get-verify-code")
    private void getVerifyCode(HttpServletResponse response, HttpSession session) {
        if (StringUtils.isEmpty(session.getAttribute("verifyCode"))) {
            session.setAttribute("verifyCode", VerifyCodeUtils.generateVerifyCode(4));
        }

        try {
            VerifyCodeUtils.outputImage(250, 35, response.getOutputStream(), (String) session.getAttribute("verifyCode"));
            log.info("获取一次验证码图片 验证码:"+session.getAttribute("verifyCode")+" sessionId:"+session.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新验证码
     */
    @GetMapping("/update-verify-code")
    private String updateVerify(HttpSession session) {
        session.setAttribute("verifyCode", VerifyCodeUtils.generateVerifyCode(4));
        return "success";
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param remember 记住我
     * @param verifyString      验证码（上传为""或null时为第一次登录）
     * @param session  用于存放服务器端验证码
     */
    @PostMapping("/login")
    private String login(String username, String password, Boolean remember, String verifyString, HttpSession session) {
        //根据线程id获取到不同的subject
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            session.removeAttribute("verifyCode");//登录成功后删除验证码
            return "已经登陆";
        } else if (subject.isRemembered()) {
            session.removeAttribute("verifyCode");//登录成功后删除验证码
            return "自动登录";
        } else if (StringUtils.hasText((String) session.getAttribute("verifyCode"))
                && !((String) session.getAttribute("verifyCode")).toLowerCase().equals(verifyString)) {
            session.setAttribute("verifyCode", VerifyCodeUtils.generateVerifyCode(4));//登录失败更新验证码
            return "验证码错误";
        } else if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(verifyString)){
            return "提交信息不全";
        }
        return userService.loginUser(subject, username, password, remember, verifyString.toLowerCase(), session);
    }

    //测试是否登录
    @GetMapping("/test")
    private String test(String username, String password, String remember, String pin) {
        return String.valueOf(SecurityUtils.getSubject().isAuthenticated());
    }

    //登出
    @GetMapping("/logout")
    private String logout() {
        SecurityUtils.getSubject().logout();
        return "访问成功登出";
    }

    //注册
    @PostMapping("/logon")
    private String logon(String username, String password, String verifyString, HttpSession session) {
        log.info(String.format("logon username:%s password:%s verifyString:%s",username,password,verifyString));
        Stream.of(session.getAttributeNames()).forEach(d->{
            String tempString = d.nextElement();
            log.info(tempString+" : "+session.getAttribute(tempString));
        });
        return userService.logonUser(username, password, verifyString.toLowerCase(), session);
    }


}
