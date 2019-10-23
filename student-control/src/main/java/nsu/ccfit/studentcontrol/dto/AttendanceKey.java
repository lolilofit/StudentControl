package nsu.ccfit.studentcontrol.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
public class AttendanceKey implements Serializable {
    private final int stud_id;
    private final int lesson_id;
}
