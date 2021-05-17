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
 * @ClassName StudentServlet
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午8:27
 * @Version 1.0
 **/
@WebServlet(name = "student",urlPatterns = "/Educational/student")
public class StudentServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "list":
                list(req,resp);
                return;
            case "search":
                search(req,resp);
                return;
            default:
                return;
        }
    }

    /**
     * 列表展示接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.queryAll();
        req.setAttribute("stuList",students);
        req.getRequestDispatcher("student/list.jsp").forward(req,resp);
    }

    /**
     * 查询接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stuName = req.getParameter("stuName");
        String stuNo = req.getParameter("stuNo");
        String sex = req.getParameter("sex");
        List<Student> students = studentService.search(stuName, stuNo, Integer.parseInt(sex));

        System.out.println("查询出的结果为:"+students);

        req.setAttribute("stuList",students);
        req.getRequestDispatcher("student/list.jsp").forward(req,resp);
    }
}
