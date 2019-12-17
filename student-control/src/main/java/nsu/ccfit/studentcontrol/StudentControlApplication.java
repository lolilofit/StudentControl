package nsu.ccfit.studentcontrol;

import com.fasterxml.jackson.databind.ObjectMapper;
import nsu.ccfit.studentcontrol.data_getter.StudentsDataCatcher;
import nsu.ccfit.studentcontrol.dto.Student;
import org.python.core.Options;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.List;

@SpringBootApplication
public class StudentControlApplication {

	public static void main(String[] args) {
		StudentsDataCatcher studentsDataCatcher = new StudentsDataCatcher(new ObjectMapper());
		List<Student> studList = studentsDataCatcher.callPython();
		System.out.println(studList);

		SpringApplication.run(StudentControlApplication.class, args);
	}
}
