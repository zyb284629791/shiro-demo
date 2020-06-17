package top.sf.shiro.common.authentication;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

@Slf4j
public class JwtCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String jwtToken = token.getPrincipal().toString();
        String username = JwtUtil.getUsername(jwtToken);
        return JwtUtil.verify(jwtToken,username, info.getCredentials().toString());
    }
}
