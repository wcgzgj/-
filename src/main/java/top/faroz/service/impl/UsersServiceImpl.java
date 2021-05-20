package top.faroz.service.impl;

import top.faroz.dao.RoleDao;
import top.faroz.dao.UsersDao;
import top.faroz.dao.impl.RoleDaoImpl;
import top.faroz.dao.impl.UsersDaoImpl;
import top.faroz.pojo.Menu;
import top.faroz.pojo.Role;
import top.faroz.pojo.Users;
import top.faroz.service.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName UsersServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午8:31
 * @Version 1.0
 **/
public class UsersServiceImpl implements UsersService {

    private UsersDao usersDao = new UsersDaoImpl();
    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public Users login(String name, String password) {
        //查询有无该人
        Users user = usersDao.selectByName(name);
        if (user==null) {
            return null;
        }
        //密码是否匹配
        if (!Objects.equals(user.getPassword(),password)) {
            return null;
        }

        //根据用户的角色信息，查询角色  菜单信息（三表联查）
        Integer roleId = user.getRoleId();
        Role role = roleDao.selectById(roleId);

        //对角色菜单，进行 级数分类
        List<Menu> menuList = role.getMenuList();
        List<Menu> newMenuList = new ArrayList<>();

        //需要将该列表改造为二级列表
        for (Menu menu : menuList) {
            if (menu.getUpmenuId().equals(0)) {
                changeToMLList(menu,menuList,newMenuList);
            }
        }
        //将分好级的菜单列表，再次赋给 role
        role.setMenuList(newMenuList);

        //测试有没有分成功
        // for (Menu menu : newMenuList) {
        //     System.out.println(menu.getMenuName());
        //     if (menu.getSecondList()!=null && menu.getSecondList().size()>0) {
        //         for (Menu menu1 : menu.getSecondList()) {
        //             System.out.println("    "+menu1.getMenuName());
        //         }
        //     }
        // }


        /**
         * 最后，将该用户的角色信息，存入该用户中
         *
         * 这里，很好的体现了：用户包含角色  角色包含菜单（权限）  的思想
         */
        user.setRole(role);
        return user;
    }

    @Override
    public List<Users> getUsesList(int pageIndex, int pageSize) {
        return usersDao.getUsesList(pageIndex,pageSize);
    }

    @Override
    public int total() {
        return usersDao.total();
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
