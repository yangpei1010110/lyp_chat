package com.lyp.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ChatHash {

    static public SimpleHash usernamePasswordHash(String username, String password) {
        String algorithmName = "MD5";//加密方式
        Object salt = ByteSource.Util.bytes(username);//盐值
        return new SimpleHash(algorithmName, password, salt);
    }
}
