package com.educibertec.sofiaproject.security;

import com.educibertec.sofiaproject.entity.CapsulaUsuario;
import com.educibertec.sofiaproject.exceptions.NoDataFoundException;
import com.educibertec.sofiaproject.repositories.IUsuariosRepository;
import com.educibertec.sofiaproject.util.JwtValidateUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtValidateUtil jwtValidateUtil;
    @Autowired
    private IUsuariosRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && jwtValidateUtil.validateToken(jwt)) {
                Long userId = Long.parseLong(jwtValidateUtil.getUsernameFromToken(jwt));
                CapsulaUsuario user = userRepository.findById(userId)
                        .orElseThrow(() -> new NoDataFoundException("No existe el usuario"));
                AuthUser authUser = AuthUser.instance(user);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authUser,
                        null, authUser.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && (bearerToken.startsWith("Bearer ") || bearerToken.startsWith("bearer "))) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
