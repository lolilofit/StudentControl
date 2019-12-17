package nsu.ccfit.studentcontrol;

import com.fasterxml.jackson.databind.ObjectMapper;
import nsu.ccfit.studentcontrol.data_getter.CallScrypt;
import nsu.ccfit.studentcontrol.dto.Class;
import nsu.ccfit.studentcontrol.dto.TimetableScriptAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Map;

@SpringBootApplication
public class StudentControlApplication {

	public static void main(String[] args) {
		CallScrypt studentsDataCatcher = new CallScrypt(new ObjectMapper());
		Map<Class.Days, Map<Integer, TimetableScriptAdapter>> table = studentsDataCatcher.callTimetablePars();
		System.out.println(table);

		SpringApplication.run(StudentControlApplication.class, args);
	}
}
