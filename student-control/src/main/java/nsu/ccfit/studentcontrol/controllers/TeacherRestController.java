package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.dto.Teacher;
import nsu.ccfit.studentcontrol.repos.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/teacher", produces = "application/json")
@CrossOrigin("*")
public class TeacherRestController {
    private final TeacherRepository repository;

    @Autowired
    public TeacherRestController(TeacherRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/")
    public Iterable<Teacher> getAllTeachers() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}/")
    public ResponseEntity<Teacher> getTeacher(@PathVariable(name = "id", required = true) int id) {
        Optional<Teacher> maybeTeacher = repository.findById(id);
        return maybeTeacher.map(teacher -> new ResponseEntity<>(teacher, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/", consumes = "application/json")
    public Teacher saveTeacher(@RequestBody @Valid Teacher newTeacher) {
        return repository.save(newTeacher);
    }
}
