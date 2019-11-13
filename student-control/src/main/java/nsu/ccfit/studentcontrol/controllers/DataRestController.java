package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.dto.Group;
import nsu.ccfit.studentcontrol.dto.Student;
import nsu.ccfit.studentcontrol.repos.GroupRepository;
import nsu.ccfit.studentcontrol.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/data", produces = "application/json")
@CrossOrigin("*")
public class DataRestController {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public DataRestController(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @PostMapping(path = "/students")
    public void getStudents(@RequestBody @Valid List<Student> students) {
        List<Group> groups = groupRepository.findAll();
        for (Student student: students) {
            if (!groups.contains(new Group(student.getGroup()))) {
                groupRepository.save(new Group(student.getGroup()));
                groups.add(new Group(student.getGroup()));
            }
            studentRepository.save(student);
        }
    }
}
