package giasuomt.demo.commondata.generic;
import java.util.List;
import java.util.Optional;
import giasuomt.demo.commondata.model.AbstractEntity;

public interface IGenericService<T extends AbstractEntity, ID> {
	List<T> findAll();
		
	T save(T entity);
	
	T update(T entity);
	
	void deleteById(ID id);
	
	
	Optional<T> findById(ID id); //Optional như là 1 cái wrap bên ngoài để cho phép null (vì khi findById có thể trả về null) - từ đó giúp tránh bị lỗi tùm lum

}
