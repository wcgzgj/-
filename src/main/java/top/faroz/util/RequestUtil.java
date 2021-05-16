package top.faroz.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @ClassName RequestUtil
 * @Description Request 工具类
 * @Author FARO_Z
 * @Date 2021/5/16 下午7:28
 * @Version 1.0
 **/
public class RequestUtil {

    /**
     * 获取req中传入的参数
     * @param req
     * @param c
     * @param <T>
     * @return
     */
    public static<T> T getBeanByReq(HttpServletRequest req,Class<T> c) {
        T obj = null;
        try {
            obj = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Map<String, String[]> params = req.getParameterMap();
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                fields[i].set(obj,params.get(fields[i].getName())[0]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return obj;
     }
}
