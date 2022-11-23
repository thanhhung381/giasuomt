package giasuomt.demo.location.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.PersistentObjectException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.location.dto.FindingVietnamAreaDto;
import giasuomt.demo.location.dto.SaveAreaDto;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.repository.IAreaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
public class AreaService extends GenericService<SaveAreaDto, Area, String> implements IAreaService {
	private IAreaRepository iAreaRepository;
	private MapDtoToModel mapper;

	@Override
	public Area create(@Valid SaveAreaDto dto) {
		Area area = new Area();
		area = (Area) mapper.map(dto, area);
		area.setId(parseIdArea(dto));
		return save(dto, area);
	}

	@Override
	public Area update(SaveAreaDto dto) {
		Area area = iAreaRepository.getOne(dto.getId());
		area = (Area) mapper.map(dto, area);
		// area.setId(StringUltilsForAreaID.concatIdArea(dto.getNation(),dto.getState(),
		// dto.getCommune(), dto.getProvincialLevel(), dto.getDistrict()));
		return save(dto, area);
	}

	// check id exits
	@Override
	public boolean checkExistIdofArea(Long id) {
		return iAreaRepository.countById(id) >= 1;
	}

	// findByNationAndProvincialLevelAndDistrictAndCommune
	@Override
	public List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(FindingVietnamAreaDto dtoArea) {
		String provincialLevel = dtoArea.getProvincialLevel();
		String district = dtoArea.getDistrict();
		String commune = dtoArea.getCommune();
		String nation = "Việt Nam";
		if (provincialLevel != null && district != null && commune != null) {
			return iAreaRepository.findByNationAndProvincialLevelAndDistrictAndCommune(nation, provincialLevel,
					district, commune);
		} else if (provincialLevel != null && district != null) {
			return iAreaRepository.findByNationAndProvincialLevelAndStateAndDistrict(nation, provincialLevel, district);
		} else if (provincialLevel != null) {
			return iAreaRepository.findByNationAndProvincialLevelAndState(nation, provincialLevel);
		}
		return null;
	}

	@Override
	public boolean checkExistProvincialLevel(String provincialLevel) {
		return iAreaRepository.countByProvincialLevel(provincialLevel) >= 1;
	}

	@Override
	public boolean checkExistDistrict(String district) {
		return iAreaRepository.countByDistrict(district) >= 1;
	}

	@Override
	public boolean checkExistCommune(String commune) {
		return iAreaRepository.countByCommune(commune) >= 1;
	}

	@Override
	public Set<Area> createAll(Set<SaveAreaDto> dtos) {
		try {
			Set<Area> areas = new HashSet<>();
			dtos.parallelStream().forEach(dto -> {
				Area area = new Area();
				area = (Area) mapper.map(dto, area);
				area.setId(parseIdArea(dto));
				create(dto);
				areas.add(area);
			});
			return Sets.newHashSet(iAreaRepository.saveAll(areas));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String parseIdArea(SaveAreaDto dto) {
		String idArea = StringUltilsForAreaID.concatIdArea(dto.getNation(), dto.getState(), dto.getCommune(),
				dto.getProvincialLevel(), dto.getDistrict());
		return idArea.contains("đ") ? idArea.replaceAll("đ", "d") : idArea;
	}

}