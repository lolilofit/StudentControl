package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.dto.*;
import nsu.ccfit.studentcontrol.dto.Class;
import nsu.ccfit.studentcontrol.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/data", produces = "application/json")
@CrossOrigin("*")
public class DataRestController {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final SubjectsRepository subjectsRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public DataRestController(StudentRepository studentRepository, GroupRepository groupRepository, SubjectsRepository subjectsRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.subjectsRepository = subjectsRepository;
        this.teacherRepository = teacherRepository;
    }

    @PostMapping(path = "/students")
    public void postStudents(@RequestBody @Valid List<Student> students) {
        List<Group> groups = groupRepository.findAll();
        for (Student student: students) {
            if (!groups.contains(new Group(student.getGroup()))) {
                groupRepository.save(new Group(student.getGroup()));
                groups.add(new Group(student.getGroup()));
            }
            studentRepository.save(student);
        }
    }
//
//    @PostMapping(path = "/timetable")
//    public void postTimetable(@RequestBody @Valid Map<String, Map<Class.Days, TimetableScriptAdapter>> data) {
//        List<Subject> subjects = subjectsRepository.findAll();
//        List<Teacher> teachers = teacherRepository.findAll();
//        List<Group> groups = groupRepository.findAll();
//        data.forEach((group_num, group_table) -> {
//            if (!groups.contains(new Group(group_num))) {
//                groupRepository.save(new Group(group_num));
//                groups.add(new Group(group_num));
//            }
//            group_table.forEach((days, timetableScriptAdapter) -> {
//
//                Subject subject = new Subject();
//                subject.setName(timetableScriptAdapter.getSubject());
//                if (!subjects.contains
//            });
//        });
//    }
}
