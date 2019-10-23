package nsu.ccfit.studentcontrol.repos;

import nsu.ccfit.studentcontrol.dto.Activity;
import org.springframework.data.repository.CrudRepository;

interface ActivityRepository extends CrudRepository<Activity, Integer> {
}
