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
 * @ClassName ListStuServlet
 * @Description 包括分页数据，模糊查询条件
 * @Author FARO_Z
 * @Date 2021/5/16 下午11:46
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/Educational/student/search")
public class ListStuServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获得参数
        //1.1 模糊查询条件
        String stuName = req.getParameter("stuName");
        String stuNo = req.getParameter("stuNo");
        String sex = req.getParameter("sex");

        //1.2 分页数据  limit 开始位置，显示条数
        //页码值
        String pageIndex = req.getParameter("pageIndex");
        //如果没有传入 pageIndex的值，默认查询第一页
        int index = pageIndex==null?1:Integer.parseInt(pageIndex);
        List<Student> students = studentService.search(stuName, stuNo, sex==null?-1:Integer.parseInt(sex),index,5);

        //获取总条数
        // 总条数%每页条数>0?总条数/每页条数+1  总条数/每页条数
        int total = studentService.total(stuName, stuNo, sex == null ? -1 : Integer.parseInt(sex)); //总条数
        int totalPages = total % 5 > 0 ? total / 5 + 1 : total / 5; //总页数



        //将查询条件，再放入 req中，是为了在前端输入框中，显示用户之前的输入
        req.setAttribute("stuList",students);
        req.setAttribute("stuName",stuName);
        req.setAttribute("stuNo",stuNo);
        req.setAttribute("sex",sex);
        req.setAttribute("pageIndex",pageIndex);
        req.setAttribute("size",5);
        req.setAttribute("total",total);
        req.setAttribute("totalPages",totalPages);


        req.getRequestDispatcher("/Educational/student/list.jsp").forward(req,resp);
    }
}
