package nsu.ccfit.studentcontrol.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "students", schema = "ooad")
public class Student implements Serializable {
    @Id
    @Column(name = "stud_id", unique = true)
    private final int id;

    @NotNull
    @Pattern(regexp = "^\\w(\\w| |\\.)*$", message = "Student name must only contain words, spaces or dots")
    @Size(min = 1, max = 50, message = "Student name must contain from 1 to 50 characters")
    private final String name;
}
