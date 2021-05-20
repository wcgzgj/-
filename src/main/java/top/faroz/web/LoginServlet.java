package top.faroz.web;

import top.faroz.pojo.Users;
import top.faroz.service.UsersService;
import top.faroz.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName LoginServlet
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/20 上午10:14
 * @Version 1.0
 **/
@WebServlet(name = "login",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UsersService usersService = new UsersServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("TxtUserName");
        String password = req.getParameter("TxtPassword");
        Users user = usersService.login(userName, password);
        System.out.println("user信息为:"+user);
        //登录失败
        if (user==null) {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>location.href='/login.jsp';alert('用户名或密码不正确！');</script>");
        } else {
            //保存用户信息
            req.getSession().setAttribute("user",user);

            //跳转到主页面
            resp.sendRedirect("/index.jsp");
        }
    }
}
