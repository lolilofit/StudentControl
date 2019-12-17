package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.repos.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/attendance", produces = "application/json")
@CrossOrigin("*")
public class AttendanceRestController {
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceRestController(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }
}
