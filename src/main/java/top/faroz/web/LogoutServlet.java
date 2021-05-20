package top.faroz.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName LogoutServlet
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/20 下午6:52
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //清空session
        req.getSession().invalidate();

        //跳转到登录页面
        // resp.sendRedirect("login.jsp");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<script>top.location.href='/login.jsp';alert('退出成功!');</script>");
    }
}
