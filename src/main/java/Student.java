import java.util.Date;
import java.util.List;
import java.util.Map;

public class Student {
    private String firstName;
    private String lastName;
    private Curriculum curriculum;
    private Date startDate;
    private Map<Course,Integer> courses;
    private List<Integer> marks;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Map<Course, Integer> getCourses() {
        return courses;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public Student(String firstName, String lastName, Curriculum curriculum, Date startDate, Map<Course, Integer> courses, List<Integer> marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.curriculum = curriculum;
        this.startDate = startDate;
        this.courses = courses;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return  firstName + " " + lastName;
    }
}
