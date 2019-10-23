package nsu.ccfit.studentcontrol.repos;

import nsu.ccfit.studentcontrol.dto.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
