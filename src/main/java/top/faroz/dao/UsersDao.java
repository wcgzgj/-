package top.faroz.dao;

import top.faroz.pojo.Users;

public interface UsersDao {
    Users selectByName(String name);
}
