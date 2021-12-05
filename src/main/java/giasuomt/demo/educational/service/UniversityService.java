package giasuomt.demo.educational.service;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.educational.dto.SaveUniversityDto;
import giasuomt.demo.educational.model.University;
import giasuomt.demo.educational.repository.IUniversityRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UniversityService extends GenericService<SaveUniversityDto, University, Long>
		implements IUniversityService {
	

	private IUniversityRepository iUniversityRepository;

	private MapDtoToModel mapDtoToModel;

	public University create(SaveUniversityDto dto) {

		University university = new University();
		
		university = (University) mapDtoToModel.map(dto, university);
		
		university.setName(dto.getName().toUpperCase());	
		university.setEnglishName(StringUltilsForAreaID.removeAccent(dto.getName()).toUpperCase());

		return save(dto, university);
	}

	@Override
	public University update(SaveUniversityDto dto) {

		University university = iUniversityRepository.getOne(dto.getId());
		
		university = (University) mapDtoToModel.map(dto, university);
		
		university.setName(dto.getName().toUpperCase());
		university.setEnglishName(StringUltilsForAreaID.removeAccent(dto.getName()).toUpperCase());

		return save(dto, university);
	}

	@Override
	public List<University> createAll(List<SaveUniversityDto> dtos) {
		try {
			List<University> universities=new LinkedList<>();
			for (SaveUniversityDto dto : dtos) {
				University university = new University();
				university = (University) mapDtoToModel.map(dto, university);
				universities.add(university);
			}
			return iUniversityRepository.saveAll(universities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
