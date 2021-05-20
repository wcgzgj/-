package top.faroz.dao.impl;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import top.faroz.dao.UsersDao;
import top.faroz.pojo.Role;
import top.faroz.pojo.Users;
import top.faroz.util.ResultSetUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StudentDaoImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午8:29
 * @Version 1.0
 **/
public class UsersDaoImpl extends BaseDao implements UsersDao {

    @Override
    public Users selectByName(String name) {
        String sql = "select * from users where loginname = ?";
        List params = new ArrayList();
        params.add(name);
        ResultSet rs = query(sql, params);
        List<Users> users = ResultSetUtil.ResultSetToBean(rs, Users.class,10);
        close();
        if (users==null || users.size()==0) {
            return null;
        }
        return users.get(0);
    }

    /**
     * 查询用户列表
     * 这里，还要显示用户的角色名称（即需要两表联查）
     *
     * 这里一定要注意，一定要把主键ID查询出来，不然，后面无法修改或者删除
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Users> getUsesList(int pageIndex, int pageSize) {
        String sql = "select userid,loginname,realname,rolename from users,role where users.roleid=role.roleid limit ?,?";
        ArrayList params = new ArrayList();
        /**
         * 前端传来的，是从1开始数的页码
         * 但是我们的数据库limit查信息，是从0开始的
         */
        params.add((pageIndex-1)*pageSize);
        params.add(pageSize);
        ResultSet rs = query(sql, params);

        List<Users> userList = new ArrayList<>();
        try {
            while (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getInt("userid"));
                user.setLoginName(rs.getString("loginname"));
                user.setRealName(rs.getString("realname"));

                Role role = new Role();
                role.setRoleName(rs.getString("rolename"));

                user.setRole(role);
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close();
        }
        return userList;
    }

    @Override
    public int total() {
        String sql = "select count(*) from users,role where users.roleid=role.roleid";
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

}
