package top.sf.shiro.common.authentication;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import top.sf.shiro.sys.entity.UserEntity;
import top.sf.shiro.sys.service.RoleService;
import top.sf.shiro.sys.service.UserService;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author: Yb.Z
 * @Date: 2020/5/8.16:33
 * @Version：1.0
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 用户认证 --- 登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        // 获取用户信息
        String token = authenticationToken.getPrincipal().toString();
        String username = JwtUtil.getUsername(token);
        UserEntity user = userService.findByLoginName(username);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        } else {
            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token,
                    user.getSalt(), getName());
            return simpleAuthenticationInfo;
        }
    }

    /**
     * 角色权限和对应权限添加
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户名
        String username = JwtUtil.getUsername(principalCollection.toString());
        // 查询用户名称
        Set<String> permissions = userService.getUserPermissions(username);
        Set<String> perms = permissions.stream().filter(perm -> !StringUtils.isEmpty(perm))
                .map(perm -> perm.split(",")).flatMap(permArray -> Arrays.asList(permArray)
                        .stream()).collect(Collectors.toSet());
        log.info("perms : {}", perms);
        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 添加权限
        simpleAuthorizationInfo.addStringPermissions(perms);
        return simpleAuthorizationInfo;
    }

}
