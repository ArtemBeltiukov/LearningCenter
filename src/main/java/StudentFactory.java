import javax.swing.*;
import java.util.*;

public class StudentFactory {

    public static Student createStudent(String firstName, String lastName,
                                 Curriculum curriculum,
                                 Date startDate, Map<Course, Integer> courses,
                                 List<Integer> marks){
        return new Student(firstName, lastName, curriculum, startDate, courses, marks);
    }

    public static Integer daysLeft(Student student){
        Map<Course,Integer> courses = student.getCourses();
        int hoursAll = courses.values().stream().mapToInt(x->x).sum();
        long hoursPass = (new Date().getTime() - student.getStartDate().getTime())/ (60 * 60 * 1000);
        int hoursLeft = hoursAll - Math.toIntExact(hoursPass)/3;
        int daysleft = hoursLeft / 8;
        return daysleft;
    }

    public static Double averageMark(Student student) {
        List<Integer> marks = student.getMarks();
        OptionalDouble avr = marks.stream().mapToInt(x->x).average();
        return avr.getAsDouble();
    }

    public static Double possibleAverageMark(Student student) {
        List<Integer> marks = new ArrayList<>(student.getMarks());
        int daysLeft = daysLeft(student);
        while (daysLeft!=0){
            marks.add(5);
            daysLeft--;
        }
        OptionalDouble avr = marks.stream().mapToInt(x->x).average();
        return avr.getAsDouble();
    }

    public static void possibilityOfDeduction(Student student){
        double possibleAvr = possibleAverageMark(student);
        if (possibleAvr < 4.5f){
            System.out.println("Средняя оценка " + possibleAvr + ". Отчислить.");
        }
        else{
            System.out.println("Средняя оценка " + possibleAvr +". Может продолжать обучение.");
        }
    }

    public static void listOfStudentsSortByMark(List<Student> list, SortOrder sortOrder) {
        List<Student> students = new ArrayList<>(list);
        if (sortOrder.equals(SortOrder.DESCENDING)){
            students.sort((o1,o2)->{
                if (averageMark(o1)>averageMark(o2))
                    return -1;
                else if (averageMark(o1)<averageMark(o2))
                    return 1;
                else return 0;
            });
        }else if (sortOrder.equals(SortOrder.ASCENDING)){
            students.sort((o1,o2)->{
                if (averageMark(o1)>averageMark(o2))
                    return 1;
                else if (averageMark(o1)<averageMark(o2))
                    return -1;
                else return 0;
            });
        }
        System.out.println("List of students sorted by actual mark " +students);
    }

    public static void listOfStudentsSortByPossibleMark(List<Student> list, SortOrder sortOrder) {
        List<Student> students = new ArrayList<>(list);
        if (sortOrder.equals(SortOrder.DESCENDING)){
            students.sort((o1,o2)->{
                if (possibleAverageMark(o1)>possibleAverageMark(o2))
                    return -1;
                else if (possibleAverageMark(o1)<possibleAverageMark(o2))
                    return 1;
                else return 0;
            });
        }else if (sortOrder.equals(SortOrder.ASCENDING)){
            students.sort((o1,o2)->{
                if (possibleAverageMark(o1)>possibleAverageMark(o2))
                    return 1;
                else if (possibleAverageMark(o1)<possibleAverageMark(o2))
                    return -1;
                else return 0;
            });
        }
        System.out.println("List of students sorted by maximum possible mark " +students);
    }

    public static void listOfStudentsSortByTimeLeft(List<Student> list, SortOrder sortOrder){
        List<Student> students = new ArrayList<>(list);
        if (sortOrder.equals(SortOrder.DESCENDING)){
            students.sort((o1,o2)->{
                if (daysLeft(o1)>daysLeft(o2))
                    return -1;
                else if (daysLeft(o1)<daysLeft(o2))
                    return 1;
                else return 0;
            });
        }else if (sortOrder.equals(SortOrder.ASCENDING)){
            students.sort((o1,o2)->{
                if (daysLeft(o1)>daysLeft(o2))
                    return 1;
                else if (daysLeft(o1)<daysLeft(o2))
                    return -1;
                else return 0;
            });
        }
        System.out.println("List of students sorted by time left " +students);
    }
}
