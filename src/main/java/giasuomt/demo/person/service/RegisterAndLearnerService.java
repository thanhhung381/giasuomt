package giasuomt.demo.person.service;

import java.util.LinkedList;


import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.location.model.RegisterAndLearnerAddress;
import giasuomt.demo.location.model.SaveRegisterAndLearnerAddressDto;
import giasuomt.demo.location.model.SaveTaskPlaceAddressDto;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.location.repository.IAreaRepository;
import giasuomt.demo.location.repository.IRegisterAndLearnerAddressRepository;
import giasuomt.demo.location.repository.ITaskPlaceAddressRepository;
import giasuomt.demo.person.dto.SaveSchoolerDto;
import giasuomt.demo.person.dto.SaveRegisterAndLearnerDto;
import giasuomt.demo.person.dto.SaveRegisterAndLearnerRelationshipDto;
import giasuomt.demo.person.dto.UpdateAvatarRegisterAndLearner;

import giasuomt.demo.person.model.Schooler;

import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.model.RegisterAndLearnerRelationship;


import giasuomt.demo.person.repository.IRegisterAndLearnerRelationshipRepository;

import giasuomt.demo.person.repository.ISchoolerRepository;

import giasuomt.demo.person.repository.IRegisterAndLearnerRepository;

import giasuomt.demo.staff.model.Staff;
import giasuomt.demo.tags.model.RegisterAndLearnerTag;
import giasuomt.demo.tags.repository.IRegisterAndLearnerTagRepository;

import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.repository.IUserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RegisterAndLearnerService extends GenericService<SaveRegisterAndLearnerDto, RegisterAndLearner, Long>
		implements IRegisterAndLearnerService {

	private MapDtoToModel mapDtoToModel;

	private AwsClientS3 awsClientS3;

	// Repository

	private IRegisterAndLearnerRepository iRegisterAndLearnerRepository;

	private IAreaRepository iAreaRepository;

	private IRegisterAndLearnerAddressRepository iRegisterAndLearnerAddressRepository;

	

	private ISchoolerRepository iSchoolerRepository;

	

	private IRegisterAndLearnerTagRepository iRegisterAndLearnerTagRepository;

	private IRegisterAndLearnerRelationshipRepository iRegisterAndLearnerRelationshipRepository;



	private IUserRepository iUserRepository;

	@Override
	public List<RegisterAndLearner> findAll() {

		return iRegisterAndLearnerRepository.findAll();

	}

	@Override
	public RegisterAndLearner create(SaveRegisterAndLearnerDto dto) {
		RegisterAndLearner registerAndLearner = new RegisterAndLearner();

		return save(dto, registerAndLearner);
	}

	@Override
	public RegisterAndLearner update(SaveRegisterAndLearnerDto dto) {

		RegisterAndLearner registerAndLearner = iRegisterAndLearnerRepository.getOne(dto.getId());

		String avatarURL = registerAndLearner.getAvatar();


		awsClientS3.getClient().deleteObject("avatargsomt", avatarURL.substring(avatarURL.lastIndexOf('/') + 1));
		


		return save(dto, registerAndLearner);
	}

	@Override
	public RegisterAndLearner save(SaveRegisterAndLearnerDto dto, RegisterAndLearner registerAndLearner) {
		try {

			mapDto(registerAndLearner, dto);

			return iRegisterAndLearnerRepository.save(registerAndLearner);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public void delete(Long id) {
		try {
			iRegisterAndLearnerRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RegisterAndLearner> createAll(List<SaveRegisterAndLearnerDto> dtos) {
		try {
			List<RegisterAndLearner> registerAndLearners = new LinkedList<>();

			for (SaveRegisterAndLearnerDto dto : dtos) {
				RegisterAndLearner registerAndLearner = new RegisterAndLearner();
				mapDto(registerAndLearner, dto);

				registerAndLearners.add(registerAndLearner);
			}

			return iRegisterAndLearnerRepository.saveAll(registerAndLearners);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RegisterAndLearner> findByPhoneNumber(String phoneNumber) {
		return iRegisterAndLearnerRepository.findByPhonesContaining(phoneNumber);
	}

	@Override
	public List<RegisterAndLearner> findByEndPhoneNumber(String endPhoneNumber) {
		return iRegisterAndLearnerRepository.findByPhonesContaining(endPhoneNumber.concat("#"));
	}
//
//	@Override
//	public List<RegisterAndLearner> findByFullNameContain(String fullName) {
//		
//		return iRegisterAndLearnerRepository.findByFullNameContaining(fullName);
//	}

	private void mapDto(RegisterAndLearner registerAndLearner, SaveRegisterAndLearnerDto dto) {
		registerAndLearner = (RegisterAndLearner) mapDtoToModel.map(dto, registerAndLearner);

		registerAndLearner.setFullName(dto.getFullName().toUpperCase());

		registerAndLearner.setEnglishFullName(StringUltilsForAreaID.removeAccent(dto.getFullName().toUpperCase()));

		List<SaveRegisterAndLearnerAddressDto> saveRegisterAndLearnerAddressDtos = dto.getRegisterAndLearnerAddresses();
		for (int i = 0; i < registerAndLearner.getRegisterAndLearnerAddresses().size(); i++) {
			boolean deleteThis = true;
			for (int j = 0; j < saveRegisterAndLearnerAddressDtos.size(); j++) {
				if (registerAndLearner.getRegisterAndLearnerAddresses().get(i)
						.getId() == saveRegisterAndLearnerAddressDtos.get(j).getId()) {
					deleteThis = false;
				}
			}
			if (deleteThis) {
				registerAndLearner
						.removeRegisterAndLearnerAddress(registerAndLearner.getRegisterAndLearnerAddresses().get(i));
				i--;
			}
		}
		for (int i = 0; i < saveRegisterAndLearnerAddressDtos.size(); i++) {
			SaveRegisterAndLearnerAddressDto saveRegisterAndLearnerAddressDto = saveRegisterAndLearnerAddressDtos
					.get(i);
			if (saveRegisterAndLearnerAddressDto.getId() != null && saveRegisterAndLearnerAddressDto.getId() > 0) {
				RegisterAndLearnerAddress registerAndLearnerAddress = iRegisterAndLearnerAddressRepository
						.getOne(saveRegisterAndLearnerAddressDto.getId());
				registerAndLearnerAddress = (RegisterAndLearnerAddress) mapDtoToModel
						.map(saveRegisterAndLearnerAddressDto, registerAndLearnerAddress);
				registerAndLearnerAddress.setArea(iAreaRepository.getOne(saveRegisterAndLearnerAddressDto.getIdArea()));
				registerAndLearner.addRegisterAndLearnerAddress(registerAndLearnerAddress);
			} else {
				RegisterAndLearnerAddress registerAndLearnerAddress = new RegisterAndLearnerAddress();
				registerAndLearnerAddress = (RegisterAndLearnerAddress) mapDtoToModel
						.map(saveRegisterAndLearnerAddressDto, registerAndLearnerAddress);
				registerAndLearnerAddress.setArea(iAreaRepository.getOne(saveRegisterAndLearnerAddressDto.getIdArea()));
				registerAndLearner.addRegisterAndLearnerAddress(registerAndLearnerAddress);
			}
		}

		// save avatar

		

		// Relationship
		List<SaveRegisterAndLearnerRelationshipDto> saveRegisterAndLearnerRelationshipDtoWiths = dto
				.getRegisterAndLearnerRelationships();
		for (int i = 0; i < registerAndLearner.getRelationshipWith().size(); i++) {
			Boolean deleteThis = true;
			for (int j = 0; j < saveRegisterAndLearnerRelationshipDtoWiths.size(); j++) {
				if (registerAndLearner.getRelationshipWith().get(i)
						.getId() == saveRegisterAndLearnerRelationshipDtoWiths.get(j).getId())
					deleteThis = false;
			}
			if (deleteThis) {
				registerAndLearner.removeRelationshipWith(registerAndLearner.getRelationshipWith().get(i)); // Delete
				i--; // Vì nó đã remove 1 element trong array lên phải trừ đi
			}
		}

		for (int i = 0; i < saveRegisterAndLearnerRelationshipDtoWiths.size(); i++) {
			SaveRegisterAndLearnerRelationshipDto saveRegisterAndLearnerRelationshipDto = saveRegisterAndLearnerRelationshipDtoWiths
					.get(i);
			if (saveRegisterAndLearnerRelationshipDto.getId() != null
					&& saveRegisterAndLearnerRelationshipDto.getId() > 0) { // Update
				RegisterAndLearnerRelationship registerAndLearnerRelationship = iRegisterAndLearnerRelationshipRepository
						.getOne(saveRegisterAndLearnerRelationshipDto.getId());
				registerAndLearnerRelationship = (RegisterAndLearnerRelationship) mapDtoToModel
						.map(saveRegisterAndLearnerRelationshipDto, registerAndLearnerRelationship);
				registerAndLearnerRelationship.setPersonB(
						iRegisterAndLearnerRepository.getOne(saveRegisterAndLearnerRelationshipDto.getIdPersonBy()));
				registerAndLearner.addRelationshipWith(registerAndLearnerRelationship);
			} else { // Create
				RegisterAndLearnerRelationship registerAndLearnerRelationship = new RegisterAndLearnerRelationship();
				registerAndLearnerRelationship = (RegisterAndLearnerRelationship) mapDtoToModel
						.map(saveRegisterAndLearnerRelationshipDto, registerAndLearnerRelationship);
				registerAndLearnerRelationship.setPersonB(
						iRegisterAndLearnerRepository.getOne(saveRegisterAndLearnerRelationshipDto.getIdPersonBy()));
				registerAndLearner.addRelationshipWith(registerAndLearnerRelationship);
			}
		}

		// Tags
		List<Long> registerAndLearnerTagIds = dto.getRegisterAndLearnerTagIds();
		List<RegisterAndLearnerTag> registerAndLearnerTags = new LinkedList<>();
		for (int i = 0; i < registerAndLearnerTagIds.size(); i++) {
			RegisterAndLearnerTag registerAndLearnerTag = iRegisterAndLearnerTagRepository
					.getOne(registerAndLearnerTagIds.get(i));
			registerAndLearnerTags.add(registerAndLearnerTag);
		}
		registerAndLearner.setRegisterAndLearnerTags(registerAndLearnerTags);

		// Hiện đang là
		List<SaveSchoolerDto> saveSchoolerDtos = dto.getSchoolers();
		for (int i = 0; i < registerAndLearner.getSchoolers().size(); i++) {
			Boolean deleteThis = true;
			for (int j = 0; j < saveSchoolerDtos.size(); j++) {
				if (registerAndLearner.getSchoolers().get(i).getId() == saveSchoolerDtos.get(j).getId())
					deleteThis = false;
			}
			if (deleteThis) {
				registerAndLearner.removeSchooler(registerAndLearner.getSchoolers().get(i)); // Delete
				i--; // Vì nó đã remove 1 element trong array lên phải trừ đi
			}
		}
		for (int i = 0; i < saveSchoolerDtos.size(); i++) {
			SaveSchoolerDto saveSchoolerDto = saveSchoolerDtos.get(i);
			if (saveSchoolerDto.getId() != null && saveSchoolerDto.getId() > 0) { // Update
				Schooler schooler = iSchoolerRepository.getOne(saveSchoolerDto.getId());
				schooler = (Schooler) mapDtoToModel.map(saveSchoolerDto, schooler);
				registerAndLearner.addSchooler(schooler);
			} else { // Create
				Schooler schooler = new Schooler();
				schooler = (Schooler) mapDtoToModel.map(saveSchoolerDto, schooler);
				registerAndLearner.addSchooler(schooler);
			}
		}



	
		// user

	}

	@Override
	public List<RegisterAndLearner> findByFullNameContaining(String fullName) {

		return iRegisterAndLearnerRepository.findByFullNameContaining(fullName);
	}

	@Override
	public List<RegisterAndLearner> findByEnglishFullNameContaining(String englishFullName) {

		return iRegisterAndLearnerRepository.findByEnglishFullNameContaining(englishFullName);
	}

	@Override
	public List<String> findByEnglishNameAndShowEngLishFullName(String englishFullName) {

		return iRegisterAndLearnerRepository.findByEnglishNameAndShowFullName(englishFullName);
	}

	@Override
	public List<String> findByFullNameAndShowFullName(String fullName) {

		return iRegisterAndLearnerRepository.findByFullNameAndShowFullName(fullName);
	}

	@Override
	public List<RegisterAndLearner> findByVocativeAndFullName(String vocative, String fullName) {

		return iRegisterAndLearnerRepository.findByVocativeAndFullNameContaining(vocative, fullName);
	}

	@Override
	public List<String> findByVocativeAndFullNameAndShowFullName(String vocative, String fullName) {

		return iRegisterAndLearnerRepository.findByVocativeAndFullNameAndShowFullName(vocative, fullName);
	}

	@Override
	public List<RegisterAndLearner> findByVocativeAndEnglishFullNameContaining(String vocative, String englishName) {

		return iRegisterAndLearnerRepository.findByVocativeAndEnglishFullNameContaining(vocative, englishName);
	}

	@Override
	public List<String> findByVocativeAndEnglishFullNameAndShowFullName(String vocative, String englishName) {

		return iRegisterAndLearnerRepository.findByVocativeAndEnglishNameAndShowFullName(vocative, englishName);
	}

	@Override
	public RegisterAndLearner updateAvatarRegisterAndLearner(UpdateAvatarRegisterAndLearner dto) {
		RegisterAndLearner registerAndLearner = iRegisterAndLearnerRepository.getOne(dto.getId());

		String avatarURL = registerAndLearner.getAvatar();

		awsClientS3.getClient().deleteObject("avatargsomt", avatarURL.substring(avatarURL.lastIndexOf('/') + 1));
		
		
		return null;
	}

}
