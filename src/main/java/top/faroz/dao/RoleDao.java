package top.faroz.dao;

import top.faroz.pojo.Role;
import top.faroz.pojo.Users;

import java.util.List;

/**
 * @ClassName RoleDao
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/19 下午4:47
 * @Version 1.0
 **/
public interface RoleDao {

    List<Role> getRoleList(int pageIndex, int pageSize);

    int total();

    /**
     * 插入角色信息
     * @param role
     * @return
     */
    int insert(Role role);

    Role selectById(Integer id);
}
