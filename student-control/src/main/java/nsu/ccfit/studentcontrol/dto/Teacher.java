package nsu.ccfit.studentcontrol.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "teachers", schema = "ooad")
public class Teacher implements Serializable {
    @Id
    @Column(name = "teach_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    @NotNull
    @Pattern(regexp = "^\\w(\\w| |\\.)*$", message = "Name must only contain words and dots")
    @Size(min = 1, max = 50, message = "Teacher name must contain from 1 to 50 characters")
    private final String name;
}
