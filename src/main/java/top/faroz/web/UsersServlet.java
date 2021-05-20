package top.faroz.web;

import top.faroz.pojo.Users;
import top.faroz.service.UsersService;
import top.faroz.service.impl.UsersServiceImpl;
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
@WebServlet(name = "user",urlPatterns = "/power/user/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService = new UsersServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "logout":
                logout(req,resp);
                break;
            case "userManage":
                userManage(req,resp);
                break;
            default:
                return;
        }
    }


    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //清空session
        req.getSession().invalidate();

        //跳转到登录页面
        // resp.sendRedirect("login.jsp");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<script>top.location.href='/login.jsp';alert('退出成功!');</script>");
    }

    protected void userManage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("pageIndex");
        int pageIndex=index!=null && index.length()>0?Integer.parseInt(index):1;

        List<Users> userList = usersService.getUsesList(pageIndex,5);
        int total = usersService.total();

        System.out.println("查询出的数据为:"+userList);
        System.out.println("总条数为:"+total);

        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setTotal(total);
        pageUtil.setDataList(userList);


        req.setAttribute("pageUtil",pageUtil);
        req.getRequestDispatcher("/power/user/list.jsp").forward(req,resp);
    }


}
