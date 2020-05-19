package top.sf.shiro.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.common.utils.Query;

import top.sf.shiro.sys.dao.MenuDao;
import top.sf.shiro.sys.entity.MenuEntity;
import top.sf.shiro.sys.service.MenuService;
import top.sf.shiro.sys.vo.MenuVO;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {

    @Override
    public List<MenuVO> listMenuVO() {
        return baseMapper.listMenuVO();
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MenuEntity> page = this.page(
                new Query<MenuEntity>().getPage(params),
                new QueryWrapper<MenuEntity>()
        );

        return new PageUtils(page);
    }

}