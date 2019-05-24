package bgroup.stocktradingsystem.stsserver.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("ADMIN_SESSION_ID") != null) {

            return true;
        } else {
            try {
                System.out.println(session.getId());
                httpServletResponse.sendRedirect("/error/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

}