package giasuomt.demo.educational.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.educational.dto.SaveMajorDto;
import giasuomt.demo.educational.model.Major;

public interface IMajorService extends IGenericService<SaveMajorDto, Major, Long> {

	List<Major> findByCode(String code);

	List<Major> findByNameContaining(String name);

	List<Major> findByEnglishNameContaining(String englishName);

	List<Major> findByUniversityIdAndCode(Long id, String code);

	List<Major> findByUniversityIdAndName(Long id, String name);

	List<Major> findByUniversityIdAndEnglishName(Long id, String englishName);

}
