package com.aeducacao.backend.core.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aeducacao.backend.model.Users;
import com.aeducacao.backend.repository.UsersRepository;


@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Users> usuarioOptional = usersRepository.findByEmail(email);
		Users users = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Incorrect username or password"));
		return new  UsersSystem(users, getPermissions(users));
	}

	private Collection<? extends GrantedAuthority> getPermissions(Users users) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		users.getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		return authorities;
	}

}