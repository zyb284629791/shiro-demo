package top.sf.shiro.sys.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUserVO implements Serializable {

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户密码
     */
    private String password;

}
