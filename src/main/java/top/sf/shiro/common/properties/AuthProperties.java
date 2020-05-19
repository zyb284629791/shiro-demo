package top.sf.shiro.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
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
        /**
         * 散列的次数，默认1次， 设置两次相当于 md5(md5(""));
         */
        private Integer times;
        /**
         * 散列算法:MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512等。
         */
        private String algorithmName;
    }
}
