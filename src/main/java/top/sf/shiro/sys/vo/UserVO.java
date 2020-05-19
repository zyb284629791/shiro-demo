package top.sf.shiro.sys.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    /**
     * 用户登录名
     */
    private String loginName;
    /**
     * 用户密码
     */
    private String password;
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
