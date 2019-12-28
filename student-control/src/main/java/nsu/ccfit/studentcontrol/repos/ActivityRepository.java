package nsu.ccfit.studentcontrol.repos;

import nsu.ccfit.studentcontrol.dto.Activity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Integer> {
    List<Activity> findAll();
}
