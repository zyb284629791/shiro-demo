package top.sf.shiro.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.sf.shiro.sys.entity.MenuEntity;
import top.sf.shiro.sys.service.MenuService;
import top.sf.shiro.sys.vo.MenuVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 系统菜单表
 *
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 16:51:27
 */
@RestController
@RequestMapping("sys/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<MenuVO> list() {
        List<MenuVO> menuVOS = menuService.listMenuVO();
        Map<Long, MenuVO> menuVOMap = menuVOS.stream().collect(Collectors.toMap(MenuVO::getId, menuVO -> menuVO));
        menuVOS.stream().forEach(menuVO -> menuVO.setParentName(menuVOMap.get(menuVO.getId()).getParentName()));
        return menuVOS;
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:menu:info")
    public MenuEntity info(@PathVariable("id") Long id) {
        MenuEntity menu = menuService.getById(id);

        return menu;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public void save(@RequestBody MenuEntity menu) {
        menuService.save(menu);

    }

    /**
     * 修改
     */
    @PatchMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public void update(@RequestBody MenuEntity menu) {
        menuService.updateById(menu);

    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("sys:menu:delete")
    public void delete(@PathVariable("id") Long id) {
        menuService.removeById(id);
    }

}
