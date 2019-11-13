package nsu.ccfit.studentcontrol.repos;

import nsu.ccfit.studentcontrol.dto.Group;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface GroupRepository extends CrudRepository<Group, String> {
    boolean existsByGroupNum(@NotNull(message = "Group number must be specified") String groupNum);
    List<Group> findAll();
}
