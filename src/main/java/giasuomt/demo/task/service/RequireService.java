package giasuomt.demo.task.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.task.dto.SaveRequireDto;
import giasuomt.demo.task.model.Require;
import giasuomt.demo.task.repository.IRequireRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequireService extends GenericService<SaveRequireDto, Require, Long> implements IRequireService {

	private MapDtoToModel mapDtoToModel;

	private IRequireRepository iRequireRepository;

	public Require create(SaveRequireDto dto) {

		Require require = new Require();

		require = (Require) mapDtoToModel.map(dto, require);

		return save(dto, require);
	}

	@Override
	public Require update(SaveRequireDto dto) {
		Require require = iRequireRepository.getOne(dto.getId());

		require = (Require) mapDtoToModel.map(dto, require);

		return save(dto, require);
	}

	@Override
	public List<Require> createAll(List<SaveRequireDto> dtos) {
		try {
			List<Require> requires = new LinkedList<>();
			for (SaveRequireDto dto : dtos) {
				Require require = new Require();

				require = (Require) mapDtoToModel.map(dto, require);

				requires.add(require);
			}

			return iRequireRepository.saveAll(requires);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
