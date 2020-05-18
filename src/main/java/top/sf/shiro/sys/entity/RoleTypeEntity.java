package top.sf.shiro.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 角色类型表
 * 
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:58
 */
@Data
@TableName("sys_role_type")
public class RoleTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 数据创建者
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
	 * 删除标识 0: 已删除, 1: 未删除
	 */
	private Integer deleteFlag;
	/**
	 * 角色类型名称
	 */
	private String roleTypeName;
	/**
	 * 角色类型code
	 */
	private Integer roleTypeCode;
	/**
	 * 角色类型描述
	 */
	private String roleTypeDescription;

}
