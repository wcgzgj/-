package top.faroz.service.impl;

import top.faroz.dao.RoleDao;
import top.faroz.dao.impl.RoleDaoImpl;
import top.faroz.pojo.Role;
import top.faroz.pojo.Users;
import top.faroz.service.RoleService;

import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/19 下午4:46
 * @Version 1.0
 **/
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public List<Role> getRoleList(int pageIndex, int pageSize) {
        List<Role> roleList = roleDao.getRoleList(pageIndex, pageSize);
        return roleList;
    }

    @Override
    public int total() {
        return roleDao.total();
    }

    @Override
    public void insert(Role role) {
        roleDao.insert(role);
    }
}
