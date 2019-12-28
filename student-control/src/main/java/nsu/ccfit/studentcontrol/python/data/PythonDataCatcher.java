package nsu.ccfit.studentcontrol.python.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nsu.ccfit.studentcontrol.dto.Class;
import nsu.ccfit.studentcontrol.dto.Student;
import nsu.ccfit.studentcontrol.dto.TimetableScriptAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Service
public class PythonDataCatcher {

    private final ObjectMapper mapper;

    @Autowired
    public PythonDataCatcher(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Student> callStudentPars() {
        return callPythonWindows("python src/main/python/students_pars.py", "CP1251", new TypeReference<List<Student>>() {});
    }

    public Map<String, Map<Class.Days, Map<Integer, TimetableScriptAdapter>>> callTablePars() {
        return callPythonWindows("python src/main/python/table.py", "CP1251", new TypeReference<Map<String, Map<Class.Days, Map<Integer, TimetableScriptAdapter>>>>() {});
    }

    private<T> T callPythonWindows(String command, String charsetName, TypeReference<T> classReference) {
        String s;
        StringBuilder total = new StringBuilder("");
        T result = null;
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream(), Charset.forName(charsetName)));

            while ((s = stdInput.readLine()) != null) {
                total.append(s);
            }
            result = mapper.readValue(total.toString(), classReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
