package top.sf.shiro.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统菜单表
 * 
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:59
 */
@Data
@TableName("sys_menu")
public class MenuEntity implements Serializable {
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
	 * 资源名称
	 */
	private String menuName;
	/**
	 * 资源类型(0: 目录,1: 菜单,2: 按钮)
	 */
	private Integer menuType;
	/**
	 * 父节点ID
	 */
	private Long parentId;
	/**
	 * 资源url
	 */
	private String url;
	/**
	 * 所需权限标识
	 */
	private String permissionFlag;
	/**
	 * 排序
	 */
	private Integer orderNum;
	/**
	 * 图标
	 */
	private String icon;

}
