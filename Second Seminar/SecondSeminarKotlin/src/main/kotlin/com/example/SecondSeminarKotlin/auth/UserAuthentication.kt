package com.example.SecondSeminarKotlin.auth

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class UserAuthentication(
    principal: Any,
    credentials: Any?,
    authorities: Collection<GrantedAuthority>?
) : UsernamePasswordAuthenticationToken(principal, credentials, authorities)