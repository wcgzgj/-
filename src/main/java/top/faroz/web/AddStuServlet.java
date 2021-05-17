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
import java.io.PrintWriter;
import java.util.Date;

/**
 * @ClassName AddStuServlet
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/17 下午4:38
 * @Version 1.0
 **/
@WebServlet(name = "addStu",urlPatterns = "/Educational/student/addStu")
public class AddStuServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        String introdction = req.getParameter("introdction");

        Student student = new Student();
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
        student.setIntrodction(introdction);
        student.setRegDate(new Date());


        studentService.add(student);

        PrintWriter writer = resp.getWriter();
        writer.println("<script>location.href='/Educational/student?method=list';alert('新增成功！');</script>");
    }

}
