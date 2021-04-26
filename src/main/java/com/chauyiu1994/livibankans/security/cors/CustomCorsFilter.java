package com.chauyiu1994.livibankans.security.cors;

import com.chauyiu1994.livibankans.context.WebContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomCorsFilter extends OncePerRequestFilter {

    private WebContext webContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", webContext.getAllowOrigin());

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "X-Requested-With, Content-Type, Authorization, Origin, "
                        + "Accept, Access-Control-Request-Method, Access-Control-Request-Headers, "
                        + "id, type, msisdn, groupId, "
                        + "Forwarded, X-Forwarded-For, X-Forwarded-Host, X-Forwarded-Proto");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}