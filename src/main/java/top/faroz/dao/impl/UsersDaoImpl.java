package top.faroz.dao.impl;

import top.faroz.dao.UsersDao;
import top.faroz.pojo.Users;
import top.faroz.util.ResultSetUtil;

import java.sql.ResultSet;
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
}
