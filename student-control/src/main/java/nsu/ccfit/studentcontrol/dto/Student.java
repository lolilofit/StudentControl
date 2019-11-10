package nsu.ccfit.studentcontrol.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Entity
@Table(name = "students", schema = "C##OOAD")
public class Student implements Serializable {
    @Id
    @Column(name = "stud_id", unique = true)
    private final int id;

    @NotNull
    @Pattern(regexp = "^[А-Яа-я]([А-Яа-я]| |\\.)*$", message = "Student name must only contain words, spaces or dots")
    @Size(min = 1, max = 50, message = "Student name must contain from 1 to 50 characters")
    private final String name;

    @Column(name = "group_id", nullable = false)
    @JoinColumn(name = "group_id", referencedColumnName = "id", table = "groups")
    @NotNull
    private int group;
}
