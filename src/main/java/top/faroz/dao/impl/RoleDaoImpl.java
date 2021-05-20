package top.faroz.dao.impl;

import top.faroz.dao.RoleDao;
import top.faroz.pojo.Role;
import top.faroz.pojo.Users;
import top.faroz.util.ResultSetUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RoleDaoImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/19 下午4:47
 * @Version 1.0
 **/
public class RoleDaoImpl extends BaseDao implements RoleDao {


    @Override
    public List<Role> getRoleList(int pageIndex, int pageSize) {
        String sql = "select * from role limit ?,?";
        ArrayList params = new ArrayList();
        /**
         * 前端传来的，是从1开始数的页码
         * 但是我们的数据库limit查信息，是从0开始的
         */
        params.add((pageIndex-1)*pageSize);
        params.add(pageSize);
        ResultSet rs = query(sql, params);

        List<Role> roleList = new ArrayList<>();
        try {
            roleList = ResultSetUtil.ResultSetToBean(rs, Role.class, 3);
        } finally {
            close();
        }
        return roleList;
    }

    @Override
    public int total() {
        String sql = "select count(*) from role";
        ResultSet rs = query(sql, null);
        int total=0;
        try {
            while (rs.next()) {
                total=rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
        return total;
    }

    @Override
    public int insert(Role role) {
        boolean insert = insert(role, Role.class,3);
        return 1;
    }
}
