package nsu.ccfit.studentcontrol;

import com.fasterxml.jackson.databind.ObjectMapper;
import nsu.ccfit.studentcontrol.dto.Class;
import nsu.ccfit.studentcontrol.dto.Student;
import nsu.ccfit.studentcontrol.dto.TimetableScriptAdapter;
import nsu.ccfit.studentcontrol.python.data.PythonDataCatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class StudentControlApplication {

	public static void main(String[] args) {
		PythonDataCatcher pythonDataCatcher = new PythonDataCatcher(new ObjectMapper());
		List<Student> studList = pythonDataCatcher.callStudentPars();
		System.out.println(studList);
		System.out.println("------------------------------------");
		Map<String, Map<Class.Days, Map<Integer, TimetableScriptAdapter>>> timetable = pythonDataCatcher.callTablePars();
		System.out.println(timetable);

		SpringApplication.run(StudentControlApplication.class, args);
	}
}
