package top.faroz.service;

import top.faroz.pojo.Student;

import java.util.List;

public interface StudentService {
    List<Student> queryAll();
    List<Student> search(String stuName, String StuNo,Integer sex);
}
