package top.sf.shiro.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.sf.shiro.sys.entity.RoleTypeEntity;
import top.sf.shiro.sys.service.RoleTypeService;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.common.utils.R;



/**
 * 角色类型表
 *
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 16:51:27
 */
@RestController
@RequestMapping("sys/roletype")
public class RoleTypeController {
    @Autowired
    private RoleTypeService roleTypeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:roletype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roleTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:roletype:info")
    public R info(@PathVariable("id") Long id){
		RoleTypeEntity roleType = roleTypeService.getById(id);

        return R.ok().put("roleType", roleType);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:roletype:save")
    public R save(@RequestBody RoleTypeEntity roleType){
		roleTypeService.save(roleType);

        return R.ok();
    }

    /**
     * 修改
     */
    @PatchMapping("/update")
    @RequiresPermissions("sys:roletype:update")
    public R update(@RequestBody RoleTypeEntity roleType){
		roleTypeService.updateById(roleType);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("sys:roletype:delete")
    public R delete(@PathVariable("id") Long id){
        roleTypeService.removeById(id);
        return R.ok();
    }

}
