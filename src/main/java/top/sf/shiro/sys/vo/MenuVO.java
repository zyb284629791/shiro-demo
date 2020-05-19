package top.sf.shiro.sys.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuVO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;
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
     * 父节点名称
     */
    private Long parentName;
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
