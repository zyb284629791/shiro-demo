package top.sf.shiro.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.common.utils.Query;

import top.sf.shiro.sys.dao.RoleTypeDao;
import top.sf.shiro.sys.entity.RoleTypeEntity;
import top.sf.shiro.sys.service.RoleTypeService;


@Service("roleTypeService")
public class RoleTypeServiceImpl extends ServiceImpl<RoleTypeDao, RoleTypeEntity> implements RoleTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoleTypeEntity> page = this.page(
                new Query<RoleTypeEntity>().getPage(params),
                new QueryWrapper<RoleTypeEntity>()
        );

        return new PageUtils(page);
    }

}