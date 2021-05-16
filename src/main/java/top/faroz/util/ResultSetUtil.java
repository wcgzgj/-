package top.faroz.util;

import java.lang.reflect.Field;
import java.sql.Date;
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
        if(rs==null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        try {
            while (rs.next()) {
                T obj = null;
                obj = c.newInstance();
                Field[] fields = c.getDeclaredFields();

                //获取数据库中队列的个数，因为 pojo中的属性个数，可能比数据库多
                int len = rs.getMetaData().getColumnCount();
                for (int i = 0; i < len; i++) {
                    fields[i].setAccessible(true);
                    if (fields[i].getType()==java.util.Date.class) {
                        Object param = rs.getObject(i + 1, java.sql.Date.class);
                        //这里要注意时间的类型
                        java.sql.Date sqlDate = (java.sql.Date) param;
                        java.util.Date javaDate = DateUtil.sqlDate2JavaDate(sqlDate);

                        //为 Bean设置属性值
                        fields[i].set(obj,javaDate);
                    } else {
                        //获取 ResultSet 中的属性值
                        Object param = rs.getObject(i + 1, fields[i].getType());
                        fields[i].set(obj, param);
                    }
                    //System.out.println("赋值完成！\n\n");
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

    public static<T> List<T> ResultSetToBean(ResultSet rs, Class<T> c,int colNum) {
        if(rs==null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        try {
            while (rs.next()) {
                T obj = null;
                obj = c.newInstance();
                Field[] fields = c.getDeclaredFields();

                //为了符合复杂查询的操作
                int len = colNum;
                for (int i = 0; i < len; i++) {
                    fields[i].setAccessible(true);
                    //System.out.println("赋值的属性为:"+fields[i].getName());

                    if (fields[i].getType()==java.util.Date.class) {
                        Object param = rs.getObject(i + 1, java.sql.Date.class);
                        //这里要注意时间的类型
                        // System.out.println("是sqlDate类型");
                        java.sql.Date sqlDate = (java.sql.Date) param;
                        java.util.Date javaDate = DateUtil.sqlDate2JavaDate(sqlDate);

                        //为 Bean设置属性值
                        fields[i].set(obj,javaDate);
                    } else {
                        //获取 ResultSet 中的属性值
                        Object param = rs.getObject(i + 1, fields[i].getType());
                        fields[i].set(obj, param);
                    }
                    //System.out.println("赋值完成！\n\n");
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
