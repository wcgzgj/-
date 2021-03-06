package top.faroz.service;

import top.faroz.pojo.Role;
import top.faroz.pojo.Users;

import java.util.List;

public interface RoleService {


    List<Role> getRoleList(int pageIndex, int pageSize);

    int total();

    void insert(Role role,String[] menuIds);

    Role selectById(Integer id);

    void deleteById(Integer id);

    void update(Role role,String[] menuIds);
}
