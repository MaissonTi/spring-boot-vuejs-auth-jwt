package com.aeducacao.backend.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import com.aeducacao.backend.model.Student;
import com.aeducacao.backend.repository.StudentRepository;
import com.aeducacao.backend.service.exception.StudentNotFoundExeption;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	public Page<Student> all(Pageable pageable) {		
		 return repository.findAll(pageable);
	}
	
	public Page<Student> all(String name, Pageable pageable) {
		if( !name.isEmpty() ) return repository.findByNameContainingIgnoreCase(name, pageable);
		
		 return this.all(pageable);
	}
	
	public Student show(Long id) {
		Optional<Student> result = repository.findById(id);

		if (!result.isPresent())
			throw new StudentNotFoundExeption();

		return result.get();
	}

	public Student save(Student student) {
		Student result = repository.save(student);
		return result;
	}

	public Student update(Student student, long id) {
		Optional<Student> studentFound = repository.findById(id);
		if (!studentFound.isPresent())
			throw new StudentNotFoundExeption();

		BeanUtils.copyProperties(student, studentFound.get(), "id");
		Student result = repository.save(studentFound.get());

		return result;

	}

}
