package nsu.ccfit.studentcontrol.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Entity
@Table(name = "activity", schema = "ooad")
public class Activity implements Serializable {
    @Id
    @Column(name = "activity_id", unique = true)
    private final int id;

    @Column(name = "teacher_id", nullable = false)
    @NotNull(message = "Teacher id must be specified")
    @JoinColumn(name = "teacher_id", referencedColumnName = "teach_id", table = "teachers")
    private final int teacherId;

    @Column(name = "subject_id", nullable = false)
    @NotNull(message = "Subject id must be specified")
    @JoinColumn(name = "subject_id", referencedColumnName = "subj_id", table = "subjects")
    private final int subjectId;

    @Column(name = "group_id", nullable = false)
    @NotNull(message = "Group id must be specified")
    @JoinColumn(name = "group_id", referencedColumnName = "id", table = "groups")
    private final int groupId;
}
