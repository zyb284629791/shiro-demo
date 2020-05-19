package top.sf.shiro.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.sf.shiro.common.authentication.JwtUtil;
import top.sf.shiro.common.properties.AuthProperties;
import top.sf.shiro.sys.entity.UserEntity;
import top.sf.shiro.sys.service.UserService;
import top.sf.shiro.sys.vo.LoginUserVO;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthProperties authProperties;

    @PostMapping("/login")
    public String login(@RequestBody LoginUserVO loginUserVO){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginUserVO.getLoginName(),loginUserVO.getPassword());
        subject.login(token);
//        subject
        return JwtUtil.sign(subject.getPrincipal().toString(), null);

    }
}
