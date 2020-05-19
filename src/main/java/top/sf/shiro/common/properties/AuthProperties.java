package top.sf.shiro.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("cmii.auth")
public class AuthProperties {

    private Encrypt encrypt;
    private Token token;

    @Data
    public static class Token {
        private String expireTime;
    }

    @Data
    public static class Encrypt{
        private Integer times;
        private String algorithmName;
    }
}
