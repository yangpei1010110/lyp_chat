package com.lyp.service;

import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpSession;

public interface UserService {
    String loginUser(Subject subject, String username, String password, Boolean remember, String verifyString, HttpSession session);

    String logonUser(String username, String password, String verifyString, HttpSession session);
}
