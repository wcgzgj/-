package top.faroz.web;

import top.faroz.dao.StudentDao;
import top.faroz.dao.impl.StudentDaoImpl;
import top.faroz.pojo.Student;
import top.faroz.service.StudentService;
import top.faroz.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @ClassName EditStuServlet
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/18 下午4:51
 * @Version 1.0
 **/
@WebServlet(name = "edit",urlPatterns = "/Educational/student/edit")
public class EditStuServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sid = req.getParameter("sid");
        String stuNo = req.getParameter("stuNo");
        String stuname = req.getParameter("stuName");
        String gid = req.getParameter("gid");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String registered = req.getParameter("registered");
        String address = req.getParameter("address");
        String politics = req.getParameter("politics");
        String idnumber = req.getParameter("idNumber");
        String profession = req.getParameter("profession");
        String introduction = req.getParameter("introduction");

        Student student = new Student();
        student.setStuId(Integer.parseInt(sid));
        student.setStuNo(stuNo);
        student.setStuName(stuname);
        student.setGid(Integer.parseInt(gid));
        student.setSex(Integer.parseInt(sex));
        student.setEmail(email);
        student.setPhone(phone);
        student.setRegistered(registered);
        student.setAddress(address);
        student.setPolitics(politics);
        student.setIdNumber(idnumber);
        student.setProfession(profession);
        student.setIntroduction(introduction);
        student.setRegDate(new Date());

        studentService.update(student);

        PrintWriter writer = resp.getWriter();
        writer.println("<script>location.href='/Educational/student/search';alert('修改成功！');</script>");
    }
}
