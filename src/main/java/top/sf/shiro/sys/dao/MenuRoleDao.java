package top.sf.shiro.sys.dao;

import top.sf.shiro.sys.entity.MenuRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单-角色关系表
 * 
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:59
 */
@Mapper
public interface MenuRoleDao extends BaseMapper<MenuRoleEntity> {
	
}
