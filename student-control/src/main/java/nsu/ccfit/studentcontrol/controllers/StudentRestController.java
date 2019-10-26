package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.dto.Student;
import nsu.ccfit.studentcontrol.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/student", produces = "application/json")
@CrossOrigin("*")
public class StudentRestController {
    private final StudentRepository repository;

    @Autowired
    public StudentRestController(StudentRepository repository) {
        this.repository = repository;
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

    @PostMapping(path = "/")
    public Student saveStudent(@RequestBody @Valid Student student) {
        return repository.save(student);
    }
}
