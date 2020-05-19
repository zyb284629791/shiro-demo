package top.sf.shiro.sys.dao;

import top.sf.shiro.sys.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.sf.shiro.sys.vo.MenuVO;

import java.util.List;

/**
 * 系统菜单表
 * 
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:59
 */
@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {

    List<MenuVO> listMenuVO();
}
