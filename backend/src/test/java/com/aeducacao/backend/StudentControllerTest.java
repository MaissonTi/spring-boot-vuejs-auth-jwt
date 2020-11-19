package com.aeducacao.backend;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.aeducacao.backend.service.StudentService;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

	   @Autowired
	   private MockMvc mockMvc;
	   
	   @Autowired
	   StudentService studentService;
	   
	    @Test
	    public void callingWithoutParameterShouldReturnBadRequest() throws Exception {
	    	
	    }

}
