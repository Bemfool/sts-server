package bgroup.stocktradingsystem.stsserver.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ClientLoginInterceptor  extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        if(httpServletRequest.getRequestURI().substring(0,7).equals("/admin/")) {
            return false;
        }
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("CLIENT_SESSION_ID") != null) {
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
