package top.faroz.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName DBUtil
 * @Description 只能被 Dao 层继承
 * 外部不得使用该类中的方法
 * @Author FARO_Z
 * @Date 2021/5/8 上午9:55
 * @Version 1.0
 **/
public class BaseDao {
    private static String url=null;
    private static String name=null;
    private static String password=null;
    private static String driver=null;
    private static DruidDataSource dataSource = new DruidDataSource();


    static {

        /**
         * Druid
         */
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        driver=bundle.getString("driver");
        url=bundle.getString("url");
        name=bundle.getString("name");
        password=bundle.getString("password");

        dataSource.setDriverClassName(driver);
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        dataSource.setUrl(url);

    }


    //受影响的行数
    private int count=0;

    private Connection conn = null;
    protected PreparedStatement stmt = null;

    /**
     * 获取数据库连接
     * @return
     */
    protected Connection getConnection() {
        try {
            conn = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * 获取preparedStatement
     * @param sql
     * @param list
     * @return
     */
    protected PreparedStatement getStatement(String sql, List list) {
        getConnection();
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (list!=null && list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    stmt.setObject(i+1,list.get(i));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return stmt;
    }

    protected PreparedStatement getStatement(String sql) {
        PreparedStatement statement = getStatement(sql, null);
        return statement;
    }

    /**
     * 依据新传入的对象，更新数据库中信息
     * 使用反射，进行封装
     * @param o
     * @param c
     * @param <T>
     * @return
     */
    protected <T> int update(Object o,Class<T> c) {
           return update(o,c,"id",c.getDeclaredFields().length);
    }

    protected <T> int update(Object o,Class<T> c,String idName,int len) {
        T obj=null;
        if (o != null) {
            obj=(T)o;
        }
        Field[] fields = c.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append("update ")
                .append(c.getSimpleName().toLowerCase())
                .append(" ")
                .append("set ");
        for (int i = 0; i < len - 1; i++) {
            sb.append(fields[i].getName().toLowerCase())
                    .append("=?,");
        }
        sb.append(fields[len-1].getName())
                .append("=? ");
        sb.append("where "+idName+"=?");
        //需要根据 id，来修改对应的信息
        String sql=sb.toString();
        //System.out.println(sql);

        //尝试设置值
        Field[] declaredFields = c.getDeclaredFields();
        List params = new ArrayList();
        // int len = declaredFields.length;
        for (int i = 0; i < len; i++) {
            fields[i].setAccessible(true);
            try {
                params.add(fields[i].get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //where id =?  设置 id
        try {
            params.add(fields[0].get(obj));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        PreparedStatement stmt = getStatement(sql, params);
        try {
            count = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return count;
    }

    /**
     * 查询
     * 因为还要支持复杂查询（内联，外联，多表查询）
     * 所以没有办法通过反射，获取对应的实例
     * @param sql
     * @param params
     * @return
     */
    protected ResultSet query(String sql,List params) {
        PreparedStatement stmt = getStatement(sql, params);
        ResultSet res = null;
        try {
             res= stmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    /**
     * 使用反射，向数据库中，添加信息
     * @param o
     * @param c
     * @param <T>
     * @return
     */
    protected <T> boolean insert(Object o,Class<T> c) {
       return insert(o,c,c.getDeclaredFields().length);
    }


    protected <T> boolean insert(Object o,Class<T> c,int len) {
        T obj = null;
        if (o!=null) {
            obj = (T) o;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ")
                .append(c.getSimpleName().toLowerCase())
                .append(" ")
                .append("values(");

        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i <len-1; i++) {
            sb.append("?,");
        }
        sb.append("?)");
        //获得 sql 语句
        String sql=sb.toString();
        System.out.println("sql语句为："+sql);

        //获取所有参数
        List params = new ArrayList();
        for (int i = 0; i < len; i++) {
            fields[i].setAccessible(true);

            Object tmp=null;
            try {
                tmp = fields[i].get(obj);
                System.out.println("获取的属性值为:"+tmp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            params.add(tmp);
        }
        PreparedStatement stmt = getStatement(sql, params);
        boolean res=false;
        try {
            res = stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }


    /**
     * 根据主键进行删除
     * @param id
     */
    protected<T> boolean delete(int id,Class<T>c) {
        return delete(id,c,"id");
    }

    protected<T> boolean delete(int id,Class<T>c,String idName) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ")
                .append(c.getSimpleName())
                .append(" ")
                .append("where "+idName+" =?");
        String sql = sb.toString();
        List params = new ArrayList();
        params.add(id);
        PreparedStatement stmt = getStatement(sql, params);
        boolean res=false;
        try {
            res = stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }


    /**
     * 关闭全部连接
     */
    protected void close() {
        if (stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
