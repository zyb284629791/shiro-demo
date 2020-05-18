package top.sf.shiro.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.common.utils.R;
import top.sf.shiro.sys.entity.MenuEntity;
import top.sf.shiro.sys.service.MenuService;

import java.util.Map;


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
//    @RequiresPermissions("sys:menu:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = menuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("sys:menu:info")
    public R info(@PathVariable("id") Long id) {
        MenuEntity menu = menuService.getById(id);

        return R.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody MenuEntity menu) {
        menuService.save(menu);

        return R.ok();
    }

    /**
     * 修改
     */
    @PatchMapping("/update")
//    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody MenuEntity menu) {
        menuService.updateById(menu);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
//    @RequiresPermissions("sys:menu:delete")
    public R delete(@PathVariable("id") Long id) {
        menuService.removeById(id);
        return R.ok();
    }

}
