package com.cjgmj.testSecurityException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cjgmj.testSecurityException.filter.AuthenticationFilter;
import com.cjgmj.testSecurityException.filter.AuthorizationFilter;
import com.cjgmj.testSecurityException.utils.ExceptionUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/register").permitAll().anyRequest().authenticated().and()
				.addFilter(new AuthenticationFilter(authenticationManager()))
				.addFilterAfter(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class).csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.accessDeniedHandler((req, rsp, e) -> {
					rsp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					rsp.setContentType("application/json");

					rsp.getWriter().write(ExceptionUtils.getObjectMapper()
							.writeValueAsString(ExceptionUtils.getResponse(e.getMessage())));
				}).authenticationEntryPoint((req, rsp, e) -> {
					rsp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					rsp.setContentType("application/json");

					rsp.getWriter().write(ExceptionUtils.getObjectMapper()
							.writeValueAsString(ExceptionUtils.getResponse(e.getMessage())));
				});
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

}
