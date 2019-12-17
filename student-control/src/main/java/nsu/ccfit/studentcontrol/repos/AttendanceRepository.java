package nsu.ccfit.studentcontrol.repos;

import nsu.ccfit.studentcontrol.dto.Attendance;
import nsu.ccfit.studentcontrol.dto.AttendanceKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceRepository extends CrudRepository<Attendance, AttendanceKey> {
}
