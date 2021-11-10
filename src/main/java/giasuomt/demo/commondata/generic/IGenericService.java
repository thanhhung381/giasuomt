package giasuomt.demo.commondata.generic;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import giasuomt.demo.commondata.model.AbstractEntity;

public interface IGenericService<DTO, T extends AbstractEntity, ID> {
	List<T> findAll();
	
	void deleteById(ID id);
	
	
	Optional<T> findById(ID id); //Optional như là 1 cái wrap bên ngoài để cho phép null (vì khi findById có thể trả về null) - từ đó giúp tránh bị lỗi tùm lum

	T save(DTO object, T entity);

	T create(DTO dto);

	T update(DTO dto);
	
	List<T> createAll(List<DTO> dtos);

	boolean checkExistIdOfT(ID id);



}
