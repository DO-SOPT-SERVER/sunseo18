package com.example.SecondSeminarKotlin.auth

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

// 지금 파일 안에 클래스 하나 더 만들면 이거 접근 가능해서 클래스 안에서 선언하는게 나을듯..!!
private const val MEMBER_ID = "memberId"

@Component
class JwtProvider(
    @Value("\${jwt.secret}")
    private val JWT_SECRET: String
) {

    fun generateToken(authentication: Authentication, tokenExpirationTime: Long): String {
        val now = Date()

        val claims = Jwts.claims()
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenExpirationTime))

        claims.put(MEMBER_ID, authentication.principal)

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setClaims(claims)
            .signWith(getSigningKey())
            .compact()
    }

    private fun getSigningKey(): SecretKey {
        val encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray())
        return Keys.hmacShaKeyFor(encodedKey.toByteArray())
    }

    private fun getBody(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .body


    fun validateToken(token: String): JwtValidationType {
        return try {
            val claims = getBody(token)
            JwtValidationType.VALID_JWT;
        } catch (error: Exception) {
            when (error) {
                is MalformedJwtException -> JwtValidationType.INVALID_JWT_TOKEN
                is ExpiredJwtException -> JwtValidationType.EXPIRED_JWT_TOKEN
                is UnsupportedJwtException -> JwtValidationType.UNSUPPORTED_JWT_TOKEN
                is IllegalArgumentException -> JwtValidationType.EMPTY_JWT
                else -> JwtValidationType.INVALID_JWT_TOKEN
            }
        }
    }

    fun getUserFromJwt(token: String): Long {
        val claims = getBody(token);
        return claims[MEMBER_ID].toString().toLong()
    }
}