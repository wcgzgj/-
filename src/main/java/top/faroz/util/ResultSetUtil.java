package top.faroz.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ResultSetUtil
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/8 下午3:17
 * @Version 1.0
 **/
public class ResultSetUtil {

    /**
     * 通过反射，根据 ResultSet 获取对应的 Bean
     * @param rs
     * @param c
     * @param <T>
     * @return
     */
    public static<T> List<T> ResultSetToBean(ResultSet rs, Class<T> c) {
        List<T> list = new ArrayList<>();
        try {
            while (rs.next()) {
                T obj = null;
                obj = c.newInstance();
                Field[] fields = c.getDeclaredFields();
                int len = fields.length;
                for (int i = 0; i < len; i++) {
                    fields[i].setAccessible(true);
                    //获取 ResultSet 中的属性值
                    Object param = rs.getObject(i + 1, fields[i].getType());
                    //为 Bean设置属性值
                    fields[i].set(obj, param);
                }
                //resultSet可能查询到了多个值
                list.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return list;
    }
}
