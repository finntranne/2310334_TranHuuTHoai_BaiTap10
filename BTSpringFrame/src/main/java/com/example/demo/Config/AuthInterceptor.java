package com.example.demo.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        HttpSession session = request.getSession();
        String currentRole = (String) session.getAttribute("userRole"); 
        String requestURI = request.getRequestURI();

        if (currentRole == null) {
            if (requestURI.contains("/login") || requestURI.contains("/register") || 
                requestURI.contains("/static") || requestURI.contains("/logout")) {
                return true; 
            }
            
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }


        if (requestURI.startsWith("/admin")) { 
            if ("ADMIN".equals(currentRole)) {
                return true;
            } else {

                response.sendRedirect(request.getContextPath() + "/login"); 
                return false;
            }
        } 
        

        if (requestURI.startsWith("/user")) {
            return true; 
        }
        
        return true; 
    }
}