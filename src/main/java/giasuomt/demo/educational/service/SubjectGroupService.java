package giasuomt.demo.educational.service;

import java.util.List;
import java.util.zip.ZipEntry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.educational.dto.SaveSubjectGroupDto;
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.educational.repository.ISubjectGroupRepository;
import lombok.AllArgsConstructor;

@Service

public class SubjectGroupService extends GenericService<SaveSubjectGroupDto, SubjectGroup, Long>  implements ISubjectGroupService {

	public SubjectGroupService(JpaRepository<SubjectGroup, Long> repository, MapDtoToModel mapDtoToModel) {
		super(repository, mapDtoToModel, t);
	}

	@Override
	public SubjectGroup create(SaveSubjectGroupDto dto) {
		// TODO Auto-generated method stub
		return super.create(dto);
	}
	
	@Override
	public List<SubjectGroup> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
	@Override
	public SubjectGroup save(SaveSubjectGroupDto dto, SubjectGroup t) {
		// TODO Auto-generated method stub
		return super.save(dto, t);
	}
	@Override
	public SubjectGroup update(SaveSubjectGroupDto entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	protected SubjectGroup createContents(SubjectGroup t) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
