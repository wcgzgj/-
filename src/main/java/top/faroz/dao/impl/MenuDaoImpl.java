package top.faroz.dao.impl;

import top.faroz.dao.MenuDao;
import top.faroz.pojo.Menu;
import top.faroz.util.ResultSetUtil;

import java.sql.ResultSet;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName MenuDaoImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/20 上午8:41
 * @Version 1.0
 **/
public class MenuDaoImpl extends BaseDao implements MenuDao {
    @Override
    public List<Menu> getMenuList() {
        String sql = "select * from menu";
        ResultSet rs = query(sql, null);
        List<Menu> menus = ResultSetUtil.ResultSetToBean(rs, Menu.class, 6);
        close();
        return menus;
    }
}
