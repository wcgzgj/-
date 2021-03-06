package top.faroz.web;

import top.faroz.pojo.Menu;
import top.faroz.pojo.Role;
import top.faroz.pojo.Users;
import top.faroz.service.MenuService;
import top.faroz.service.RoleService;
import top.faroz.service.impl.MenuServiceImpl;
import top.faroz.service.impl.RoleServiceImpl;
import top.faroz.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName StudentServlet
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午8:27
 * @Version 1.0
 **/
@WebServlet(name = "user",urlPatterns = "/power/role")
public class RoleServlet extends HttpServlet {

    private RoleService roleService = new RoleServiceImpl();
    private MenuService menuService = new MenuServiceImpl();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        switch (method) {
            case "listRole":
                listRole(req,resp);
                break;
            case "addRole":
                addRole(req,resp);
                break;
            case "add":
                add(req,resp);
                break;
            case "roleInfo":
                roleInfo(req,resp);
                break;
            case "deleteRole":
                deleteRole(req,resp);
                break;
            case "toUpdateView":
                toUpdateView(req,resp);
                break;
            case "updateRole":
                updateRole(req,resp);
                break;
            default:
                return;
        }
    }


    protected void listRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("pageIndex");
        int pageIndex=index!=null && index.length()>0?Integer.parseInt(index):1;
        if (pageIndex<=0) {
            pageIndex=1;
        }

        List<Role> roleList = roleService.getRoleList(pageIndex, 5);
        int total = roleService.total();

        System.out.println("pageIndex为:"+pageIndex);
        System.out.println("查询出的数据为:"+roleList);
        System.out.println("总条数为:"+total);

        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setTotal(total);
        pageUtil.setDataList(roleList);


        req.setAttribute("pageUtil",pageUtil);
        req.setAttribute("roleList",roleList);
        req.getRequestDispatcher("/power/role/list.jsp").forward(req,resp);
    }


    protected void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Menu> menuList = menuService.getMenuList();


        req.setAttribute("menuList",menuList);
        req.getRequestDispatcher("/power/role/add.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleName = req.getParameter("roleName");
        String[] menuIds = req.getParameterValues("menuId");
        String state = req.getParameter("state");
        int roleState = state != null || state.length() > 0 ? Integer.parseInt(state) : 1;


        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleState(roleState);

        roleService.insert(role,menuIds);

        req.getRequestDispatcher("/power/role?method=listRole").forward(req,resp);
    }



    protected void roleInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("roleId");
        int roleId = id != null && id.length() > 0 ? Integer.parseInt(id) : 0;
        //获取当前角色信息
        Role role = roleService.selectById(roleId);
        //获取菜单列表
        List<Menu> menuList = menuService.getMenuList();
        List<Menu> roleMenuList = role.getMenuList();

        /**
         * 这里，如果该位置被选过了，要有标记
         * 不然，前端无法显示
         */
        for (Menu menu : menuList) {
            for (Menu menu1 : roleMenuList) {
                if (menu1.getMenuId().equals(menu.getMenuId())) {
                    menu.setChecked(1);
                }
            }
            //二层也要判断，有没有被选中
            List<Menu> secondList = menu.getSecondList();
            if (secondList!=null && secondList.size()>0) {
                for (Menu menu1 : secondList) {
                    for (Menu menu2 : roleMenuList) {
                        if (menu1.getMenuId().equals(menu2.getMenuId())) {
                            menu1.setChecked(1);
                        }
                    }
                }
            }
        }


        System.out.println("查出的role为:"+role);

        req.setAttribute("menuList",menuList);
        req.setAttribute("role",role);
        req.getRequestDispatcher("/power/role/info.jsp").forward(req,resp);
    }


    /**
     * 删除 role 之前
     * 一定要把 middle 中的相关信息 ，先给删除了
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("roleId");
        int roleId = id != null && id.length() > 0 ? Integer.parseInt(id) : 0;

        roleService.deleteById(roleId);

        resp.sendRedirect("/power/role?method=listRole");
    }


    /**
     * 跳转到更新界面
     * 要展示当前用户已知的信息
     * 可以参考 Info页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void toUpdateView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("roleId");
        int roleId = id != null && id.length() > 0 ? Integer.parseInt(id) : 0;
        //获取当前角色信息
        Role role = roleService.selectById(roleId);
        //获取菜单列表
        List<Menu> menuList = menuService.getMenuList();
        List<Menu> roleMenuList = role.getMenuList();

        /**
         * 这里，如果该位置被选过了，要有标记
         * 不然，前端无法显示
         */
        for (Menu menu : menuList) {
            for (Menu menu1 : roleMenuList) {
                if (menu1.getMenuId().equals(menu.getMenuId())) {
                    menu.setChecked(1);
                }
            }
            //二层也要判断，有没有被选中
            List<Menu> secondList = menu.getSecondList();
            if (secondList!=null && secondList.size()>0) {
                for (Menu menu1 : secondList) {
                    for (Menu menu2 : roleMenuList) {
                        if (menu1.getMenuId().equals(menu2.getMenuId())) {
                            menu1.setChecked(1);
                        }
                    }
                }
            }
        }


        req.setAttribute("menuList",menuList);
        req.setAttribute("role",role);
        req.getRequestDispatcher("/power/role/edit.jsp").forward(req,resp);
    }



    /**
     * 修改用户信息
     * 在修改 role信息的同时，
     * 还要同时修改 middle 表
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("roleId");
        int roleId = Integer.parseInt(id);
        String roleName = req.getParameter("roleName");
        String[] menuIds = req.getParameterValues("menuId");
        String state = req.getParameter("state");
        int roleState = state != null || state.length() > 0 ? Integer.parseInt(state) : 1;


        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        role.setRoleState(roleState);


        roleService.update(role,menuIds);

        resp.sendRedirect("/power/role?method=listRole");
    }


}
