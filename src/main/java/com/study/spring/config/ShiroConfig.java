package com.study.spring.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tp on 2018/12/27.
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterfactoryBean
     */

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        /**
         * anon 无需认证
         * authc 必须认证才可以访问
         * user 记住我
         * perms 该资源必须得到资源权限才可以访问
         * role 该资源必须得到资源角色才可以访问
         */
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/add", "authc");
        filterMap.put("/update", "authc");
        filterMap.put("/login", "anon");
        filterMap.put("/add" , "perms[user:add]");
        filterMap.put("/update" , "perms[user:update]");
        // 拦截所有资源
        // filterMap.put("/*" , "authc");
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 设置未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "myRealm")
    public MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
