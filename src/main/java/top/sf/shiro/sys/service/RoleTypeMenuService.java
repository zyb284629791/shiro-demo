package top.sf.shiro.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.sys.entity.RoleTypeMenuEntity;

import java.util.Map;

/**
 * 角色类型-资源关系表
 *
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-19 13:38:34
 */
public interface RoleTypeMenuService extends IService<RoleTypeMenuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

