package top.sf.shiro.sys.service.impl;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.sf.shiro.common.properties.AuthProperties;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.common.utils.Query;

import top.sf.shiro.sys.dao.UserDao;
import top.sf.shiro.sys.entity.UserEntity;
import top.sf.shiro.sys.service.UserService;
import top.sf.shiro.sys.vo.UserVO;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private AuthProperties authProperties;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public UserEntity findByLoginName(String loginName) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name", loginName);
        return this.getOne(wrapper);
    }

    @Override
    public void saveUser(UserVO user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        String salt = UUID.randomUUID().toString().replaceAll("-","");
        SimpleHash simpleHash = new SimpleHash(authProperties.getEncrypt().getAlgorithmName(),
                user.getPassword(), salt, authProperties.getEncrypt().getTimes());
        userEntity.setPassword(simpleHash.toHex());
        userEntity.setSalt(salt);

        this.save(userEntity);
    }

    @Override
    public Set<String> getUserPermissions(String username) {
        return baseMapper.getUserPermissions(username);
    }
}