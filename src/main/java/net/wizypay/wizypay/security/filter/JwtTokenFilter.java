package net.wizypay.wizypay.security.filter;

import net.wizypay.wizypay.security.JwtTokenProvider;
import net.wizypay.wizypay.security.exception.CustomException;
import net.wizypay.wizypay.security.exception.CustomExceptionModel;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException, CustomException {
        HttpServletResponse response = (HttpServletResponse) res;
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);

        boolean isTokenValid = jwtTokenProvider.validateToken(token);
        if (token != null && isTokenValid) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        if (token != null && !isTokenValid) {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new CustomExceptionModel("Invalid or expired token!", HttpStatus.FORBIDDEN)));
            return;
        }
        filterChain.doFilter(req, res);
    }
}
