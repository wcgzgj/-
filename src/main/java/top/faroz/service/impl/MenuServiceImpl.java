package top.faroz.service.impl;

import top.faroz.dao.MenuDao;
import top.faroz.dao.impl.MenuDaoImpl;
import top.faroz.pojo.Menu;
import top.faroz.service.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * @ClassName MenuServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/20 上午8:44
 * @Version 1.0
 **/
public class MenuServiceImpl implements MenuService {

    private MenuDao menuDao = new MenuDaoImpl();

    @Override
    public List<Menu> getMenuList() {
        List<Menu> menuList = menuDao.getMenuList();
        List<Menu> target = new ArrayList<>();

        //需要将该列表改造为二级列表
        for (Menu menu : menuList) {
            if (menu.getUpmenuId().equals(0)) {
               changeToMLList(menu,menuList,target);
            }
        }

        return target;
    }

    private void changeToMLList(Menu root,List<Menu> menuList,List<Menu> target) {
        boolean flag=false;
        //递归结束条件，判断是否有菜单是传入菜单的子菜单
        for (Menu menu : menuList) {
            if (root.getMenuId().equals(menu.getUpmenuId())){
                flag=true;
            }
        }
        if (!flag) {
            return;
        }

        target.add(root);
        List<Menu> secondList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getUpmenuId().equals(root.getMenuId())) {
                //递归子树
                changeToMLList(menu,menuList,target);
                secondList.add(menu);
            }
            root.setSecondList(secondList);
        }
    }
}
