package top.sf.shiro.common.oauth2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("cmii.auth.oauth2")
public class OAuth2Properties {

    private ServerInfo server;

    @Data
    public static class ServerInfo{
        private String appId;
        private String secretKey;
    }
}
