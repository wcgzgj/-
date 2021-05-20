import org.junit.Test;
import top.faroz.pojo.Menu;
import top.faroz.service.MenuService;
import top.faroz.service.impl.MenuServiceImpl;

import java.util.List;

/**
 * @ClassName MenuServiceTest
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/20 上午9:01
 * @Version 1.0
 **/
public class MenuServiceTest {
    @Test
    public void getListTest() {
        MenuService menuService = new MenuServiceImpl();
        List<Menu> menuList = menuService.getMenuList();
        for (Menu menu : menuList) {
            System.out.println(menu.getMenuName());
            if (menu.getSecondList()!=null && menu.getSecondList().size()>0) {
                for (Menu menu1 : menu.getSecondList()) {
                    System.out.println("    "+menu1.getMenuName());
                }
            }
        }
    }
}
