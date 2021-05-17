package top.faroz.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AddStuServlet
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/17 下午4:38
 * @Version 1.0
 **/
@WebServlet(name = "addStu",urlPatterns = "/Educational/student/addStu")
public class AddStuServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
