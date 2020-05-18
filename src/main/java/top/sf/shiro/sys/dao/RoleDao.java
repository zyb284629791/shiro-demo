package top.sf.shiro.sys.dao;

import top.sf.shiro.sys.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统角色表
 * 
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:59
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {
	
}
