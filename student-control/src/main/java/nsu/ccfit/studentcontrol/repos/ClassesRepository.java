package nsu.ccfit.studentcontrol.repos;

import nsu.ccfit.studentcontrol.dto.Class;
import org.springframework.data.repository.CrudRepository;

public interface ClassesRepository extends CrudRepository<Class, Integer> {
}
