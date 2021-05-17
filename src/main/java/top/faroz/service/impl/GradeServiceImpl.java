package top.faroz.service.impl;

import top.faroz.dao.GradeDao;
import top.faroz.dao.impl.GradeDaoImpl;
import top.faroz.pojo.Grade;
import top.faroz.service.GradeService;

import java.util.List;

/**
 * @ClassName GradeServiceImpl
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午8:31
 * @Version 1.0
 **/
public class GradeServiceImpl implements GradeService {

    private GradeDao gradeDao = new GradeDaoImpl();

    @Override
    public List<Grade> queryAll() {
        return gradeDao.queryAll();
    }
}
