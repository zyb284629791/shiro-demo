package top.sf.shiro.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import top.sf.shiro.sys.entity.UserEntity;
import top.sf.shiro.sys.service.UserService;
import top.sf.shiro.sys.vo.LoginUserVO;

import java.util.UUID;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAdd(){
        String salt = UUID.randomUUID().toString().replaceAll("-","");
        SimpleHash simpleHash = new SimpleHash("MD5", "123456", salt, 1024);
        UserEntity userEntity = new UserEntity();
        userEntity.setCreateUser("SuperAdmin");
        userEntity.setLoginName("admin");
        userEntity.setPassword(simpleHash.toHex());
        userEntity.setSalt(salt);
        userEntity.setMobilePhoneNo("18802888888");
        userEntity.setUserName("管理员");
        log.info("userentity" + userEntity);
        userService.save(userEntity);
    }

    @Test
    public void testLogin(){
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setLoginName("admin");
        loginUserVO.setPassword("admin");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name", loginUserVO.getLoginName());
        UserEntity userEntity = userService.getOne(wrapper);

        SimpleHash simpleHash = new SimpleHash("MD5", loginUserVO.getPassword(), userEntity.getSalt(), 1024);
        log.info("user password = {}, db password = {}",userEntity.getPassword(),simpleHash.toHex());
        Assert.isTrue(userEntity.getPassword().equalsIgnoreCase(simpleHash.toHex()));
    }
}

