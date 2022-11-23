package giasuomt.demo.person.service;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.person.dto.SaveTutorInvitationDto;
import giasuomt.demo.person.model.TutorInvitation;
import giasuomt.demo.person.repository.IRegisterAndLearnerRepository;
import giasuomt.demo.person.repository.ITutorInvitationRepository;
import giasuomt.demo.person.repository.ITutorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TutorInvitationService extends GenericService<SaveTutorInvitationDto, TutorInvitation, Long>{

	
	private ITutorInvitationRepository iTutorInvitationRepository;
	
	private IRegisterAndLearnerRepository iRegisterAndLearnerRepository;
	
	private ITutorRepository iTutorRepository;
	
	private MapDtoToModel mapDtoToModel;
	
	
	@Override
	public TutorInvitation create(SaveTutorInvitationDto dto) {
		try {
			TutorInvitation tutorInvitation=new TutorInvitation();
			tutorInvitation=(TutorInvitation) mapDtoToModel.map(dto, tutorInvitation);
			if(iRegisterAndLearnerRepository.findById(dto.getRegisterAndLearnerId()).isPresent())
				tutorInvitation.setRegisterAndLearner(iRegisterAndLearnerRepository.getOne(dto.getRegisterAndLearnerId()));		
			if(iTutorRepository.findById(dto.getTutorId()).isPresent())
				tutorInvitation.setTutor(iTutorRepository.getOne(dto.getTutorId()));		
			return iTutorInvitationRepository.save(tutorInvitation);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TutorInvitation update(SaveTutorInvitationDto dto) {
		try {	
			TutorInvitation tutorInvitation=iTutorInvitationRepository.getOne(dto.getId());
			tutorInvitation=(TutorInvitation) mapDtoToModel.map(dto, tutorInvitation);
			if(iRegisterAndLearnerRepository.findById(dto.getRegisterAndLearnerId()).isPresent())
				tutorInvitation.setRegisterAndLearner(iRegisterAndLearnerRepository.getOne(dto.getRegisterAndLearnerId()));
			if(iTutorRepository.findById(dto.getTutorId()).isPresent())
				tutorInvitation.setTutor(iTutorRepository.getOne(dto.getTutorId()));					
			return iTutorInvitationRepository.save(tutorInvitation);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
