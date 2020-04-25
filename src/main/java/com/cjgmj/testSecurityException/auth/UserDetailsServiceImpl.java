package com.cjgmj.testSecurityException.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cjgmj.testSecurityException.entity.RoleEntity;
import com.cjgmj.testSecurityException.entity.UserEntity;
import com.cjgmj.testSecurityException.service.IUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userService.findByUsername(username);

		if (user != null && !"".equals(user.getUsername())) {
			String roles = "";

			for (RoleEntity role : user.getRoles()) {
				if (roles.length() > 0) {
					roles += ",";
				}

				roles += "ROLE_".concat(role.getAuthority());
			}

			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);

			return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		}

		throw new UsernameNotFoundException("Username: " + username + " not found");
	}

}
