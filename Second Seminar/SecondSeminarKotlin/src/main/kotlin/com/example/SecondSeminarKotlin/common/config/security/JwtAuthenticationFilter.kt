package com.example.SecondSeminarKotlin.common.config.security

import com.example.SecondSeminarKotlin.auth.JwtProvider
import com.example.SecondSeminarKotlin.auth.JwtValidationType
import com.example.SecondSeminarKotlin.auth.UserAuthentication
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getJwtFromRequest(request)


        if (jwtProvider.validateToken(token) == JwtValidationType.VALID_JWT) {
            val memberId = jwtProvider.getUserFromJwt(token)
            val authentication = UserAuthentication(memberId.toString(), null, null)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String {
        val bearerToken = request.getHeader("Authorization")
        if (!bearerToken.isNullOrBlank() && bearerToken.startsWith("Bearer "))
            return bearerToken.substring("Bearer ".length)
        return ""
    }
}