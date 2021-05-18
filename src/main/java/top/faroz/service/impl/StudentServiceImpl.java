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

    @Override
    public List<Student> search(String stuName, String StuNo, Integer sex,int pageIndex,int pageSize) {
        return studentDao.search(stuName,StuNo,sex,pageIndex,pageSize);
    }

    @Override
    public void add(Student student) {
        studentDao.add(student);
    }

    @Override
    public int total(String name, String stuno, int sex) {
        return studentDao.total(name,stuno,sex);
    }

    @Override
    public Student selectById(Integer id) {
        return studentDao.selectById(id);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(Integer id) {
        studentDao.delete(id);
    }

}
