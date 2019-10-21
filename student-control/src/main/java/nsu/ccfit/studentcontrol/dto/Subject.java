package nsu.ccfit.studentcontrol.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "subjects", schema = "ooad")
public class Subject implements Serializable {
    @Id
    @Column(name = "subj_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    @NotNull
    @Size(min = 1, max = 30, message = "Name of the subject must consist from 1 to 30 characters")
    @Column(name = "subj_name", unique = true)
    private final String name;
}
