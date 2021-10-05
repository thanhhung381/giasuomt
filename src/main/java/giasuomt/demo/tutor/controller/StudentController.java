package giasuomt.demo.tutor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.location.dto.UpdateAreaDTO;
import giasuomt.demo.tutor.dto.CreateStudentDto;
import giasuomt.demo.tutor.dto.CreateTutorDto;
import giasuomt.demo.tutor.dto.UpdateStudentDto;
import giasuomt.demo.tutor.model.Student;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.service.IStudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private IStudentService iStudentService;
	
	//Show List of Tutor
	@GetMapping("")
	public ResponseEntity<Object> findAll() {
		List<Student> students = iStudentService.findAll();
		if (students.isEmpty())

			return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(students, HttpStatus.OK);
	}
	
	// Save Student
		@PostMapping("")
		public ResponseEntity<Object> save(@Valid @RequestBody CreateStudentDto dto, BindingResult errors) {
			if (errors.hasErrors())
				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST); // Trả về các messages lỗi, kèm theo
																					// HttpStatus BAD REQUEST [xem trong
																					// ResponsHandler.java]
			Student addedStudent =iStudentService.save(dto);
			return ResponseHandler.getResponse(addedStudent , HttpStatus.CREATED); // Trả về http status là đã thành công
		}
	//delete	
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Object> deleteById(@PathVariable("id") Long id)
		{
		    	iStudentService.deleteById(id);
			
			return ResponseHandler.getResponse("delete successfully", HttpStatus.OK);
		}
	//Update
		@PutMapping("/update/{id}")
		public ResponseEntity<Object> updateById(@PathVariable("id") Long id,@RequestBody UpdateStudentDto dto,BindingResult errors)
		{
			if(errors.hasErrors())
				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
			Student studentUpdate=iStudentService.update(dto, id);
			return ResponseHandler.getResponse(studentUpdate,HttpStatus.OK);
		}
		
	
}
