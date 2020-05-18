package top.sf.shiro.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统用户表
 * 
 * @author zhangyanbin
 * @email zhangyanbin@cmii.chinamobile.com
 * @date 2020-05-18 10:23:59
 */
@Data
@TableName("sys_user")
public class UserEntity implements Serializable {
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
	 * 用户登录名
	 */
	private String loginName;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 加密盐值
	 */
	private String salt;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 用户手机号
	 */
	private String mobilePhoneNo;
	/**
	 * 用户状态(0：禁用   1：正常)
	 */
	private Integer userStatus;

}
