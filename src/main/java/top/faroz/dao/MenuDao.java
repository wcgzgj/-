package top.faroz.dao;

import top.faroz.pojo.Menu;

import java.util.List;

/**
 * @ClassName MenuDao
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/19 下午4:47
 * @Version 1.0
 **/
public interface MenuDao {

    List<Menu> getMenuList(int pageIndex, int pageSize);
    
}
