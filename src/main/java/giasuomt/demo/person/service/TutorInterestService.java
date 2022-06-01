package giasuomt.demo.person.service;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.person.dto.SaveTutorInterestDto;
import giasuomt.demo.person.model.TutorInterest;
import giasuomt.demo.person.repository.IRegisterAndLearnerRepository;
import giasuomt.demo.person.repository.ITutorInterestRepository;
import giasuomt.demo.person.repository.ITutorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TutorInterestService extends GenericService<SaveTutorInterestDto, TutorInterest, Long> implements ITutorInterestService{
	
	private ITutorInterestRepository iTutorInterestRepository;
	
	private IRegisterAndLearnerRepository iRegisterAndLearnerRepository;
	
	private ITutorRepository iTutorRepository;
	
	private MapDtoToModel mapDtoToModel;
	

	@Override
	public TutorInterest create(SaveTutorInterestDto dto) {
		
		try {
			TutorInterest tutorInterest=new TutorInterest();
			
			tutorInterest=(TutorInterest) mapDtoToModel.map(dto, tutorInterest);
			
			if(iRegisterAndLearnerRepository.findById(dto.getRegisterAndLearnerId()).isPresent())
				tutorInterest.setRegisterAndLearner(iRegisterAndLearnerRepository.getOne(dto.getRegisterAndLearnerId()));
			
			if(iTutorRepository.findById(dto.getTutorId()).isPresent())
				tutorInterest.setTutor(iTutorRepository.getOne(dto.getTutorId()));
			
			return iTutorInterestRepository.save(tutorInterest);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public TutorInterest update(SaveTutorInterestDto dto) {
		try {
			
			
			TutorInterest tutorInterest=iTutorInterestRepository.getOne(dto.getId());
			
			tutorInterest=(TutorInterest) mapDtoToModel.map(dto, tutorInterest);
			
			if(iRegisterAndLearnerRepository.findById(dto.getRegisterAndLearnerId()).isPresent())
				tutorInterest.setRegisterAndLearner(iRegisterAndLearnerRepository.getOne(dto.getRegisterAndLearnerId()));
			
			if(iTutorRepository.findById(dto.getTutorId()).isPresent())
				tutorInterest.setTutor(iTutorRepository.getOne(dto.getTutorId()));
			
			return iTutorInterestRepository.save(tutorInterest);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
