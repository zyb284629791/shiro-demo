package top.sf.shiro.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.common.utils.Query;

import top.sf.shiro.sys.dao.UserDao;
import top.sf.shiro.sys.entity.UserEntity;
import top.sf.shiro.sys.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Set<String> getUserPermissions(String username) {
        return baseMapper.getUserPermissions(username);
    }
}