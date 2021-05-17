package top.faroz.dao;

import top.faroz.pojo.Student;

import java.util.List;

public interface StudentDao {
    List<Student> queryAll();
    List<Student> search(String stuName, String StuNo,Integer sex);
    void add(Student student);
}
