package top.sf.shiro.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 角色类型-资源关系表
 * 
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:58
 */
@Data
@TableName("sys_role_type_menu")
public class RoleTypeMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
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
	 * 角色类型ID
	 */
	private Long roleTypeId;
	/**
	 * 角色ID
	 */
	private Long menuId;
	/**
	 * 是否默认选中(0:否 1: 是)
	 */
	private Integer defaultSelected;

}
