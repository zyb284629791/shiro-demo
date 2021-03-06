package top.sf.shiro.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 菜单-角色关系表
 * 
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:59
 */
@Data
@TableName("sys_menu_role")
public class MenuRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 数据创建人
	 */
	private String createUser;
	/**
	 * 数据创建时间
	 */
	private Date createTime;
	/**
	 * 最后一次数据修改人
	 */
	private String updateUser;
	/**
	 * 最后一次数据修改时间
	 */
	private Date updateTime;
	/**
	 * 删除标识(0:删除 1:未删除)
	 */
	private Integer deleteFlag;
	/**
	 * 菜单ID
	 */
	private Long menuId;
	/**
	 * 角色ID
	 */
	private Long roleId;

}
