package giasuomt.demo.learnerAndRegister.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.learnerAndRegister.dto.SaveLearnerAndRegistersDTO_Staff;
import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;
import giasuomt.demo.learnerAndRegister.service.LearnerAndRegisterService;



@RestController //Tức là server sẽ trả JSON về client
@RequestMapping("/api/learnerAndRegister") //Ghi nhận yêu cầu gọi api của Client
public class LearnerAndRegisterController {
	@Autowired
	private LearnerAndRegisterService service;
	
	//API TRẢ VỀ LIST TẤT CẢ
	@GetMapping("")
	public ResponseEntity<Object> findAll(){
		List<LearnerAndRegister> learnerAndRegisters = service.findAll();
		if(learnerAndRegisters.isEmpty())
			return ResponseHandler.getResponse("There is no data.",HttpStatus.OK);		
		return ResponseHandler.getResponse(learnerAndRegisters, HttpStatus.OK);
	}
	
	//API TRẢ VỀ LIST TẤT CẢ ROLE CÓ CHỨA DESCRIPTION LÀ /description Và SẮP XẾP THEO THỨ TỰ TĂNG DẦN
	@GetMapping("/email")
	public ResponseEntity<Object> findByEmail(@RequestParam("email") String email){
		List<LearnerAndRegister> learnerAndRegisters = service.findByEmail(email);
		if(learnerAndRegisters == null)
			return ResponseHandler.getResponse("There is no data.",HttpStatus.OK);
		return  ResponseHandler.getResponse(learnerAndRegisters, HttpStatus.OK); //Trả về roles, kèm theo HttpStatus OK
	}
	
	@GetMapping("/fullName")
	public ResponseEntity<Object> findByFullName(@RequestParam("fullName") String fullName){
		List<LearnerAndRegister> learnerAndRegisters = service.findByFullName(fullName);
		if(learnerAndRegisters == null)
			return ResponseHandler.getResponse("There is no data.",HttpStatus.OK);
		LearnerAndRegister newLearner = new LearnerAndRegister();
		newLearner.setFullName(String.valueOf(learnerAndRegisters.size()));
		learnerAndRegisters.add(newLearner);
		return  ResponseHandler.getResponse(learnerAndRegisters, HttpStatus.OK); //Trả về roles, kèm theo HttpStatus OK
	}
	
	@GetMapping("/fullNameAnd")
	public ResponseEntity<Object> findByFullNameAnd(@RequestParam("fullNameAnd") String fullNameAnd){
		List<LearnerAndRegister> learnerAndRegisters = service.findByFullNameAnd(fullNameAnd);
		if(learnerAndRegisters == null)
			return ResponseHandler.getResponse("There is no data.",HttpStatus.OK);
		LearnerAndRegister newLearner = new LearnerAndRegister();
		newLearner.setFullName(String.valueOf(learnerAndRegisters.size()));
		learnerAndRegisters.add(newLearner);
		return  ResponseHandler.getResponse(learnerAndRegisters, HttpStatus.OK); //Trả về roles, kèm theo HttpStatus OK
	}
	
	
	//API GHI DỮ LIỆU LearnerAndRegister ko ghi username/password (có dùng Validation để duyệt) VÀO DATABASE TABLE
	@PostMapping("/saveLearnerAndRegisters_Staff") //Khi save dữ liệu thì dùng thằng Post
	public ResponseEntity<Object> saveLearnerAndRegisters(@Valid @RequestBody SaveLearnerAndRegistersDTO_Staff dto, //@RequestBody là parse JSON của client về dạng object của server //@Valid là để validation các validation ở trong model Role
			                           BindingResult bindingResult){ //Khi kiểm tra Validation @Valid, có những messages lỗi gì, nó sẽ đẩy về biến BindingResult bindingResult
		if(bindingResult.hasErrors())
			return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST); //Trả về các messages lỗi, kèm theo HttpStatus BAD REQUEST [xem trong ResponsHandler.java]
		
		Set<LearnerAndRegister> addedLearnerAndRegisters = service.saveLearnerAndRegisters(dto);
		
		return ResponseHandler.getResponse(addedLearnerAndRegisters, HttpStatus.CREATED); //Trả về http status là đã thành công
	}  
	
	
}
