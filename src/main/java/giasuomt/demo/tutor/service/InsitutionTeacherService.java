package giasuomt.demo.tutor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.CreateInstitutionTeacherDto;
import giasuomt.demo.tutor.model.InstitutionTeacher;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.repository.IInstitutionTeacherRepository;
import giasuomt.demo.tutor.repository.ITutorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InsitutionTeacherService extends GenericService<InstitutionTeacher, Long>
		implements IInsitutionTeacherService {

	private IInstitutionTeacherRepository iInsitutionTeacherRepository;

	private ITutorRepository iTutorRepository;

	private MapDtoToModel mapper;

	// save institution Teacher
	@Override
	public InstitutionTeacher save(CreateInstitutionTeacherDto dto) {

		try {
			InstitutionTeacher institutionTeacher = new InstitutionTeacher();
			institutionTeacher = (InstitutionTeacher) mapper.map(dto, institutionTeacher);

			Optional<Tutor> tutor = Optional.ofNullable(iTutorRepository.getOne(dto.getTutorId()));
			if (tutor.isPresent())
				institutionTeacher.setTutor(tutor.get());

			return iInsitutionTeacherRepository.save(institutionTeacher);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

}
