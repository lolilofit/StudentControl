package nsu.ccfit.studentcontrol.data_getter;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

//make it bean
@Component
public class StudentsDataCatcher {
    public void callPython() {
        String s = null;
        try {
            Process p = Runtime.getRuntime().exec("python src/main/python/students_pars.py");

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream(), Charset.forName("CP1251")));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
