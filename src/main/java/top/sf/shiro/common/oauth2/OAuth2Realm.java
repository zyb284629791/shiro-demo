package top.sf.shiro.common.oauth2;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.sf.shiro.sys.entity.UserEntity;
import top.sf.shiro.sys.service.UserService;

public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 登陆后授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 鉴权登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        OAuth2Token auth2Token = (OAuth2Token) token;
        // 请求统一认证获取accessToken
        String accessToken = userService.exchangeAccessToken(token.getCredentials().toString());
        if (StringUtils.isEmpty(accessToken)) {
            throw new AuthenticationException("统一认证accessToken获取失败");
        }
        UserEntity userEntity = userService.findByAccessToken(accessToken);
        if (ObjectUtils.isEmpty(userEntity)) {
            throw new AuthenticationException("用户名密码错误");
        }
        return new SimpleAuthenticationInfo(userEntity, auth2Token, getName());
    }
}
