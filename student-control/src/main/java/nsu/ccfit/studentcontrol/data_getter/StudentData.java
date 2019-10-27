package nsu.ccfit.studentcontrol.data_getter;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class StudentData implements Serializable {
    String name;
    String group;
    String student_id;

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public String getGroup() { return group; }
    public String getStudent_id() { return student_id; }
    public void setGroup(String group) { this.group = group; }
    public void setStudent_id(String student_id) { this.student_id = student_id; }
}
