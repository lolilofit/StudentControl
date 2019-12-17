package nsu.ccfit.studentcontrol.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor
public class TimetableScriptAdapter implements Serializable {
    private final String subject;
    private final String room;
    private final String teacher;
}
