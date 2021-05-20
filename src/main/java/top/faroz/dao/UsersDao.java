package top.faroz.dao;

import top.faroz.pojo.Users;

import java.util.List;

public interface UsersDao {
    Users selectByName(String name);

    /**
     * 获取用户的列表
     */
    List<Users> getUsesList(int pageIndex, int pageSize);

    /**
     * 获取用户的总个数
     */
    int total();
}
