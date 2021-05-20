package top.faroz.web;

import top.faroz.pojo.Role;
import top.faroz.pojo.Users;
import top.faroz.service.RoleService;
import top.faroz.service.impl.RoleServiceImpl;
import top.faroz.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "listRole":
                listRole(req,resp);
                break;
            default:
                return;
        }
    }


    protected void listRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("pageIndex");
        int pageIndex=index!=null && index.length()>0?Integer.parseInt(index):1;

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


}