package top.sf.shiro.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.common.utils.Query;

import top.sf.shiro.sys.dao.MenuRoleDao;
import top.sf.shiro.sys.entity.MenuRoleEntity;
import top.sf.shiro.sys.service.MenuRoleService;


@Service("menuRoleService")
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleDao, MenuRoleEntity> implements MenuRoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MenuRoleEntity> page = this.page(
                new Query<MenuRoleEntity>().getPage(params),
                new QueryWrapper<MenuRoleEntity>()
        );

        return new PageUtils(page);
    }

}