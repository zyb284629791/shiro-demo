package top.sf.shiro.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.sf.shiro.sys.entity.UserRoleEntity;
import top.sf.shiro.sys.service.UserRoleService;
import top.sf.shiro.common.utils.PageUtils;
import top.sf.shiro.common.utils.R;



/**
 * 用户-角色关系表
 *
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 16:51:27
 */
@RestController
@RequestMapping("sys/userrole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:userrole:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:userrole:info")
    public R info(@PathVariable("id") Long id){
		UserRoleEntity userRole = userRoleService.getById(id);

        return R.ok().put("userRole", userRole);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:userrole:save")
    public R save(@RequestBody UserRoleEntity userRole){
		userRoleService.save(userRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @PatchMapping("/update")
    @RequiresPermissions("sys:userrole:update")
    public R update(@RequestBody UserRoleEntity userRole){
		userRoleService.updateById(userRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("sys:userrole:delete")
    public R delete(@PathVariable("id") Long id){
        userRoleService.removeById(id);
        return R.ok();
    }

}
