package nsu.ccfit.studentcontrol.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Entity
@Table(name = "timetable", schema = "C##OOAD")
public class Class implements Serializable {
    public enum Days {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    @Id
    @Column(name = "id", unique = true)
    private final int id;

    @Column(name = "activity_id", nullable = false)
    @NotNull(message = "Activity id must be specified")
    @JoinColumn(name = "activity_id", referencedColumnName = "activity_id", table = "activity")
    private final int activityId;

    @Column(name = "start_time", nullable = false)
    @NotNull(message = "Start time needs to be specified")
    private final Time start;

    @Column(name = "end_time", nullable = false)
    @NotNull(message = "End time needs to be specified")
    private final Time end;

    @Column(name = "day", nullable = false)
    @NotNull(message = "Day of the week must be specified")
    private final Days day;
}
