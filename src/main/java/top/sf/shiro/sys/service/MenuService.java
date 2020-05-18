package top.sf.shiro.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.sys.entity.MenuEntity;

import java.util.Map;

/**
 * 系统菜单表
 *
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:59
 */
public interface MenuService extends IService<MenuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

