package top.sf.shiro.sys.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUserVO implements Serializable {

    private Long userId;
    private String loginName;

    @JsonIgnore
    private String password;
    private String userName;
    private String mobilePhoneNo;

    @JsonIgnore
    private String salt;
    private Byte userStatus;
    private String permissionFlag;
}
