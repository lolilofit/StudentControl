package nsu.ccfit.studentcontrol.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Entity
@Table(name = "groups", schema = "C##OOAD")
public class Group implements Serializable {
    @Id
    @NotNull(message = "Group number must be specified")
    @Column(name = "num", unique = true)
    private final String groupNum;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<Student> groupStudents;
}
