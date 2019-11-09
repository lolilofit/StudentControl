package nsu.ccfit.studentcontrol.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private final int id;

    @NotNull(message = "Group number must be specified")
    @Column(name = "num", unique = true)
    private final int groupNum;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> groupStudents;
}
