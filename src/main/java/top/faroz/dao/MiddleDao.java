package top.faroz.dao;

public interface MiddleDao {
    void insert(Integer roleId,Integer[] menuIds);

    void deleteByRoleId(Integer roleId);
}
