package top.faroz.service;

import top.faroz.pojo.Users;

import java.util.List;

public interface UsersService {
    Users login(String name,String password);

    /**
     * 获取用户的列表
     */
    List<Users> getUsesList(int pageIndex, int pageSize);

    /**
     * 获取用户的总个数
     */
    int total();
}
