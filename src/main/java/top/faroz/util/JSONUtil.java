package top.faroz.util;

import com.alibaba.fastjson.JSON;
import top.faroz.resp.CommonResp;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName JSONUtil
 * @Description 向前端传送数据的工具
 * 封装了 fastjson 和 writer.out
 * 传送的是通用返回值
 *
 * 这里一定要注意，使用 fastJson,也一定要将 fastJson的 jar包，放进tomcat中!!!
 *
 * @Author FARO_Z
 * @Date 2021/5/15 下午2:18
 * @Version 1.0
 **/
public class JSONUtil {

    /**
     * 将请求封装为一个
     * @param resp
     * @param content
     * @param success
     * @param message
     * @param <T>
     */
    public static<T> void send(HttpServletResponse resp,T content, boolean success, String message) {

        CommonResp<T> commonResp = new CommonResp<>();
        commonResp.setSuccess(success);
        if (message==null) {
            commonResp.setMessage("");
        } else {
            commonResp.setMessage(message);
        }
        commonResp.setContent(content);

        /**
         * 一定要将 FastJson的 jar 包，也在 tomcat的lib目录中放一份
         * 不然，会无法执行
         *
         * 貌似是因为 Servlet中的对象，都是由tomcat来管理的
         * 那对其中的对象使用第三方库，就一定要走 tomcat
         * 所以如果 tomcat中没有这个第三方库的话，就会报错
         */
        String json = JSON.toJSONString(commonResp);

        PrintWriter writer=null;
        try {
             writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println(json);
    }

    /**
     * 默认返回信息正确的发送方式
     * @param resp
     * @param content
     * @param <T>
     */
    public static<T> void send(HttpServletResponse resp,T content) {
        send(resp,content,true,"");
    }
}
