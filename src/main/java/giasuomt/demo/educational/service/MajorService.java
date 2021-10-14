package giasuomt.demo.educational.service;

import org.hibernate.jpa.internal.ManagedFlushCheckerLegacyJpaImpl;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
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

		return save(dto, major);
	}

	@Override
	public Major update(SaveMajorDto dto) {
		Major major = iMajorRepository.getOne(dto.getId());

		major = (Major) mapDtoToModel.map(dto, major);

		major.setUniversity(iUniversityRepository.getOne(dto.getIdUniversity()));

		return save(dto, major);
	}

}
