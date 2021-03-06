package nsu.ccfit.studentcontrol.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Entity
@Table(name = "attendance", schema = "C##OOAD")
@IdClass(AttendanceKey.class)
public class Attendance implements Serializable {
    public enum Status {
        ABSENT, PRESENT;

        @JsonValue
        public boolean toValue() {
            return this == PRESENT;
        }
    }

    @Id
    @Column(name = "stud_id", nullable = false)
    @NotNull(message = "Student id must be specified")
    @JoinColumn(name = "stud_id", referencedColumnName = "stud_id", table = "students")
    private final int studId;

    @ManyToOne
    @JoinColumn(name = "stud_id", insertable = false, updatable = false)
    private Student student;

    @Id
    @Column(name = "lesson_id", nullable = false)
    @NotNull(message = "Lesson id must be specified")
    @JoinColumn(name = "lesson_id", referencedColumnName = "activity_id", table = "activity")
    private final int lessonId;

    @ManyToOne
    @JoinColumn(name = "lesson_id", insertable = false, updatable = false)
    private Activity activity;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Status must be specified")
    @Enumerated(EnumType.STRING)
    private final Status status;

    @Column(name = "datetime", nullable = false)
    @NotNull(message = "Date time must be not null")
    private final Timestamp datetime;
}
