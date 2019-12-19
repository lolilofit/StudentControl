package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.dto.Activity;
import nsu.ccfit.studentcontrol.dto.Class;
import nsu.ccfit.studentcontrol.dto.Group;
import nsu.ccfit.studentcontrol.dto.Student;
import nsu.ccfit.studentcontrol.repos.ActivityRepository;
import nsu.ccfit.studentcontrol.repos.ClassesRepository;
import nsu.ccfit.studentcontrol.repos.GroupRepository;
import nsu.ccfit.studentcontrol.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/student", produces = "application/json")
@CrossOrigin("*")
public class StudentRestController {
    private final StudentRepository repository;
    private final GroupRepository groupRepository;
    private final ClassesRepository classesRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public StudentRestController(StudentRepository repository, GroupRepository groupRepository, ClassesRepository classesRepository, ActivityRepository activityRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.classesRepository = classesRepository;
        this.activityRepository = activityRepository;
    }

    @GetMapping(path = "/")
    public Iterable<Student> getAllStudents() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}/")
    public ResponseEntity<Student> getStudent(@PathVariable(name = "id") int id) {
        Optional<Student> maybeStudent = repository.findById(id);
        return maybeStudent.map(student -> new ResponseEntity<>(student, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/", consumes = "application/json")
    public Student saveStudent(@RequestBody @Valid Student student) {
        return repository.save(student);
    }

    @Transactional
    @GetMapping(path = "/activity/{id}")
    public ResponseEntity<List<Student>> getStudentsByClass(@PathVariable(name = "id") int id) {
        Activity currentActivity = activityRepository.findById(id).orElse(null);
        if (currentActivity == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        String groupId = currentActivity.getGroupId();
        Group currentGroup = groupRepository.findById(groupId).orElse(null);
        if (currentGroup == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(currentGroup.getGroupStudents(), HttpStatus.OK);
    }
}
