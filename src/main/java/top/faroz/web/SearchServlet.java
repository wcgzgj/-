package top.faroz.web;

import top.faroz.pojo.Student;
import top.faroz.service.StudentService;
import top.faroz.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName SearchServlet
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午11:46
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/Educational/student/search")
public class SearchServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String stuName = req.getParameter("stuName");
        String stuNo = req.getParameter("stuNo");
        String sex = req.getParameter("sex");



        List<Student> students = studentService.search(stuName, stuNo, Integer.parseInt(sex));

        //将查询条件，再放入 req中，是为了在前端输入框中，显示用户之前的输入
        req.setAttribute("stuList",students);
        req.setAttribute("stuName",stuName);
        req.setAttribute("stuNo",stuNo);
        req.setAttribute("sex",sex);

        req.getRequestDispatcher("/Educational/student/list.jsp").forward(req,resp);
    }
}
