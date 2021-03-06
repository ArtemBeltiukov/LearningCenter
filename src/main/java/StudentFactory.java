import javax.swing.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class StudentFactory {
    static MathContext context = new MathContext(2, RoundingMode.HALF_UP);
    public static Student createStudent(String firstName, String lastName,
                                        Curriculum curriculum,
                                        LocalDate startDate, Map<Course, Integer> courses,
                                        List<Integer> marks){
        return new Student(firstName, lastName, curriculum, startDate, courses, marks);
    }

    public static Integer daysLeft(Student student){
        Map<Course,Integer> courses = student.getCourses();
        int hoursAll = courses.values().stream().mapToInt(x->x).sum();
        long daysPass = Period.between(LocalDate.now(),student.getStartDate()).getDays();
        return hoursAll/8 - Math.toIntExact(daysPass)*-1;
    }

    public static Double averageMark(Student student) {
        List<Integer> marks = student.getMarks();
        OptionalDouble avr = marks.stream().mapToInt(x->x).average();
        BigDecimal result = new BigDecimal(avr.getAsDouble(), context);
        return result.doubleValue();
    }

    public static Double possibleAverageMark(Student student) {
        List<Integer> marks = new ArrayList<>(student.getMarks());
        int daysLeft = daysLeft(student);
        while (daysLeft!=0){
            marks.add(5);
            daysLeft--;
        }
        OptionalDouble avr = marks.stream().mapToInt(x->x).average();
        BigDecimal result = new BigDecimal(avr.getAsDouble(), context);
        return result.doubleValue();
    }

    public static void possibilityOfDeduction(Student student){
        double possibleAvr = possibleAverageMark(student);
        if (possibleAvr < 4.5f){
            System.out.println("Максимально возможная средняя оценка " + possibleAvr + ". Отчислить.");
        }
        else{
            System.out.println("Максимально возможная средняя оценка " + possibleAvr +". Может продолжать обучение.");
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
        System.out.println("Список студентов, отсоритрованный по текущей средней оценке " + students + ". " + sortOrder.toString());
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
        System.out.println("Список студентов, отсортированный по максимально возможной средней оценке " +students + ". " + sortOrder.toString());
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
        System.out.println("Список студентов, отсортированный по оставщемуся времени обучения " +students + ". " + sortOrder.toString());
    }
}
