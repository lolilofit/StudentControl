package nsu.ccfit.studentcontrol.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Entity
@Table(name = "period", schema = "C##OOAD")
public class Lesson implements Serializable {
    @Id
    @Column(name = "period_id", nullable = false)
    private final int id;

    @Column(name = "start_time", nullable = false)
    private final Time start;

    @Column(name = "end_time", nullable = false)
    private final Time end;
}
