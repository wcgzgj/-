import org.junit.Test;
import top.faroz.dao.RoleDao;
import top.faroz.dao.impl.RoleDaoImpl;
import top.faroz.pojo.Role;

/**
 * @ClassName RoleDaoTest
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/20 下午3:23
 * @Version 1.0
 **/
public class RoleDaoTest {

    private RoleDao roleDao = new RoleDaoImpl();

    @Test
    public void selectByIdTest() {
        Role r1 = roleDao.selectById(1);
        Role r2 = roleDao.selectById(2);
        System.out.println("r1:"+r1);
        System.out.println("r2:"+r2);
    }
}
