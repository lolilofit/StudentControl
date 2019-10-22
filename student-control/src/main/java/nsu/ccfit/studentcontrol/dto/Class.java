package nsu.ccfit.studentcontrol.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;

@Data
@Entity
@Table(name = "timetable", schema = "ooad")
public class Class implements Serializable {
    public enum Days {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    @Id
    @Column(name = "id", unique = true)
    private final int id;

    @Column(name = "activityId", nullable = false)
    @NotNull(message = "Activity id must be specified")
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
