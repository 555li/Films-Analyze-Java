package com.neusoft.xlm.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String email= (String) request.getSession().getAttribute("email");
        String account= (String) request.getSession().getAttribute("account");
        if((email!=null&&email.length()!=0)||(account!=null&&account.length()!=0)){
            return true;
        }
        else {
            response.sendRedirect("/login");
            return false;
        }
    }

//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        String email= (String) request.getSession().getAttribute("email");
//        String account= (String) request.getSession().getAttribute("account");
//        if((email==null||email.length()==0)||(account==null||account.length()==0)){
//            response.sendRedirect("/");
//        }
//    }
}
