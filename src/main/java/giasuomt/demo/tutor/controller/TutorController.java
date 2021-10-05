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
import giasuomt.demo.location.dto.CreateAreaDTO;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.tutor.dto.CreateStudentDto;
import giasuomt.demo.tutor.dto.CreateTutorDto;
import giasuomt.demo.tutor.dto.TutorWithStudent;
import giasuomt.demo.tutor.dto.UpdateStudentDto;
import giasuomt.demo.tutor.dto.UpdateTutorDto;
import giasuomt.demo.tutor.model.Student;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.service.IStudentService;
import giasuomt.demo.tutor.service.ITutorService;

@RestController
@RequestMapping("/api/Tutor")
public class TutorController {
	
	@Autowired
	private ITutorService iTutorService;
	
	@Autowired
	private IStudentService iStudentService;
	
	//Show List of Tutor
	@GetMapping("/showTutor")
	public ResponseEntity<Object> findAllTutor() {
		List<Tutor> tutors = iTutorService.findAll();
		if (tutors.isEmpty())

			return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);
	}
	
	// Save Tutor
		@PostMapping("/saveTutor")
		public ResponseEntity<Object> save(@Valid @RequestBody CreateTutorDto dto, BindingResult errors) {
			if (errors.hasErrors())
				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST); // Trả về các messages lỗi, kèm theo
																					// HttpStatus BAD REQUEST [xem trong
																					// ResponsHandler.java]
			Tutor addedTutor =iTutorService.save(dto);
			
			//List<TutorWithStudent> lists=iTutorService.findalll();
			
			return ResponseHandler.getResponse(addedTutor, HttpStatus.CREATED); // Trả về http status là đã thành công
		}
		//Delete Tutor
		@DeleteMapping("/deleteTutor/{id}")
		public ResponseEntity<Object> deleteByIdofTutor(@PathVariable("id") Long id)
		{
			
			  iTutorService.deleteById(id);
			
			
			
			return ResponseHandler.getResponse("ok", HttpStatus.BAD_REQUEST);
		}
		//UpdateTutor
		@PutMapping("/updateTutor/{id}")
		public ResponseEntity<Object> updateTutor(@Valid @RequestBody UpdateTutorDto dto,Long id)
		{
			
			Tutor updateTutor=iTutorService.update(dto, id);
			
			return ResponseHandler.getResponse(updateTutor, HttpStatus.OK) ;
		}
		
		
		
		// Controller Student
		
		//Show List of Tutor
		@GetMapping("/Student/showStudent")
		public ResponseEntity<Object> findAllStudent() {
			List<Student> students = iStudentService.findAll();
			if (students.isEmpty())

				return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);

			return ResponseHandler.getResponse(students, HttpStatus.OK);
		}
		
		// Save Student
			@PostMapping("/Student/saveStudent")
			public ResponseEntity<Object> saveStudent(@Valid @RequestBody CreateStudentDto dto, BindingResult errors) {
				if (errors.hasErrors())
					return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST); // Trả về các messages lỗi, kèm theo
																						// HttpStatus BAD REQUEST [xem trong
																						// ResponsHandler.java]
				Student addedStudent =iStudentService.save(dto);
				return ResponseHandler.getResponse(addedStudent , HttpStatus.CREATED); // Trả về http status là đã thành công
			}
		//delete student	
			@DeleteMapping("/Student/deleteStudent/{id}")
			public ResponseEntity<Object> deleteByIdInStudent(@PathVariable("id") Long id)
			{
			    	iStudentService.deleteById(id);
				
				return ResponseHandler.getResponse("delete successfully", HttpStatus.OK);
			}
		//Update student
			@PutMapping("/Student/updateStudent/{id}")
			public ResponseEntity<Object> updateByIdInStudent(@PathVariable("id") Long id,@RequestBody UpdateStudentDto dto,BindingResult errors)
			{
				if(errors.hasErrors())
					return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
				Student studentUpdate=iStudentService.update(dto, id);
				return ResponseHandler.getResponse(studentUpdate,HttpStatus.OK);
			}
		
			

			
			//
			@GetMapping("/showTutorWithDifferInfo")
			public ResponseEntity<Object> ShowTutorWithBasicInfo() {
				List<TutorWithStudent> lists=iTutorService.findalll();
				if (lists.isEmpty())

					return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);

				return ResponseHandler.getResponse(lists, HttpStatus.OK);
			}
			
	
}
