package top.faroz.web;

import javafx.beans.value.WritableObjectValue;
import javafx.scene.layout.BackgroundRepeat;
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
            case "login":
                login(req,resp);
                break;
            case "logout":
                logout(req,resp);
                break;
            default:
                return;
        }
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //清空session
        req.getSession().invalidate();

        //跳转到登录页面
        // resp.sendRedirect("login.jsp");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<script>top.location.href='/login.jsp';alert('退出成功!');</script>");
    }


}
