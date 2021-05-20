package top.faroz.dao.impl;

import top.faroz.dao.MiddleDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @ClassName MiddleDaoImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/20 下午2:17
 * @Version 1.0
 **/
public class MiddleDaoImpl extends BaseDao implements MiddleDao {

    @Override
    public void insert(Integer roleId, Integer[] menuIds) {
        System.out.println("待插入的roleId:"+roleId);
        System.out.println("待插入的menuIds:"+ Arrays.toString(menuIds));
        String sql = "insert into middle values(null,?,?)";
        PreparedStatement statement = getStatement(sql);
        try {
            for (int i = 0; i < menuIds.length; i++) {
                statement.setInt(1,roleId);
                statement.setInt(2,menuIds[i]);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
