import org.junit.Test;
import top.faroz.dao.StudentDao;
import top.faroz.pojo.Student;

import java.lang.reflect.Field;

/**
 * @ClassName ReflectTest
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午10:21
 * @Version 1.0
 **/
public class ReflectTest {
    @Test
    public void setIntegerTest() throws NoSuchFieldException, IllegalAccessException {
        Student stu = new Student();
        int stuId=1;
        Class<? extends Student> aClass = stu.getClass();
        Field cStuId = aClass.getDeclaredField("stuId");
        cStuId.setAccessible(true);
        cStuId.set(stu,stuId);
        System.out.println(stu);
    }
}
