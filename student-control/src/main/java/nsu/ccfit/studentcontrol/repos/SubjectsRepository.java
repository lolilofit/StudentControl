package nsu.ccfit.studentcontrol.repos;

import nsu.ccfit.studentcontrol.dto.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectsRepository extends CrudRepository<Subject, Integer> {
}
