package top.sf.shiro.common.authentication;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class JwtCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String jwtToken = token.getPrincipal().toString();
        String username = JwtUtil.getUsername(jwtToken);
        return JwtUtil.verify(jwtToken,username,info.getCredentials().toString());
    }
}
