package giasuomt.demo.task.service;

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

}
