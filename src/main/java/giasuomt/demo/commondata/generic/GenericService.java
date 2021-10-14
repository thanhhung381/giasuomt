package giasuomt.demo.commondata.generic;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.educational.model.SubjectGroup;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class GenericService<DTO extends DtoId, T extends Object , ID>
		implements IGenericService<DTO, T, ID> {

	private JpaRepository<T, ID> repository; // Cần phải viết @Component GenericRepository (viết ở trong JpaConfig.java)
												// nó mới inject cái repository này được
	
	
	private MapDtoToModel mapDtoToModel;

	protected static Object t;

	public List<T> findAll() {
		return repository.findAll();
	}



	
	public T create(DTO dto) {

		
			return save(dto, t);
	}

	protected abstract T createContents(T t);

	public T save(DTO dto, T t) {
		try {
			t = (T) mapDtoToModel.map(dto, t);

			return repository.save(t);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public T update(DTO entity) {

		T t = repository.getOne((ID) entity.getId());

		return save(entity, t);
	}

	public void deleteById(ID id) {
		repository.deleteById(id);
	}

	public GenericService(JpaRepository<SubjectGroup, Long> repository2, MapDtoToModel mapDtoToModel2, Object t2) {
		// TODO Auto-generated constructor stub
	}
}
