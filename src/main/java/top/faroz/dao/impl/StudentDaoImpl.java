package top.faroz.dao.impl;

import top.faroz.dao.StudentDao;
import top.faroz.pojo.Student;
import top.faroz.util.ResultSetUtil;

import java.sql.ResultSet;
import java.util.List;

/**
 * @ClassName StudentDaoImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午8:29
 * @Version 1.0
 **/
public class StudentDaoImpl extends BaseDao implements StudentDao {

    @Override
    public List<Student> queryAll() {
        String sql = "select * from student";
        ResultSet rs = query(sql, null);
        List<Student> students = ResultSetUtil.ResultSetToBean(rs, Student.class,14);
        return students;
    }
}
