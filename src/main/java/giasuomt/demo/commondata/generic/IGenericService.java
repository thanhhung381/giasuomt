package giasuomt.demo.commondata.generic;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IGenericService<DTO, T , ID> {
	List<T> findAll();
	void deleteById(ID id);
	T findById(ID id); // Optional như là 1 cái wrap bên ngoài để cho phép null (vì khi findById có thể							// trả về null) - từ đó giúp tránh bị lỗi tùm lum
	T save(DTO object, T entity);
	T create(DTO dto);
	T update(DTO dto);
	Set<T> createAll(Set<DTO> dtos);
	boolean checkExistIdOfT(ID id);


}
