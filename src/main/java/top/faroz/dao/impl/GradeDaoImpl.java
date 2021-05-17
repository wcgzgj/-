package top.faroz.dao.impl;

import top.faroz.dao.GradeDao;
import top.faroz.dao.StudentDao;
import top.faroz.pojo.Grade;
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
public class GradeDaoImpl extends BaseDao implements GradeDao {

    @Override
    public List<Grade> queryAll() {
        String sql = "select * from grade";
        ResultSet rs = query(sql, null);
        List<Grade> grades = ResultSetUtil.ResultSetToBean(rs, Grade.class);
        close();
        return grades;
    }
}
