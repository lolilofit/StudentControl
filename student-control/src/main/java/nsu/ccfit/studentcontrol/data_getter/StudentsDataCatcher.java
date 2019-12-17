package nsu.ccfit.studentcontrol.data_getter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nsu.ccfit.studentcontrol.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

//make it bean
@Component
public class StudentsDataCatcher {

    ObjectMapper mapper;

    @Autowired
    public StudentsDataCatcher(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Student> callPython() {
        String s = null;
        StringBuilder total = new StringBuilder("");
        List<Student> students = null;

        try {
            Process p = Runtime.getRuntime().exec("python src/main/python/students_pars.py");

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream(), Charset.forName("CP1251")));

            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                total.append(s);
            }
            students = mapper.readValue(total.toString(), new TypeReference<List<Student>>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;

    }
}
