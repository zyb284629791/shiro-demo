package top.sf.shiro.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.sys.entity.RoleTypeEntity;

import java.util.Map;

/**
 * 角色类型表
 *
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:58
 */
public interface RoleTypeService extends IService<RoleTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

