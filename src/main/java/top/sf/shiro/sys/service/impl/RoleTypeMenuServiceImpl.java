package top.sf.shiro.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.common.utils.Query;

import top.sf.shiro.sys.dao.RoleTypeMenuDao;
import top.sf.shiro.sys.entity.RoleTypeMenuEntity;
import top.sf.shiro.sys.service.RoleTypeMenuService;


@Service("roleTypeMenuService")
public class RoleTypeMenuServiceImpl extends ServiceImpl<RoleTypeMenuDao, RoleTypeMenuEntity> implements RoleTypeMenuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoleTypeMenuEntity> page = this.page(
                new Query<RoleTypeMenuEntity>().getPage(params),
                new QueryWrapper<RoleTypeMenuEntity>()
        );

        return new PageUtils(page);
    }

}