import org.junit.Test;
import top.faroz.dao.UsersDao;
import top.faroz.dao.impl.UsersDaoImpl;
import top.faroz.pojo.Users;

/**
 * @ClassName UserDaoTest
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午9:44
 * @Version 1.0
 **/
public class UserDaoTest {

    private UsersDao usersDao = new UsersDaoImpl();

    @Test
    public void selectBuNameTest() {
        Users admin = usersDao.selectByName("admin");
        System.out.println(admin);
    }
}
