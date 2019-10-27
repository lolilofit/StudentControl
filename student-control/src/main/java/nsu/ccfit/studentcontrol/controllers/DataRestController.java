package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.data_getter.StudentData;
import nsu.ccfit.studentcontrol.data_getter.StudentsDataCatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/data")
@CrossOrigin("*")
public class DataRestController {

    @GetMapping(path = "/students")
    public void getStudents(List<StudentData> studentData) {
        //save data
    }
}
