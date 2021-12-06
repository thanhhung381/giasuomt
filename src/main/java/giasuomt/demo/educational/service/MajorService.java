package giasuomt.demo.educational.service;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.jpa.internal.ManagedFlushCheckerLegacyJpaImpl;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.educational.dto.SaveMajorDto;
import giasuomt.demo.educational.model.Major;
import giasuomt.demo.educational.repository.IMajorRepository;
import giasuomt.demo.educational.repository.IUniversityRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MajorService extends GenericService<SaveMajorDto, Major, Long> implements IMajorService {

	private IMajorRepository iMajorRepository;

	private IUniversityRepository iUniversityRepository;

	private MapDtoToModel mapDtoToModel;

	@Override
	public Major create(SaveMajorDto dto) {

		Major major = new Major();

		major = (Major) mapDtoToModel.map(dto, major);

		major.setUniversity(iUniversityRepository.getOne(dto.getIdUniversity()));

		major.setName(dto.getName().toUpperCase());
		major.setEnglishName(StringUltilsForAreaID.removeAccent(dto.getName()).toUpperCase());

		return save(dto, major);
	}

	@Override
	public Major update(SaveMajorDto dto) {
		Major major = iMajorRepository.getOne(dto.getId());

		major = (Major) mapDtoToModel.map(dto, major);

		major.setUniversity(iUniversityRepository.getOne(dto.getIdUniversity()));

		major.setName(dto.getName().toUpperCase());
		major.setEnglishName(StringUltilsForAreaID.removeAccent(dto.getName()).toUpperCase());

		return save(dto, major);
	}

	@Override
	public List<Major> createAll(List<SaveMajorDto> dtos) {
		try {

			List<Major> majors = new LinkedList<>();
			for (SaveMajorDto dto : dtos) {
				Major major = new Major();

				major = (Major) mapDtoToModel.map(dto, major);

				major.setUniversity(iUniversityRepository.getOne(dto.getIdUniversity()));

				majors.add(major);
			}

			return iMajorRepository.saveAll(majors);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Major> findByCode(String code) {

		return iMajorRepository.findByCode(code);
	}

	@Override
	public List<Major> findByNameContaining(String name) {

		return iMajorRepository.findByNameContaining(name);
	}

	@Override
	public List<Major> findByEnglishNameContaining(String englishName) {

		return iMajorRepository.findByEnglishNameContaining(englishName);
	}

	@Override
	public List<Major> findByUniversityIdAndCode(Long id, String code) {

		return iMajorRepository.findByUniversityIdAndCode(id, code);
	}

	@Override
	public List<Major> findByUniversityIdAndName(Long id, String name) {

		return iMajorRepository.findByUniversityIdAndName(id, name);
	}

	@Override
	public List<Major> findByUniversityIdAndEnglishName(Long id, String englishName) {
	
		return iMajorRepository.findByUniversityIdAndEnglishName(id, englishName);
	}

}
