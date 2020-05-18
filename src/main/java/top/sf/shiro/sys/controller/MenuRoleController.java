package top.sf.shiro.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.sf.shiro.sys.entity.MenuRoleEntity;
import top.sf.shiro.sys.service.MenuRoleService;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.common.utils.R;



/**
 * 菜单-角色关系表
 *
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 16:51:27
 */
@RestController
@RequestMapping("sys/menurole")
public class MenuRoleController {
    @Autowired
    private MenuRoleService menuRoleService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menurole:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = menuRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:menurole:info")
    public R info(@PathVariable("id") Long id){
		MenuRoleEntity menuRole = menuRoleService.getById(id);

        return R.ok().put("menuRole", menuRole);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:menurole:save")
    public R save(@RequestBody MenuRoleEntity menuRole){
		menuRoleService.save(menuRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @PatchMapping("/update")
    @RequiresPermissions("sys:menurole:update")
    public R update(@RequestBody MenuRoleEntity menuRole){
		menuRoleService.updateById(menuRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("sys:menurole:delete")
    public R delete(@PathVariable("id") Long id){
        menuRoleService.removeById(id);
        return R.ok();
    }

}
