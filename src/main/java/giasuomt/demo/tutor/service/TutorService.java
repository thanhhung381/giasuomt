package giasuomt.demo.tutor.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.repository.AreaRepository;
import giasuomt.demo.location.service.IAreaService;
import giasuomt.demo.tutor.dto.CreateStudentDto;
import giasuomt.demo.tutor.dto.CreateTutorDto;
import giasuomt.demo.tutor.dto.TutorWithStudent;
import giasuomt.demo.tutor.model.Student;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.repository.StudentRepository;
import giasuomt.demo.tutor.repository.TutorRepository;

@Service
public class TutorService extends GenericService<Tutor, Long> implements ITutorService {

	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private IStudentService iStudentService;

	@Autowired
	private ModelMapper mapper2;

	
	private Logger logger=LoggerFactory.getLogger(TutorService.class);
	
	@Override
	public List<Tutor> findAll() {
		// TODO Auto-generated method stub
		return tutorRepository.findAll();
	}

	// luu
	@Override
	public Tutor save(CreateTutorDto dto) {
		// TODO Auto-generated method stub

		try {

			Tutor tutor = new Tutor();
			tutor = mapper2.map(dto, tutor.getClass());
			
			
			Optional<Area> tempArea = Optional.ofNullable(areaRepository.getOne(dto.getTempArea()));
				if(tempArea.isPresent())
					tutor.setTempArea(tempArea.get());
				
			Optional<Area> perArea = Optional.ofNullable(areaRepository.getOne(dto.getPerArea()));
				if(perArea.isPresent())
					tutor.setPerArea(perArea.get());

			Optional<Area> relArea = Optional.ofNullable(areaRepository.getOne(dto.getRelArea()));
					if(perArea.isPresent())
						tutor.setRelArea(relArea.get());
			
					
					logger.debug("Tutor is saved");
					
			return tutorRepository.save(tutor);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return null;

	}
	//DELETE 
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
		
		try {
			
			tutorRepository.deleteById(id);
			
			logger.debug("Tutor is  deleted");
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return;
		
	}
	
	//Update
	
	
	
	
	
	// StudentWithDto

	private void ModelToDto(Tutor tutor, TutorWithStudent tutorWithStudent) {
		
		
	//	Set<Student> students = studentRepository.findByallStudent(tutor.getTutorCode());
		mapper2.map(tutor, tutorWithStudent);
		//tutorWithStudent.setStudents(null);

	}

	private List<TutorWithStudent> MaptoList(List<Tutor> students) {
		List<TutorWithStudent> tutorWithStudents = new LinkedList<>();
		for (Tutor tutor : students) {
			TutorWithStudent student = new TutorWithStudent();
			ModelToDto(tutor, student);
			tutorWithStudents.add(student);

		}

		return tutorWithStudents;

	}

	public List<TutorWithStudent> findalll() {

		List<Tutor> list = tutorRepository.findAll();
		List<TutorWithStudent> res = MaptoList(list);
		return res;
	}

}
