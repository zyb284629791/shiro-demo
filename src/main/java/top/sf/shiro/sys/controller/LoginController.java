package top.sf.shiro.sys.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.sf.shiro.common.authentication.JwtUtil;
import top.sf.shiro.common.oauth2.OAuth2Token;
import top.sf.shiro.sys.entity.UserEntity;
import top.sf.shiro.sys.vo.LoginUserVO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
public class LoginController {

    /**
     * 普通登录
     * @param loginUserVO
     * @return
     */
    @PostMapping(value = "/login", produces="application/json;charset=UTF-8")
    public ResponseEntity<String> login(@RequestBody LoginUserVO loginUserVO){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginUserVO.getLoginName(),loginUserVO.getPassword());
        subject.login(token);
        // 因为在loginRealm中设置SimpleAuthenticationInfo时传的principal是userEntity对象，因此这里可以取出来使用
        UserEntity principal = (UserEntity) subject.getPrincipal();
        log.info("login sign token with secret : {}", principal.getSalt());
        return ResponseEntity.ok(JwtUtil.sign(principal.getLoginName(), principal.getSalt()));
    }

    /**
     * oauth2 登录
     * @param code
     * @return
     */
    @PostMapping("/sys/login")
    public String login(@RequestParam @NotEmpty(message = "授权码不能为空") String code){
        Subject subject = SecurityUtils.getSubject();
        OAuth2Token token = new OAuth2Token(code);
        subject.login(token);
        // 因为在loginRealm中设置SimpleAuthenticationInfo时传的principal是userEntity对象，因此这里可以取出来使用
        UserEntity principal = (UserEntity) subject.getPrincipal();
        log.info("login sign token with secret : {}", principal.getSalt());
        return JwtUtil.sign(principal.getLoginName(), principal.getSalt());
    }
}
