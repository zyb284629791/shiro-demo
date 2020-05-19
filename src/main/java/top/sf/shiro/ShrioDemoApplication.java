package top.sf.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.sf.shiro.common.properties.AuthProperties;

@SpringBootApplication
@EnableConfigurationProperties(AuthProperties.class)
public class ShrioDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShrioDemoApplication.class, args);
    }

}
