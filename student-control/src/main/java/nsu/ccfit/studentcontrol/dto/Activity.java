package nsu.ccfit.studentcontrol.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "activity", schema = "ooad")
public class Activity implements Serializable {
    @Id
    @Column(name = "activity_id", unique = true)
    private final int id;

    @Column(name = "teacher_id", nullable = false)
    @NotNull
    private final int teacherId;

    @Column(name = "subject_id", nullable = false)
    @NotNull
    private final int subjectId;

    @Column(name = "group_id", nullable = false)
    @NotNull
    private final int groupId;
}
