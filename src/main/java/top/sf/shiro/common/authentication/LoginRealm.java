package top.sf.shiro.common.authentication;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import top.sf.shiro.sys.entity.UserEntity;
import top.sf.shiro.sys.service.UserService;

@Slf4j
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 找它的原因是这个方法返回true
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 从数据库查用户信息，交给shiro SimpleAuthenticationInfo判断
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        String loginName = usernamePasswordToken.getPrincipal().toString();
        UserEntity userEntity = userService.findByLoginName(loginName);
        if (ObjectUtils.isEmpty(userEntity)) {
            throw new AuthenticationException("用户名密码错误");
        }
        return new SimpleAuthenticationInfo(userEntity, userEntity.getPassword(), ByteSource.Util.bytes(userEntity.getSalt()), getName());
    }
}
