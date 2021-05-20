package top.faroz.service.impl;

import top.faroz.dao.MiddleDao;
import top.faroz.dao.RoleDao;
import top.faroz.dao.impl.MiddleDaoImpl;
import top.faroz.dao.impl.RoleDaoImpl;
import top.faroz.pojo.Role;
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
    private MiddleDao middleDao = new MiddleDaoImpl();

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
    public void insert(Role role,String[] menuIds) {
        /**
         * 获取插入后的数据生成的key
         *
         * 正常的逻辑，应该是 service 层生成不重复的id
         * 然后再插入数据库的
         */
        int key = roleDao.insert(role);

        //插入角色信息
        Integer[] ids = new Integer[menuIds.length];
        for (int i = 0; i < menuIds.length; i++) {
            ids[i]=Integer.parseInt(menuIds[i]);
        }
        //插入所有中间表信息
        middleDao.insert(key,ids);
    }

    @Override
    public Role selectById(Integer id) {
        Role role = roleDao.selectById(id);
        return role;
    }

    @Override
    public void deleteById(Integer id) {
        roleDao.deleteById(id);
        middleDao.deleteByRoleId(id);
    }

    @Override
    public void update(Role role, String[] menuIds) {
        Integer[] ids = new Integer[menuIds.length];
        for (int i = 0; i < menuIds.length; i++) {
            ids[i]=Integer.parseInt(menuIds[i]);
        }
        //先删除middle表中的关系
        middleDao.deleteByRoleId(role.getRoleId());
        //再将新的关系，插入middle表中
        middleDao.insert(role.getRoleId(),ids);

        //更新 role表中的信息
        roleDao.update(role);

    }
}
