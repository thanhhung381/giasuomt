package giasuomt.demo.tutorrequest.service;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutorrequest.dto.SaveTutorRequestDto;
import giasuomt.demo.tutorrequest.model.TutorRequest;
import giasuomt.demo.tutorrequest.repository.ITutorRequestRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TutorRequestService extends GenericService<SaveTutorRequestDto, TutorRequest, Long>
		implements ITutorRequestService {

	private ITutorRequestRepository iTutorRequestRepository;
	private MapDtoToModel mapDtoToModel;

	public TutorRequest create(SaveTutorRequestDto dto) {
		TutorRequest request = new TutorRequest();
		return save(dto, request);
	}

	public TutorRequest save(SaveTutorRequestDto dto, TutorRequest request) {
		try {
			request = (TutorRequest) mapDtoToModel.map(dto, request);
			request.setChosenTutorId(dto.getChosenTutorId());
			return iTutorRequestRepository.save(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TutorRequest update(SaveTutorRequestDto dto) {
		TutorRequest request = iTutorRequestRepository.getOne(dto.getId());
		return save(dto, request);
	}

}
