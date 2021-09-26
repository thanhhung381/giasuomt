package giasuomt.demo.learnerAndRegister.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.util.UserGender;
import giasuomt.demo.learnerAndRegister.dto.SaveLearnerAndRegisterDTO_Staff;
import giasuomt.demo.learnerAndRegister.dto.SaveLearnerAndRegistersDTO_Staff;
import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;
import giasuomt.demo.learnerAndRegister.repository.ILearnerAndRegisterRepository;


@Service //Đánh dấu nó là 1 cái Service để nó tạo Bean, để truyền Dependency Injection vô (xem @Autowired ở dưới)
public class LearnerAndRegisterService extends GenericService<LearnerAndRegister, Long> implements ILearnerAndRegisterService {
	@Autowired //Để Spring Boot inject instance vô xài chứ ko cần đi khởi tạo khai báo các kiểu (xem lại kiến thức DI và IoC)
	private ILearnerAndRegisterRepository repository;
	
	@Autowired
	private MapDtoToModel mapDtoToModel;
	
	@Override
	public Set<LearnerAndRegister> saveLearnerAndRegisters(SaveLearnerAndRegistersDTO_Staff dto){
		Set<LearnerAndRegister> addedLearnerAndRegisters = new HashSet<>();
		//Save Register trước
		SaveLearnerAndRegisterDTO_Staff registerDto = dto.getRegister();
		LearnerAndRegister addedRegister = saveLearnerAndRegister(registerDto, new LearnerAndRegister());
		addedLearnerAndRegisters.add(addedRegister);
		//Sau đó lần lượt save các Learners
		Set<SaveLearnerAndRegisterDTO_Staff> learnersDto = dto.getLearners();
		for(SaveLearnerAndRegisterDTO_Staff learnerDto: learnersDto) {
			LearnerAndRegister addedLearner = saveLearnerAndRegister(learnerDto, addedRegister);
			addedLearnerAndRegisters.add(addedLearner);
		}
		return addedLearnerAndRegisters;
	}
	
	//API GHI DỮ LIỆU ROLE NGƯỜI DÙNG NHẬP VÀO (có dùng Validation để duyệt) VÀO DATABASE TABLE role
	@Override
	public LearnerAndRegister saveLearnerAndRegister(SaveLearnerAndRegisterDTO_Staff learnerAndRegisterDto, LearnerAndRegister addedLearnerAndRegister) {
//		for(int i = 0; i < 8000; i++) {
//		LearnerAndRegister learnerAndRegister = new LearnerAndRegister();
//		learnerAndRegister.fullName(learnerAndRegisterDto.getFullName())
//						  .gender(learnerAndRegisterDto.getGender())
//						  .phones(learnerAndRegisterDto.getPhones())
//						  .zaloes(learnerAndRegisterDto.getZaloes())
//		                  .emails(learnerAndRegisterDto.getEmails())
//						  .fbs(learnerAndRegisterDto.getFbs())
//		                  .birthDate(learnerAndRegisterDto.getBirthDate())
//		                  .birthYear(learnerAndRegisterDto.getBirthYear());
//		learnerAndRegister.addNo(learnerAndRegisterDto.getAddNo())
//						  .addSt(learnerAndRegisterDto.getAddSt())
//						  .addNote(learnerAndRegisterDto.getAddNote())
//						  .addAreaById(learnerAndRegisterDto.getAreaId())
//						  .addLearnerAndRegisterRelationship(addedLearnerAndRegister, learnerAndRegisterDto.getRelationship());
//		String them = "gfgfgfgyutyutyuytutyuggggiôii";
//		if(i%10==0) {
//			learnerAndRegister.fullName(them+them+them+String.valueOf(i+3)+"Sky Castle"+"- Vật lý"+String.valueOf(i));
//		} else if(i%5==0) {
//			learnerAndRegister.fullName(them+them+them+String.valueOf(i+2)+"Mouse"+them+"- Sinh học"+String.valueOf(i));
//		} else if(i%2==0) {
//			learnerAndRegister.fullName(them+them+them+String.valueOf(i+6)+them+"Ring"+"- Cấp 1"+String.valueOf(i));
//		} else {
//			learnerAndRegister.fullName(them+them+them+String.valueOf(i+1)+"Parasite"+"- Cấp 2"+ String.valueOf(i));
//		}
//		repository.save(learnerAndRegister); //Hàm save nếu chưa có thì nó tạo mới, nếu có rồi thì nó cập nhật
//		}
		LearnerAndRegister learnerAndRegister = new LearnerAndRegister();
		learnerAndRegister = (LearnerAndRegister) mapDtoToModel.map(learnerAndRegisterDto, learnerAndRegister);
//		learnerAndRegister.fullName(learnerAndRegisterDto.getFullName())
//						  .gender(learnerAndRegisterDto.getGender())
//						  .phones(learnerAndRegisterDto.getPhones())
//						  .zaloes(learnerAndRegisterDto.getZaloes())
//		                  .emails(learnerAndRegisterDto.getEmails())
//						  .fbs(learnerAndRegisterDto.getFbs())
//		                  .birthDate(learnerAndRegisterDto.getBirthDate())
//		                  .birthYear(learnerAndRegisterDto.getBirthYear());
//		learnerAndRegister.addNo(learnerAndRegisterDto.getAddNo())
//						  .addSt(learnerAndRegisterDto.getAddSt())
//						  .addNote(learnerAndRegisterDto.getAddNote())
//		learnerAndRegister.addAreaById(learnerAndRegisterDto.getAreaId());
		learnerAndRegister.addLearnerAndRegisterRelationship(addedLearnerAndRegister, learnerAndRegisterDto.getRelationship());
		
		repository.save(learnerAndRegister); //Hàm save nếu chưa có thì nó tạo mới, nếu có rồi thì nó cập nhật
		return learnerAndRegister;
	}

	
	//API TRẢ VỀ LIST TẤT CẢ ROLE CÓ CHỨA email LÀ /description Và SẮP XẾP THEO THỨ TỰ TĂNG DẦN
	@Override
	public List<LearnerAndRegister> findByEmail(String email) {
		return repository.findByEmails(email);
	}
	
	@Override
	public List<LearnerAndRegister> findByFullName(String fullName) {
		return repository.findByFullNameContaining(fullName);
	}

	@Override
	public List<LearnerAndRegister> findByFullNameAnd(String fullNameAnd) {
		UserGender gender = UserGender.MALE;
		return repository.findByFullNameContainingAndBirthYearAndGenderAndAddNoAndAddSt(fullNameAnd,"string",gender,"string","string");
	}

	
}
