package top.sf.shiro.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.sys.entity.UserEntity;
import top.sf.shiro.sys.vo.UserVO;

import java.util.Map;
import java.util.Set;

/**
 * 系统用户表
 *
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:59
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Set<String> getUserPermissions(String username);

    void saveUser(UserVO user);

    UserEntity findByLoginName(String loginName);

    String exchangeAccessToken(String code);

    UserEntity findByAccessToken(String accessToken);
}

