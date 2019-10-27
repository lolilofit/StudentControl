package nsu.ccfit.studentcontrol;

import nsu.ccfit.studentcontrol.data_getter.StudentsDataCatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentControlApplication {

	public static void main(String[] args) {
		StudentsDataCatcher catcher = new StudentsDataCatcher();
		catcher.callPython();

		SpringApplication.run(StudentControlApplication.class, args);
	}

}
