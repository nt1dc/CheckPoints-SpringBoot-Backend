package se.itmo.checkpointsbackend.filter;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import se.itmo.checkpointsbackend.dto.JwtResponse;
import se.itmo.checkpointsbackend.security.JwtCreationUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.FORBIDDEN;


@Slf4j

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtCreationUtils jwtCreationUtils;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, JwtCreationUtils jwtCreationUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtCreationUtils = jwtCreationUtils;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username is : {}", username);
        log.info("Password is : {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        String access_token = jwtCreationUtils.createJWAccessToken(request, authentication);
        String refresh_token = jwtCreationUtils.createJWTRefreshToken(request, authentication);
        Gson gson = new Gson();
        response.getWriter().print(gson.toJson(new JwtResponse(access_token, refresh_token)));
//        response.setHeader("access_token", access_token);
//        response.setHeader("refresh_token", refresh_token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error(failed.getMessage());
        response.setHeader("error", failed.getMessage());
//        response.sendError(401,failed.getMessage());
        response.setStatus(401,failed.getMessage());

    }
}
