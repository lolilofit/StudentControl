package nsu.ccfit.studentcontrol.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttendanceKey implements Serializable {
    private final int stud_id;
    private final int lesson_id;
}
