package top.sf.shiro.sys.dao;

import top.sf.shiro.sys.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 系统用户表
 * 
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:59
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    Set<String> getUserPermissions(String username);
}
