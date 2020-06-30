package com.lening.interceptor;

import com.lening.entity.UserBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public class PowerInterceptor implements HandlerInterceptor {

    private List<String> exceptUrls;

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        //特权url，不管有没有登录直接放行
        if (exceptUrls.contains(uri)){
            return true;
        }else {
            //不是特权要判断有没有登录
            UserBean ub = (UserBean) request.getSession().getAttribute("ub");
            if (ub!=null){
                /**
                 * 登录成功了，但是只能访问自己的，
                 * 目前是只要登录成功就都能访问了
                 * 需要把当前这个用户拥有的url查出来，再次进行判断
                 * 用户拥有的url目前菜单部分在数据库，还有一部分，哪里都没有，必须全部写进数据库
                 * 目前在数据库的只有3个菜单的url，和不需要过滤的两个，只有5个。
                 * 但是我们项目里面已经有（controller里面每一个方法对应一个url，这个url必须全部进库）
                 * 12个，还有7个需要进库
                 */
                Set<String> urls = (Set<String>) request.getSession().getAttribute("urls");
                if (urls!=null){
                    uri=uri.substring(1);
                    if (urls.contains(uri)){
                        return true;
                    }else {
                        request.setAttribute("msg","非法访问，你越权了！！");
                        request.getRequestDispatcher("index.jsp").forward(request,response);
                        return false;
                    }
                }
                return false;
            }else {
                //没有登录不能访问
                request.setAttribute("msg","请先登录！！");
                request.getRequestDispatcher("index.jsp").forward(request,response);
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
