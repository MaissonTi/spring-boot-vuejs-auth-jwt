package com.aeducacao.backend.repository;

import com.aeducacao.backend.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

	public Optional<Users> findByEmail(String email);

}
