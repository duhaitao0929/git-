package com.lening.filter;

import com.lening.entity.UserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PowerFilter implements Filter {
    Set<String> noturls=new HashSet<String>();
    public void init(FilterConfig config) throws ServletException {
        String notCheckUrl = config.getInitParameter("notCheckUrl");
        for (String url : notCheckUrl.split(",")) {
            noturls.add(url.trim());
        }
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req=(HttpServletRequest)request;
        String uri = req.getRequestURI();
        if (noturls.contains(uri)){
            chain.doFilter(request,response);
        }else {
            UserBean ub = (UserBean) req.getSession().getAttribute("ub");
            if (ub!=null){
                chain.doFilter(request,response);
            }else {
                req.setAttribute("msg","请先登录!!");
                request.getRequestDispatcher("index.jsp").forward(req,response);
            }
        }
    }
    public void destroy() {
    }




}
