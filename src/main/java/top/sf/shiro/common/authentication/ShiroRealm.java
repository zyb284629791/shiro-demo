package top.sf.shiro.common.authentication;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import top.sf.shiro.sys.entity.RoleEntity;
import top.sf.shiro.sys.entity.UserEntity;
import top.sf.shiro.sys.service.RoleService;
import top.sf.shiro.sys.service.UserService;
import top.sf.shiro.sys.vo.LoginUserVO;

import java.util.Set;

/**
 * @Description
 * @Author: Yb.Z
 * @Date: 2020/5/8.16:33
 * @Version：1.0
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name", username);
        UserEntity user = userService.getOne(wrapper);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        } else {
            if (!JwtUtil.verify(token,username,user.getPassword())) {
                throw new AuthenticationException("Username or password error");
            }
            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,
                    user.getPassword(), getName());
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

        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 添加权限
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

}
