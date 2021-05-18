package top.faroz.dao.impl;

import top.faroz.dao.StudentDao;
import top.faroz.pojo.Student;
import top.faroz.util.ResultSetUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        //14列，是因为 JavaBean中，最后一列是教师信息
        List<Student> students = ResultSetUtil.ResultSetToBean(rs, Student.class,14);
        close();
        return students;
    }

    @Override
    public List<Student> search(String stuName, String StuNo, Integer sex,int pageIndex,int pageSize) {
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
        //分页
        sb.append(" limit ?,?");
        /**
         * limit 是从 0开始的，但是我们前端传来的是从1开始的
         *  所以   limit(pageIndex-1)*pageSize,pageSize
         */
        params.add((pageIndex-1)*pageSize);
        params.add(pageSize);


        String sql = sb.toString();


        ResultSet rs = query(sql, params);
        List<Student> students = ResultSetUtil.ResultSetToBean(rs, Student.class);
        close();
        return students;
    }

    @Override
    public void add(Student student) {
        insert(student, Student.class,15);
        close();
    }

    @Override
    public int total(String name, String stuno, int sex) {
        int total = 0;
        ArrayList params = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from student where 1=1 ");
        if (name!=null && name.length()>0) {
            sb.append("and stuname like ?");
            params.add("%"+name+"%");
        }
        if (stuno!=null && stuno.length()>0) {
            sb.append("and stuno=? ");
            params.add(stuno);
        }
        if (sex!=-1) {
            sb.append(" and sex=? ");
            params.add(sex);
        }
        String sql = sb.toString();
        ResultSet rs = query(sql, params);
        try {
            while (rs.next()) {
                total=rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close();
        return total;
    }

    @Override
    public Student selectById(Integer stuId) {
        String sql = "select * from student where stuid=?";
        ArrayList params = new ArrayList();
        params.add(stuId);
        ResultSet rs = query(sql, params);
        List<Student> students = ResultSetUtil.ResultSetToBean(rs, Student.class, 14);
        close();
        if (students==null || students.size()==0) {
            return null;
        }
        return students.get(0);
    }


}
