package top.faroz.service;

import top.faroz.pojo.Student;

import java.util.List;

public interface StudentService {
    List<Student> queryAll();
    List<Student> search(String stuName, String StuNo,Integer sex,int pageIndex,int pageSize);
    void add(Student student);

    /**
     * 获取总条数，要根据模糊查询的条件来获取
     * @param name
     * @param stuno
     * @param sex
     * @return
     */
    int total(String name,String stuno,int sex);

    Student selectById(Integer id);
}
