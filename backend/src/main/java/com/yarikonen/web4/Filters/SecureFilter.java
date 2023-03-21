package com.yarikonen.web4.Filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yarikonen.web4.Services.SecretService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecureFilter implements Filter {

    private final SecretService secretService;

    public SecureFilter(SecretService secretService) {
        this.secretService = secretService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if( ((HttpServletRequest) servletRequest).getMethod().equals("OPTIONS")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String jwtToken = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        if(jwtToken!=null) {
            try {
                {
                    secretService.validateAccessToken(jwtToken);
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            } catch (JWTVerificationException exp) {
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization error:" + exp.getMessage());
            }
        }
        else{
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization error");
        }
    }
}
