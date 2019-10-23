package nsu.ccfit.studentcontrol.repos;

import nsu.ccfit.studentcontrol.dto.Attendance;
import nsu.ccfit.studentcontrol.dto.AttendanceKey;
import org.springframework.data.repository.CrudRepository;

interface AttendanceRepository extends CrudRepository<Attendance, AttendanceKey> {
}
