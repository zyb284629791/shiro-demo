package top.sf.shiro.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.sf.shiro.common.authentication.JwtUtil;
import top.sf.shiro.sys.entity.UserEntity;
import top.sf.shiro.sys.vo.LoginUserVO;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody LoginUserVO loginUserVO){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginUserVO.getLoginName(),loginUserVO.getPassword());
        subject.login(token);
        // 因为在loginRealm中设置SimpleAuthenticationInfo时传的principal是userEntity对象，因此这里可以取出来使用
        UserEntity principal = (UserEntity) subject.getPrincipal();
        return JwtUtil.sign(principal.getLoginName(), principal.getPassword());

    }
}
