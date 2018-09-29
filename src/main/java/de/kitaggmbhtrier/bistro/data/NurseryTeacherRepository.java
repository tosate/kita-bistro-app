package de.kitaggmbhtrier.bistro.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface NurseryTeacherRepository extends CrudRepository<NurseryTeacher, Long> {
	List<NurseryTeacher> findByLastName(String lastName);
	Optional<NurseryTeacher> findByLastNameAndFirstName(String firstName, String lastName);
}
