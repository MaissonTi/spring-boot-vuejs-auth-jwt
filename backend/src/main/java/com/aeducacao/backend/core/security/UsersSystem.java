package com.aeducacao.backend.core.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.aeducacao.backend.model.Users;

public class UsersSystem extends User {
	
	private static final long serialVersionUID = 1L;

	private Users users;

	public UsersSystem(Users users, Collection<? extends GrantedAuthority> authorities) {
		super(users.getEmail(), users.getPassword(), authorities);
		this.users = users;
	}

	public Users getUsers() {
		return users;
	}

}
