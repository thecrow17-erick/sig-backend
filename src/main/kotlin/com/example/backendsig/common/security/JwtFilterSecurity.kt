package com.example.backendsig.common.security

import com.example.backendsig.common.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilterSecurity(
    private val jwtService: JwtService,
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = this.extracToken(request);
        if(token == null) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Token invalido");
            filterChain.doFilter(request,response);
            return;
        }
        val decode = this.jwtService.decoderToken(token);
        if(decode.isExpired){
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Token expirado");
            filterChain.doFilter(request,response);
            return;
        }
        request.setAttribute("userId",decode.userId);
        filterChain.doFilter(request,response);
    }

    private fun extracToken(req: HttpServletRequest): String? {
        val token = req.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(token) && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }
}