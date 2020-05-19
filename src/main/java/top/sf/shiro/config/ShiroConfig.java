package top.sf.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.sf.shiro.common.authentication.JwtFilter;
import top.sf.shiro.common.authentication.JwtRealm;
import top.sf.shiro.common.authentication.LoginRealm;
import top.sf.shiro.common.properties.AuthProperties;

/**
 * @Description
 * @Author: Yb.Z
 * @Date: 2020/5/8.15:58
 * @Version：1.0
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private AuthProperties authProperties;

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(authProperties.getEncrypt().getAlgorithmName());//散列算法:MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512等。
        hashedCredentialsMatcher.setHashIterations(authProperties.getEncrypt().getTimes());//散列的次数，默认1次， 设置两次相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    // 将自己的验证方式加入容器
    @Bean
    public JwtRealm jwtRealm() {
        JwtRealm jwtRealm = new JwtRealm();

        return jwtRealm;
    }

    @Bean
    public LoginRealm loginRealm(){
        LoginRealm loginRealm = new LoginRealm();
        loginRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return loginRealm;
    }

    // 禁用本地session
    @Bean
    public SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        return defaultSessionStorageEvaluator;
    }

    // 权限管理，配置主要是Realm的管理认证
    @Bean
    public SessionsSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(jwtRealm());
        // 3.关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator());
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    // 配置url过滤器
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // swagger
        chainDefinition.addPathDefinition("/swagger-ui.html","anon");
        chainDefinition.addPathDefinition("/swagger/**","anon");
        chainDefinition.addPathDefinition("/webjars/**", "anon");
        chainDefinition.addPathDefinition("/swagger-resources/**","anon");
        chainDefinition.addPathDefinition("/v2/**","anon");

//        chainDefinition.addPathDefinition("/sys/menu/**","anon");
        // these paths managed via annotations
        chainDefinition.addPathDefinition("/druid/**", "anon");
        chainDefinition.addPathDefinition("/api/**", "anon");
        chainDefinition.addPathDefinition("/login","anon");
        chainDefinition.addPathDefinition("/**", "jwt");
        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/logout","authc");
        return chainDefinition;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(ShiroFilterChainDefinition shiroFilterChainDefinition){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.getFilters().put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
