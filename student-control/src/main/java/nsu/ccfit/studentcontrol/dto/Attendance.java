package nsu.ccfit.studentcontrol.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "attendance", schema = "ooad")
@IdClass(AttendanceKey.class)
public class Attendance implements Serializable {
    public enum Status {
        ABSENT, PRESENT
    }

    @Id
    @Column(name = "stud_id", nullable = false)
    @NotNull(message = "Student id must be specified")
    private final int studId;

    @Id
    @Column(name = "lesson_id", nullable = false)
    @NotNull(message = "Lesson id must be specified")
    private final int lessonId;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Status must be specified")
    private final Status status;
}
