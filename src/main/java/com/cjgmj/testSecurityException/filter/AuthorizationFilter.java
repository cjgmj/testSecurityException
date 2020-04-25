package com.cjgmj.testSecurityException.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cjgmj.testSecurityException.utils.ExceptionUtils;
import com.cjgmj.testSecurityException.utils.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader(JwtConfig.HEADER);

		if (header == null || !header.startsWith(JwtConfig.PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = header.replace(JwtConfig.PREFIX, "");

		try {
			Claims claims = null;

			try {
				claims = Jwts.parserBuilder().setSigningKey(JwtConfig.KEY).build().parseClaimsJws(token).getBody();
			} catch (ExpiredJwtException e) {
				throw e;
			} catch (JwtException ex) {
				throw ex;
			}

			String username = claims.getSubject();

			if (username != null) {
				@SuppressWarnings("unchecked")
				List<String> authorities = (List<String>) claims.get("authorities");

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

				SecurityContextHolder.getContext().setAuthentication(auth);
			}

			filterChain.doFilter(request, response);
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");

			response.getWriter().write(
					ExceptionUtils.getObjectMapper().writeValueAsString(ExceptionUtils.getResponse(e.getMessage())));
		}
	}

}
