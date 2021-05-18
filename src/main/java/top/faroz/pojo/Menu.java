package top.faroz.pojo;

import java.util.List;

/**
 * @ClassName Menu
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/18 下午7:04
 * @Version 1.0
 **/
public class Menu {
    private Integer menuId;
    private String menuName;
    private Integer upmenuId;
    private Integer state;
    private String desc;
    private String url;
    //与role是多对多关系
    private List<Role> roleList;

    public Menu(Integer menuId, String menuName, Integer upmenuId, Integer state, String desc, String url, List<Role> roleList) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.upmenuId = upmenuId;
        this.state = state;
        this.desc = desc;
        this.url = url;
        this.roleList = roleList;
    }

    public Menu() {
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getUpmenuId() {
        return upmenuId;
    }

    public void setUpmenuId(Integer upmenuId) {
        this.upmenuId = upmenuId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", upmenuId=" + upmenuId +
                ", state=" + state +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
