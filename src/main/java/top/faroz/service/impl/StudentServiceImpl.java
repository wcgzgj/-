package top.faroz.service.impl;

import top.faroz.dao.StudentDao;
import top.faroz.dao.impl.StudentDaoImpl;
import top.faroz.pojo.Student;
import top.faroz.service.StudentService;

import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午8:31
 * @Version 1.0
 **/
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> queryAll() {
        List<Student> students = studentDao.queryAll();
        return students;
    }
}
