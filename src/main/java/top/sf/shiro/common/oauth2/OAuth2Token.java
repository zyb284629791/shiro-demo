package top.sf.shiro.common.oauth2;

import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

@AllArgsConstructor
public class OAuth2Token implements AuthenticationToken {

    /**
     * auth2服务器的授权码
     */
    private String code;

    @Override
    public Object getPrincipal() {
        return code;
    }

    @Override
    public Object getCredentials() {
        return code;
    }
}
