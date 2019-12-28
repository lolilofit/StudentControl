package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.dto.Group;
import nsu.ccfit.studentcontrol.repos.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/group", produces = "application/json")
@CrossOrigin("*")
public class GroupRestController {
    private final GroupRepository repository;

    @Autowired
    public GroupRestController(GroupRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/")
    public Iterable<Group> getAllGroups() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}/")
    public ResponseEntity<Group> getGroup(@PathVariable(name = "id") String id) {
        Optional<Group> maybeGroup = repository.findById(id);
        return maybeGroup.map(group -> new ResponseEntity<>(group, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/", consumes = "application/json")
    public Group saveGroup(@RequestBody @Valid Group group) {
        return repository.save(group);
    }
}
