package nsu.ccfit.studentcontrol.repos;

import nsu.ccfit.studentcontrol.dto.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectsRepository extends CrudRepository<Subject, Integer> {
    List<Subject> findAll();
}
