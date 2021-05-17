package top.faroz.dao.impl;

import top.faroz.dao.StudentDao;
import top.faroz.pojo.Student;
import top.faroz.util.ResultSetUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
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
        close();
        return students;
    }

    @Override
    public List<Student> search(String stuName, String StuNo, Integer sex) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from student where 1=1 ");
        List params = new ArrayList();
        if (stuName!=null && stuName.length()>0) {
            params.add(stuName);
            sb.append(" and stuname like ? ");
        }
        if (StuNo!=null && StuNo.length()>0) {
            params.add(StuNo);
            sb.append(" and stuno=? ");
        }
        if (sex!=-1) {
            params.add(sex);
            sb.append(" and sex=? ");
        }

        String sql = sb.toString();


        ResultSet rs = query(sql, params);
        List<Student> students = ResultSetUtil.ResultSetToBean(rs, Student.class);
        close();
        return students;
    }
}
