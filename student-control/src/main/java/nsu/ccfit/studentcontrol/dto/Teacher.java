package nsu.ccfit.studentcontrol.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Entity
@Table(name = "teachers", schema = "C##OOAD")
public class Teacher implements Serializable {
    @Id
    @Column(name = "teach_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private final Integer id;

    @NotNull
    @Pattern(regexp = "^[А-Яа-яё]([А-Яа-яё]| |\\.)*$", message = "Name must only contain words and dots")
    @Size(min = 1, max = 50, message = "Teacher name must contain from 1 to 50 characters")
    private final String name;
}
