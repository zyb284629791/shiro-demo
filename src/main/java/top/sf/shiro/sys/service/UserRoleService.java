package top.sf.shiro.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.sys.entity.UserRoleEntity;

import java.util.Map;

/**
 * 用户-角色关系表
 *
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:59
 */
public interface UserRoleService extends IService<UserRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

