package top.sf.shiro.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.sys.entity.MenuRoleEntity;

import java.util.Map;

/**
 * 菜单-角色关系表
 *
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:59
 */
public interface MenuRoleService extends IService<MenuRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

