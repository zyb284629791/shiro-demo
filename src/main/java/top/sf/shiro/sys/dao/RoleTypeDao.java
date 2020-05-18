package top.sf.shiro.sys.dao;

import top.sf.shiro.sys.entity.RoleTypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色类型表
 * 
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:58
 */
@Mapper
public interface RoleTypeDao extends BaseMapper<RoleTypeEntity> {
	
}
