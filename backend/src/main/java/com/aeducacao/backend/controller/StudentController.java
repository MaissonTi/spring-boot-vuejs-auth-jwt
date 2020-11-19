package com.aeducacao.backend.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aeducacao.backend.model.Student;
import com.aeducacao.backend.repository.StudentRepository;
import com.aeducacao.backend.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private StudentService service;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_VIEW_STUDENT')")
	public Page<Student> all(@RequestParam(required = false, defaultValue = "") String name, Pageable pageable) {			
		 return service.all(name, pageable);
	}
		
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_VIEW_STUDENT')")
	public ResponseEntity<Student> show(@PathVariable Long id) {		
		Student result = service.show(id);		
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CREATE_STUDENT')")
	public ResponseEntity<Student> store(@Valid @RequestBody Student student, HttpServletResponse response) {
		Student result = service.save(student);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_UPDATE_STUDENT')")
	public ResponseEntity<Student> update(@PathVariable Long id, @Valid @RequestBody Student student) {							
		Student result = service.update(student, id);		
		
		return ResponseEntity.status(HttpStatus.OK).body(result);			
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_DELETE_STUDENT')")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	

}
