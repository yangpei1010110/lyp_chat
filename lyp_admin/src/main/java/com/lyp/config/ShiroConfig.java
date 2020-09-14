package com.lyp.config;

import com.lyp.shiro.CustomRealm;
import com.lyp.shiro.ShiroSessionDao;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        chainDefinition.addPathDefinition("/**", "anon");
        return chainDefinition;
    }


    @Bean
    public DefaultWebSecurityManager securityManager(ShiroSessionDao shiroSessionDao) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //自定义Realm
        securityManager.setRealm(customRealm());
        //自定义SessionManager
        securityManager.setSessionManager(defaultWebSessionManager(shiroSessionDao));

        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(credentialsMatcher());
        return customRealm;
    }

    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");  // 散列算法，这里使用更安全的sha256算法
        credentialsMatcher.setStoredCredentialsHexEncoded(true);  // 数据库存储的密码字段使用HEX还是BASE64方式加密
        return credentialsMatcher;
    }

    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(ShiroSessionDao shiroSessionDao) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(3600 * 1000);//全局过期时间
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionDAO(shiroSessionDao);//自定义SessionDao
        sessionManager.setSessionValidationSchedulerEnabled(true);

        return sessionManager;
    }

}
