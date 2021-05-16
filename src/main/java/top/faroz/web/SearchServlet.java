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
        System.out.println("进入了Servlet!!!");

        String stuName = req.getParameter("stuName");
        String stuNo = req.getParameter("stuNo");
        String sex = req.getParameter("sex");

        System.out.println("stuName:"+stuName);


        List<Student> students = studentService.search(stuName, stuNo, Integer.parseInt(sex));

        System.out.println("查询出的结果为:"+students);

        req.setAttribute("stuList",students);
        req.getRequestDispatcher("/Educational/student/list.jsp").forward(req,resp);
    }
}