package com.example.api.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        // rotas públicas ficam excluídas no WebConfig
        HttpSession s = req.getSession(false);
        if (s != null && s.getAttribute("USER_ID") != null) return true;
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autenticado");
        return false;
    }
}
