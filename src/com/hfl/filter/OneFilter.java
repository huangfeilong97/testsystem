package com.hfl.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 防止恶意登录
 */
public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.过滤器增强服务
        //servletRequest.setCharacterEncoding("UTF-8");//对请求头/请求体查询编码

        HttpServletRequest request =(HttpServletRequest) servletRequest;

        String uri=request.getRequestURI();
        if(uri.indexOf("login")!=-1){
            //放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //2.利用令牌机制防止恶意登录
        //拦截后，通过请求对象向Tomcat索要当前用户的HttpSession。
        HttpSession token = request.getSession(false);//没有返回null

        if(token!=null){
            //放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //拒绝
        request.getRequestDispatcher("/login_error.html").forward(servletRequest,servletResponse);




    }
}
