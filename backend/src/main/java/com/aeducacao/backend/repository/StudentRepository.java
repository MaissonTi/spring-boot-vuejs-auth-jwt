package com.aeducacao.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aeducacao.backend.model.Student;

public interface StudentRepository extends JpaRepository< Student, Long> {
	
	public Optional<Student> findByRa(String ra);
	
	public Optional<Student> findByCpf(String cpf);
	
	public Page<Student> findByNameContainingIgnoreCase(String nome, Pageable pageable);

}
