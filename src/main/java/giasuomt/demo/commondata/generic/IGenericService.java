package giasuomt.demo.commondata.generic;
import java.util.List;
import java.util.Optional;
import giasuomt.demo.commondata.model.AbstractEntity;

public interface IGenericService<DTO extends DtoId,T extends Object, ID> {
	public List<T> findAll() ;
	
	public T create(DTO dto);

	public T save(DTO dto,T t);

	

	public T update(DTO entity) ;
	public void deleteById(ID id);
}
