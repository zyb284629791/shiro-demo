package top.sf.shiro.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.sf.shiro.sys.entity.RoleTypeMenuEntity;
import top.sf.shiro.sys.service.RoleTypeMenuService;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.common.utils.R;



/**
 * 角色类型-资源关系表
 *
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 16:51:27
 */
@RestController
@RequestMapping("sys/roletypemenu")
public class RoleTypeMenuController {
    @Autowired
    private RoleTypeMenuService roleTypeMenuService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:roletypemenu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roleTypeMenuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:roletypemenu:info")
    public R info(@PathVariable("id") Long id){
		RoleTypeMenuEntity roleTypeMenu = roleTypeMenuService.getById(id);

        return R.ok().put("roleTypeMenu", roleTypeMenu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:roletypemenu:save")
    public R save(@RequestBody RoleTypeMenuEntity roleTypeMenu){
		roleTypeMenuService.save(roleTypeMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @PatchMapping("/update")
    @RequiresPermissions("sys:roletypemenu:update")
    public R update(@RequestBody RoleTypeMenuEntity roleTypeMenu){
		roleTypeMenuService.updateById(roleTypeMenu);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("sys:roletypemenu:delete")
    public R delete(@PathVariable("id") Long id){
        roleTypeMenuService.removeById(id);
        return R.ok();
    }

}
