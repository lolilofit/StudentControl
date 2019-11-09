package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.data_getter.StudentsDataCatcher;
import nsu.ccfit.studentcontrol.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/data", produces = "application/json")
public class DataRestController {
    private final StudentsDataCatcher catcher;

    @Autowired
    public DataRestController(StudentsDataCatcher catcher) {
        this.catcher = catcher;
    }

    @GetMapping(path = "/students")
    public void getStudents() {
        //save data
        List<Student> list = catcher.callPython();
        list.forEach(System.out::println);
    }
}
