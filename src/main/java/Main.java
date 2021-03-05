import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Curriculum curriculumJ2EE = new Curriculum("J2EE Developer");

        Map<Course, Integer> courseJ2EE = new HashMap<>();
        courseJ2EE.put(new Course("Технология Java Servlets"), 16);
        courseJ2EE.put(new Course("Struts Framework"), 24);
        courseJ2EE.put(new Course("Spring Framework"), 48);
        courseJ2EE.put(new Course("Hibernate"), 20);

        List<Integer> marksStudent1 = List.of(3, 4, 2, 5, 3, 3);

        Curriculum curriculumJava = new Curriculum("Java Developer ");
        Map<Course, Integer> courseJava = new HashMap<>();
        courseJava.put(new Course("Обзор технологий Java"), 8);
        courseJava.put(new Course("Библиотека JFC/Swing"), 16);
        courseJava.put(new Course("Технология JDBC"), 16);
        courseJava.put(new Course("Технология JAX"), 52);
        courseJava.put(new Course("Библиотеки commons"), 44);

        List<Integer> marksStudent2 = List.of(4, 5, 3, 2, 3, 3, 5, 5);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = null;

        try {
            date = format.parse("02/28/2021 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date date1 = null;
        try {
            date1 = format.parse("02/26/2021 10:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Student student2 = new Student("Petr",
                "Petrov",
                curriculumJava,
                date1,
                courseJava,
                marksStudent2);

        Student student1 = StudentFactory.createStudent("Ivan",
                "Ivanov",
                curriculumJ2EE,
                date,
                courseJ2EE,
                marksStudent1);
        // Student 1
        int daysLeft = StudentFactory.daysLeft(student1);
        System.out.println(student1 + " days left: " + daysLeft);
        double avrMark = StudentFactory.averageMark(student1);
        System.out.println(student1 + " average mark is " + avrMark);
        StudentFactory.possibilityOfDeduction(student1);

        // Student 2
        int daysLeft2 = StudentFactory.daysLeft(student2);
        System.out.println(student2 + " days left: " + daysLeft2);
        double avrMark2 = StudentFactory.averageMark(student2);
        System.out.println(student2 + " average mark is " + avrMark2);
        StudentFactory.possibilityOfDeduction(student2);

        // Sorted list of students
        StudentFactory.listOfStudentsSortByMark(List.of(student1,student2), SortOrder.DESCENDING);
        StudentFactory.listOfStudentsSortByPossibleMark(List.of(student1,student2),SortOrder.DESCENDING);
        StudentFactory.listOfStudentsSortByTimeLeft(List.of(student1,student2),SortOrder.DESCENDING);
    }
}
