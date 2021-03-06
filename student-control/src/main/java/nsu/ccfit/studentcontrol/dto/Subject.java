package nsu.ccfit.studentcontrol.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Entity
@Table(name = "subjects", schema = "C##OOAD")
public class Subject implements Serializable {
    @Id
    @Column(name = "subj_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private final Integer id;

    @NotNull(message = "Name must be specified")
    @Size(min = 1, max = 30, message = "Name of the subject must consist from 1 to 30 characters")
    @Column(name = "subj_name", unique = true)
    private final String name;
}
