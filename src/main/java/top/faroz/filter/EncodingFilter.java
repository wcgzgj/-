package top.faroz.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName EncodingFilter
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/14 下午5:58
 * @Version 1.0
 **/
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 全局设置编码方式
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //放行
        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
