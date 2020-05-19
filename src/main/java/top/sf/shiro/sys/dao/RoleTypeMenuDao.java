package top.sf.shiro.sys.dao;

import top.sf.shiro.sys.entity.RoleTypeMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色类型-资源关系表
 * 
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-19 13:38:34
 */
@Mapper
public interface RoleTypeMenuDao extends BaseMapper<RoleTypeMenuEntity> {
	
}
