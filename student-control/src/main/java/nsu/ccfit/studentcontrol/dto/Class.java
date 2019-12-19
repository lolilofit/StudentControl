package nsu.ccfit.studentcontrol.dto;

import com.fasterxml.jackson.annotation.JsonValue;
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
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
        @JsonValue
        public int toValue() {
            return ordinal();
        }
    }

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private final Integer id;

    @Column(name = "activity_id", nullable = false)
    @NotNull(message = "Activity id must be specified")
    @JoinColumn(name = "activity_id", referencedColumnName = "activity_id", table = "activity")
    private final int activityId;

    @Column(name = "period_id", nullable = false)
    @NotNull(message = "Period id must be specified")
    @JoinColumn(name = "period_id", referencedColumnName = "period_id", table = "period")
    private final int periodId;

    @Column(name = "day", nullable = false)
    @NotNull(message = "Day of the week must be specified")
    @Enumerated(EnumType.STRING)
    private final Days day;
}
