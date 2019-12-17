package nsu.ccfit.studentcontrol.data_getter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nsu.ccfit.studentcontrol.dto.Class;
import nsu.ccfit.studentcontrol.dto.Student;
import nsu.ccfit.studentcontrol.dto.TimetableScriptAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

//make it bean
@Component
public class CallScrypt {

    ObjectMapper mapper;

    @Autowired
    public CallScrypt(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    private String callScrypt(String scryptName) {
        String s;
        StringBuilder total = new StringBuilder("");
        List<Student> students = null;

        try {
            Process p = Runtime.getRuntime().exec("python src/main/python/" + scryptName + ".py");

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream(), Charset.forName("CP1251")));

            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                total.append(s);
            }
            return total.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "";
    }

    public Map<Class.Days, Map<Integer, TimetableScriptAdapter>> callTimetablePars() {
        try {
            String total = callScrypt("table");
           // List<Student> students = mapper.readValue(total.toString(), new TypeReference<List<Student>>() {});
            Map<Class.Days, Map<Integer, TimetableScriptAdapter>> table = mapper
                    .readValue(total, new TypeReference<Map<Class.Days, Map<Integer, TimetableScriptAdapter>>>() {
                        @Override
                        public Type getType() {
                            return super.getType();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> callStudentPars() {
        try {
            String total = callScrypt("students_pars");
            List<Student> students = mapper.readValue(total, new TypeReference<List<Student>>() {});
            return students;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
