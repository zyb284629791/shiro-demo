package top.sf.shiro.common.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import top.sf.shiro.common.utils.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {

    private static final long EXPIRE_TIME = 60;

    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * 校验 token是否正确
     *
     * @param token  密钥
     * @param username 用户名
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username",username).build();
            verifier.verify(token);
            Date expire = JWT.decode(token).getClaim(PublicClaims.EXPIRES_AT).asDate();
            LocalDateTime localDateTime = expire.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            log.info("token will be expire at :{}",localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            log.info("token is valid");
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            log.info("token is invalid{}", e.getMessage());
            return false;
        }
    }

    /**
     * 从 token中获取用户名
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim("username").asString();
    }

    /**
     * 生成 token
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return token
     */
    public static String sign(String username, String secret) {
        try {
            Date expireDate = DateTimeUtil.transferLocalDateTime2Date(LocalDateTime.now().plusMinutes(EXPIRE_TIME));
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("username",username)
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            log.error("jwt sign error：{}", e);
            return null;
        }
    }

    /**
     * 验证 token是否过期
     */
    public static boolean isTokenExpired(String token) {
        Date now = DateTimeUtil.transferLocalDateTime2Date(LocalDateTime.now());
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }

    /**
     * 刷新 token的过期时间
     */
    public static String refreshTokenExpired(String token, String secret) {
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> claims = jwt.getClaims();
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTCreator.Builder builer = JWT.create().withExpiresAt(date);
            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
                builer.withClaim(entry.getKey(), entry.getValue().asString());
            }
            return builer.sign(algorithm);
        } catch (JWTCreationException e) {
            return null;
        }
    }

}
