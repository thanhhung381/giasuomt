package giasuomt.demo.person.service;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;

import giasuomt.demo.commondata.generator.TutorCodeGenerator;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.commondata.util.Calendar;
import giasuomt.demo.commondata.util.HienDangLa;
import giasuomt.demo.commondata.util.Voice;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.educational.repository.ISubjectGroupRepository;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.repository.IAreaRepository;
import giasuomt.demo.person.Ultils.UpdateSubjectGroupMaybeAndSure;
import giasuomt.demo.person.dto.TutorForWebDto;
import giasuomt.demo.person.dto.UpdateCalendarDto;
import giasuomt.demo.person.dto.UpdateNowLevelAndNowUpdateAtDto;
import giasuomt.demo.person.dto.SaveTutorDto;

import giasuomt.demo.person.model.Tutor;

import giasuomt.demo.person.repository.ITutorRepository;

import giasuomt.demo.staff.dto.UpdateAvatarStaff;
import giasuomt.demo.tags.model.TutorTag;
import giasuomt.demo.tags.repository.ITutorTagRepository;
import giasuomt.demo.task.dto.UpdateSubjectGroupForSureDto;
import giasuomt.demo.task.dto.UpdateSubjectGroupMaybeDto;

import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.repository.IUserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class TutorService extends GenericService<SaveTutorDto, Tutor, Long> implements ITutorService {

	private MapDtoToModel mapDtoToModel;

	private AwsClientS3 awsClientS3;

	// Repository

	private ITutorRepository iTutorRepository;

	private IAreaRepository iAreaRepository;

	private ITutorTagRepository iTutorTagRepository;

	private ISubjectGroupRepository iSubjectGroupRepository;

	private IUserRepository iUserRepository;

	@Override
	public List<Tutor> findAll() {

		return iTutorRepository.findAll();

	}

	@Override
	public Tutor create(SaveTutorDto dto) {
		Tutor tutor = new Tutor();

		tutor.setId(Long.parseLong(generateTutorCode()));
		;

		return save(dto, tutor);
	}

	@Override
	public Tutor update(SaveTutorDto dto) {

		Tutor tutor = iTutorRepository.getOne(dto.getId());

		// dto.setTutorCode(tutor.getId()); //Để đảm bảo là tutorCode ko được phép
		// update khi save

		String avatarURL = tutor.getAvatar();

		awsClientS3.getClient().deleteObject("avatargsomt", avatarURL.substring(avatarURL.lastIndexOf('/') + 1));

		return save(dto, tutor);
	}

	@Override
	public Tutor save(SaveTutorDto dto, Tutor tutor) {
		try {

			mapDto(tutor, dto);

			return iTutorRepository.save(tutor);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public void delete(Long id) {
		try {
			iTutorRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Tutor> createAll(List<SaveTutorDto> dtos) {
		try {

			List<Tutor> tutors = new LinkedList<>();

			for (SaveTutorDto dto : dtos) {
				Tutor tutor = new Tutor();
				mapDto(tutor, dto);

				tutors.add(tutor);
			}

			return iTutorRepository.saveAll(tutors);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Tutor findByTutorCode(Long tutorCode) {

		return iTutorRepository.findByIdOrTutorCode(tutorCode);
	}

	@Override
	public List<Tutor> findByPhoneNumber(String phoneNumber) {

		return iTutorRepository.findByPhonesContaining(phoneNumber);
	}

	@Override
	public List<Tutor> findByEndPhoneNumber(String endPhoneNumber) {

		return iTutorRepository.findByPhonesContaining(endPhoneNumber.concat("#"));
	}

	@Override
	public List<Tutor> findByFullNameContain(String fullName) {

		return iTutorRepository.findByFullNameContaining(fullName);
	}

	private void mapDto(Tutor tutor, SaveTutorDto dto) {
		tutor = (Tutor) mapDtoToModel.map(dto, tutor);

		tutor.setFullName(dto.getFullName().toUpperCase());

		tutor.setEnglishFullName(StringUltilsForAreaID.removeAccent(dto.getFullName()).toUpperCase());

		if (iAreaRepository.findById(dto.getTempAreaId()).isPresent())
			tutor.setTempArea(iAreaRepository.getOne(dto.getTempAreaId()));

		if (iAreaRepository.findById(dto.getPerAreaId()).isPresent())
			tutor.setPerArea(iAreaRepository.getOne(dto.getPerAreaId()));

		if (iAreaRepository.findById(dto.getRelAreaId()).isPresent())
			tutor.setRelArea(iAreaRepository.getOne(dto.getRelAreaId()));

		tutor.setExp(0.0);

		// save avatar
		// Relationship
//		List<SaveRelationshipDto> saveRelationshipDtoWiths = dto.getSaveRelationshipDtosWith();
//		for (int i = 0; i < tutor.getRelationshipWith().size(); i++) {
//			Boolean deleteThis = true;
//			for (int j = 0; j < saveRelationshipDtoWiths.size(); j++) {
//				if (tutor.getRelationshipWith().get(i).getId() == saveRelationshipDtoWiths.get(j).getId())
//					deleteThis = false;
//			}
//			if (deleteThis) {
//				tutor.removeRelationshipWith(tutor.getRelationshipWith().get(i)); // Delete
//				i--; // Vì nó đã remove 1 element trong array lên phải trừ đi
//			}
//		}
//
//		for (int i = 0; i < saveRelationshipDtoWiths.size(); i++) {
//			SaveRelationshipDto saveRelationshipDto = saveRelationshipDtoWiths.get(i);
//			if (saveRelationshipDto.getId() != null && saveRelationshipDto.getId() > 0) { // Update
//				Relationship relationship = iRelationshipRepository.getOne(saveRelationshipDto.getId());
//				relationship = (Relationship) mapDtoToModel.map(saveRelationshipDto, relationship);
//				relationship.setPersonB(iTutorRepository.getOne(saveRelationshipDto.getIdPersonBy()));
//				tutor.addRelationshipWith(relationship);
//			} else { // Create
//				Relationship relationship = new Relationship();
//				relationship = (Relationship) mapDtoToModel.map(saveRelationshipDto, relationship);
//				relationship.setPersonB(iTutorRepository.getOne(saveRelationshipDto.getIdPersonBy()));
//				tutor.addRelationshipWith(relationship);
//			}
//		}

		// Tags
		Set<String> tutorTagIds = dto.getTutorTagIds();
		Set<TutorTag> tutorTags = new HashSet<>();

		for (String id : tutorTagIds) {
			TutorTag tutorTag = iTutorTagRepository.getOne(id);
			tutorTags.add(tutorTag);
		}

		tutor.setTutorTags(tutorTags);

		// voice
		Set<Voice> voices = new HashSet<>();
		for (Voice voice : dto.getVoices()) {
			voices.add(voice);
		}

		tutor.setVoices(voices);
		// Hien dang la
		Set<HienDangLa> heiDangLas = new HashSet<>();
		for (HienDangLa hienDangLa : dto.getHienDangLas()) {
			heiDangLas.add(hienDangLa);
		}

		tutor.setHienDangLa(heiDangLas);
		// User

	}

	private String generateTutorCode() {
		String ResponseTutorCode = null;

		// lấy những người có tutorcode à ko null
		Tutor personHasTutorCode = iTutorRepository.getPersonTutorCodeNotNULL();

		if (personHasTutorCode != null) {

			System.out.println(personHasTutorCode.getId());
			if (personHasTutorCode != null) {

				String tutorCodeWithIdMaxorPreviousId = String.valueOf(personHasTutorCode.getId());// lấy mã đó ra từ
																									// Person
				// trước đó cuối

				int count = TutorCodeGenerator
						.generateResponsiveReserve(tutorCodeWithIdMaxorPreviousId.substring(6, 8));

				if (tutorCodeWithIdMaxorPreviousId == null
						|| TutorCodeGenerator.AutoGennerate(tutorCodeWithIdMaxorPreviousId) == -1
						|| TutorCodeGenerator.AutoGennerate(tutorCodeWithIdMaxorPreviousId) == 2) {
					count = 1;

				} else if (TutorCodeGenerator.AutoGennerate(tutorCodeWithIdMaxorPreviousId) == 3) {
					count += 1;

				}

				ResponseTutorCode = TutorCodeGenerator.generateResponsive((int) count);
			}

		} else {
			ResponseTutorCode = TutorCodeGenerator.generateResponsive((int) 1);
		}
		return TutorCodeGenerator.generatorCode().concat(ResponseTutorCode);
	}

	@Override
	public List<Tutor> findByEnglishFullName(String fullname) {

		return iTutorRepository.findByEnglishFullNameContaining(fullname);
	}

	@Override
	public List<String> findByEngfullnameAndShowFullName(String fullname) {

		return iTutorRepository.findByEnglishNameAndShowFullName(fullname);
	}

	@Override
	public List<String> findByfullnameAndShowFullName(String fullname) {

		return iTutorRepository.showFullname(fullname);
	}

	public Tutor updateRegisteredSubjects() {

		// Tutor tutor = iTutorRepository.getOne(dto.getId());

		// List<Long> subjectIds = dto.getRegisteredSubjectIds();
		// List<Subject> subjects = new ArrayList<>();
		// for (int i = 0; i < subjectIds.size(); i++) {
		// Subject subject = iSubjectRepository.getOne(subjectIds.get(i));
		// subjects.add(subject);

		// }

		// tutor.setRegisteredSubjects(subjects);
		// cập nhật xong
		// tutor =
		// UpdateSubjectGroupMaybeAndSure.generateSubjectGroupMaybeInTutor(tutor);

		return null;// iTutorRepository.save(tutor);

	}

	private void mapTutorForResponse(Tutor tutor, TutorForWebDto tutorForWebDto) {
		tutorForWebDto.setId(tutor.getId());
		tutorForWebDto.setFullName(tutor.getFullName());
		tutorForWebDto.setAvatar(tutor.getAvatar());
		tutorForWebDto.setGender(tutor.getGender());
		tutorForWebDto.setAverageStarNumbers(tutor.getAverageStarNumbers());
		tutorForWebDto.setSubjectGroupMaybes(tutor.getSubjectGroupMaybes());
		tutorForWebDto.setSubjectGroupSures(tutor.getSubjectGroupSures());
		tutorForWebDto.setRelArea(tutor.getRelArea());
		tutorForWebDto.setAdvantageNote(tutor.getAdvantageNote());
		tutorForWebDto.setVoices(tutor.getVoices());
		tutorForWebDto.setTutorTags(tutor.getTutorTags());
		tutorForWebDto.setTutorReviewNumbers(tutor.getTutorReviews().size());
		tutorForWebDto.setJobNumbers(tutor.getJobs().size());

	}

	private List<TutorForWebDto> mapTutorForResponseList(List<Tutor> tutors) {
		List<TutorForWebDto> tutorForWebDtos = new LinkedList<>();

		for (Tutor tutor : tutors) {
			TutorForWebDto tutorForWebDto = new TutorForWebDto();
			mapTutorForResponse(tutor, tutorForWebDto);
			tutorForWebDtos.add(tutorForWebDto);
		}
		return tutorForWebDtos;

	}

	@Override
	public List<TutorForWebDto> findAllTutorForWeb() {

		return mapTutorForResponseList(iTutorRepository.findByAverageStarNumbersGreaterThanEquals());
	}

	@Override
	public Tutor updateSubjetGroupMaybe(UpdateSubjectGroupMaybeDto dto) {
		try {

			Tutor tutor = iTutorRepository.getOne(dto.getId());

			Set<String> idSubjetGroupMaybes = dto.getIdSubjectGroupMaybes();

			Set<SubjectGroup> subjectGroupMaybes = new HashSet<>();

			for (String id : idSubjetGroupMaybes) {
				SubjectGroup subjectGroup = iSubjectGroupRepository.getOne(id);
				subjectGroupMaybes.add(subjectGroup);
			}

			tutor.setSubjectGroupMaybes(subjectGroupMaybes);

			return iTutorRepository.save(tutor);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Tutor updateSubjectGroupForSure(UpdateSubjectGroupForSureDto dto) {
		try {

			Tutor tutor = iTutorRepository.getOne(dto.getId());

			Set<String> idSubjetGroupMaybes = dto.getIdSubjectGroupForSures();

			Set<SubjectGroup> subjectGroupForSures = new HashSet<>();

			for (String id : idSubjetGroupMaybes) {
				SubjectGroup subjectGroup = iSubjectGroupRepository.getOne(id);
				subjectGroupForSures.add(subjectGroup);
			}

			tutor.setSubjectGroupSures(subjectGroupForSures);

			return iTutorRepository.save(tutor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Tutor updateNowLevelAndNowUpdateAt(UpdateNowLevelAndNowUpdateAtDto dto) {
		try {

			Tutor tutor = iTutorRepository.getOne(dto.getId());

			tutor.setNowLevel(dto.getNowLevel());

		    iTutorRepository.save(tutor);
		    
		    tutor.setNowLevelUpdatedAt(tutor.getUpdatedAt());
		    
		    return iTutorRepository.save(tutor);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Tutor updateCalendar(UpdateCalendarDto dto) {
		try {

			Tutor tutor = iTutorRepository.getOne(dto.getId());

			Set<Calendar> calendars = new HashSet<>();
			for (Calendar calendar : dto.getCalendars()) {
				calendars.add(calendar);
			}
			tutor.setCalendars(calendars);

			return iTutorRepository.save(tutor);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
