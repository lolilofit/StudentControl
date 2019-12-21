package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.dto.Attendance;
import nsu.ccfit.studentcontrol.dto.Student;
import nsu.ccfit.studentcontrol.repos.AttendanceRepository;
import nsu.ccfit.studentcontrol.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/attendance", produces = "application/json")
@CrossOrigin("*")
public class AttendanceRestController {
    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceRestController(StudentRepository studentRepository, AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @PostMapping(path = "/all", consumes = "application/json")
    public void postReport(@RequestBody @Valid Attendance[] attendances) {
        List<Attendance> nextAttendances = Arrays.asList(attendances);
        attendanceRepository.saveAll(nextAttendances);
    }

    @GetMapping(path = "/student/{id}/")
    public ResponseEntity<List<Attendance>> studentAttendance(@PathVariable(name = "id") int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student.getAttendanceList(), HttpStatus.OK);
    }
}
