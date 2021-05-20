package top.faroz.service.impl;

import top.faroz.dao.UsersDao;
import top.faroz.dao.impl.UsersDaoImpl;
import top.faroz.pojo.Users;
import top.faroz.service.UsersService;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName UsersServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午8:31
 * @Version 1.0
 **/
public class UsersServiceImpl implements UsersService {

    private UsersDao usersDao = new UsersDaoImpl();

    @Override
    public Users login(String name, String password) {
        //查询有无该人
        Users user = usersDao.selectByName(name);
        if (user==null) {
            return null;
        }

        //密码是否匹配
        if (!Objects.equals(user.getPassword(),password)) {
            return null;
        }
        return user;
    }

    @Override
    public List<Users> getUsesList(int pageIndex, int pageSize) {
        return usersDao.getUsesList(pageIndex,pageSize);
    }

    @Override
    public int total() {
        return usersDao.total();
    }
}
