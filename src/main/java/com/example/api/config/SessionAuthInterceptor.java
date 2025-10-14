package com.example.api.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        // deixa passar o preflight CORS, navegador nao estava nem conseguindo fazer get/post
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            return true;
        }
        HttpSession s = req.getSession(false);
        if (s != null && s.getAttribute("USER_ID") != null) return true;
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autenticado");
        return false;
    }
}
