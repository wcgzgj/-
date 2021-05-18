package top.faroz.pojo;

import java.util.List;

/**
 * @ClassName Role
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/18 下午7:02
 * @Version 1.0
 **/
public class Role {
    private Integer roleId;
    private String roleName;
    private Integer roleState;

    //包含多个用户信息
    private List<Users> usersList;
    //与menu是多对多关系
    private List<Menu> menuList;

    public Role(Integer roleId, String roleName, Integer roleState, List<Users> usersList, List<Menu> menuList) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleState = roleState;
        this.usersList = usersList;
        this.menuList = menuList;
    }

    public Role() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleState() {
        return roleState;
    }

    public void setRoleState(Integer roleState) {
        this.roleState = roleState;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleState=" + roleState +
                ", usersList=" + usersList +
                ", menuList=" + menuList +
                '}';
    }
}
