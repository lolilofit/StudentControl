package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.dto.Subject;
import nsu.ccfit.studentcontrol.repos.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/subject", produces = "application/json")
@CrossOrigin("*")
public class SubjectRestController {
    private final SubjectsRepository repository;

    @Autowired
    public SubjectRestController(SubjectsRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/")
    public Iterable<Subject> getAllSubjects() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}/")
    public ResponseEntity<Subject> getSubjects(@PathVariable(name = "id") int id) {
        Optional<Subject> maybeSubject = repository.findById(id);
        return maybeSubject.map(subject -> new ResponseEntity<>(subject, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/", consumes = "application/json")
    public Subject saveSubject(@RequestBody @Valid Subject subject) {
        return repository.save(subject);
    }
}
