package nsu.ccfit.studentcontrol.repos;

import nsu.ccfit.studentcontrol.dto.Attendance;
import nsu.ccfit.studentcontrol.dto.AttendanceKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendanceRepository extends CrudRepository<Attendance, AttendanceKey> {
    @Query(value = "SELECT attendance.* FROM  attendance, activity WHERE activity.teacher_id = ?1 AND attendance.lesson_id = activity.activity_id ORDER BY activity.GROUP_ID", nativeQuery = true)
    List<Attendance> findAttendanceByTeacher(int teacherId);
}
