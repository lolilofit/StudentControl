package nsu.ccfit.studentcontrol.controllers;

import nsu.ccfit.studentcontrol.dto.*;
import nsu.ccfit.studentcontrol.dto.Class;
import nsu.ccfit.studentcontrol.python.data.PythonDataCatcher;
import nsu.ccfit.studentcontrol.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    private final ActivityRepository activityRepository;
    private final ClassesRepository classesRepository;
    private final PythonDataCatcher pythonDataCatcher;

    @Autowired
    public DataRestController(StudentRepository studentRepository, GroupRepository groupRepository, SubjectsRepository subjectsRepository, TeacherRepository teacherRepository, ActivityRepository activityRepository, ClassesRepository classesRepository, PythonDataCatcher pythonDataCatcher) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.subjectsRepository = subjectsRepository;
        this.teacherRepository = teacherRepository;
        this.activityRepository = activityRepository;
        this.classesRepository = classesRepository;
        this.pythonDataCatcher = pythonDataCatcher;
    }

    @Transactional
    @PostMapping(path = "/students", consumes = "application/json")
    public void postStudents(/*@RequestBody @Valid List<Student> students*/) {
        List<Student> students = pythonDataCatcher.callStudentPars();
        List<Group> groups = groupRepository.findAll();
        for (Student student: students) {
            if (!groups.contains(new Group(student.getGroup()))) {
                groupRepository.save(new Group(student.getGroup()));
                groups.add(new Group(student.getGroup()));
            }
            studentRepository.save(student);
        }
    }

    @Transactional
    @PostMapping(path = "/timetable", consumes = "application/json")
    public void postTimetable(/*@RequestBody @Valid Map<String, Map<Class.Days, Map<Integer, TimetableScriptAdapter>>> data*/) {
        Map<String, Map<Class.Days, Map<Integer, TimetableScriptAdapter>>> data = pythonDataCatcher.callTablePars();
        List<Subject> subjects = subjectsRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();
        List<Group> groups = groupRepository.findAll();
        List<Activity> activities = activityRepository.findAll();

        data.forEach((groupNum, groupTable) -> {
            Group group = new Group(groupNum);
            if (!groups.contains(group)) {
                group = groupRepository.save(new Group(groupNum));
                groups.add(group);
            } else {
                int groupInd = groups.indexOf(group);
                group = groups.get(groupInd);
            }

            Group finalGroup = group;
            groupTable.forEach((day, groupDayList) -> {
                groupDayList.forEach((lessonNum, timetableScriptAdapter) -> {
                    if (timetableScriptAdapter.getTeacher().equals("НЕТ"))
                        return;

                    Subject subject = new Subject(null, timetableScriptAdapter.getSubject());
                    if (!subjects.contains(subject)) {
                        subject = subjectsRepository.save(subject);
                        subjects.add(subject);
                    } else {
                        int subjectInd = subjects.indexOf(subject);
                        subject = subjects.get(subjectInd);
                    }

                    Teacher teacher = new Teacher(null, timetableScriptAdapter.getTeacher());
                    if (!teachers.contains(teacher)) {
                        System.out.println("SAVING: " + teacher);
                        teacher = teacherRepository.save(teacher);
                        teachers.add(teacher);
                    } else {
                        int teacherInd = teachers.indexOf(teacher);
                        teacher = teachers.get(teacherInd);
                    }

                    Activity activity = new Activity(null, teacher.getId(), subject.getId(), finalGroup.getGroupNum());
                    if (!activities.contains(activity)) {
                        activity = activityRepository.save(activity);
                        activities.add(activity);
                    } else {
                        int activityInd = activities.indexOf(activity);
                        activity = activities.get(activityInd);
                    }

                    Class currentClass = new Class(null, activity.getId(), lessonNum, day);
                    classesRepository.save(currentClass);
                });
            });
        });
    }
}
