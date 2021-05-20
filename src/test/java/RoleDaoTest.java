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
        System.out.println(roleDao.selectById(3));

    }
}
