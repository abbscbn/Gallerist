package com.abbascoban.gallerist.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();

        String message="Bu işlem için yetkiniz yok";

        Map<String, Object> body = Map.of(
                "status", 403,
                "error", "UNAUTHORIZED",
                "message", message,
                "path", request.getRequestURI()
        );

        mapper.writeValue(response.getOutputStream(), body);


//        response.setContentType("application/json;charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//
//        response.getWriter().write("""
//            {
//              "error": "FORBIDDEN",
//              "message": "Bu işlem için yetkiniz yok"
//            }
//        """);
    }
}
