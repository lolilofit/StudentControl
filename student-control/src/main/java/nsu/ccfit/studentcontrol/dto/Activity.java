package nsu.ccfit.studentcontrol.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Entity
@Table(name = "activity", schema = "C##OOAD")
public class Activity implements Serializable {
    @Id
    @Column(name = "activity_id", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private final Integer id;

    @Column(name = "teacher_id", nullable = false)
    @NotNull(message = "Teacher id must be specified")
    @JoinColumn(name = "teacher_id", referencedColumnName = "teach_id", table = "teachers")
    private final int teacherId;

    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private Teacher teacher;

    @Column(name = "subject_id", nullable = false)
    @NotNull(message = "Subject id must be specified")
    @JoinColumn(name = "subject_id", referencedColumnName = "subj_id", table = "subjects")
    private final int subjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private Subject subject;

    @Column(name = "group_id", nullable = false)
    @NotNull(message = "Group id must be specified")
    @JoinColumn(name = "group_id", referencedColumnName = "num", table = "groups")
    private final String groupId;
}
