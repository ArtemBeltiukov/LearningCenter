import javax.swing.*;
import java.time.LocalDate;
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
        int daysPass1  = marksStudent1.size();

        Curriculum curriculumJava = new Curriculum("Java Developer ");
        Map<Course, Integer> courseJava = new HashMap<>();
        courseJava.put(new Course("Обзор технологий Java"), 8);
        courseJava.put(new Course("Библиотека JFC/Swing"), 16);
        courseJava.put(new Course("Технология JDBC"), 16);
        courseJava.put(new Course("Технология JAX"), 52);
        courseJava.put(new Course("Библиотеки commons"), 44);

        List<Integer> marksStudent2 = List.of(4, 5, 3, 2, 3, 3, 5, 5);
        int daysPass2  = marksStudent2.size();
        LocalDate date = LocalDate.now().minusDays(daysPass1);
        LocalDate date1 = LocalDate.now().minusDays(daysPass2);

        Student student2 = StudentFactory.createStudent("Petr",
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
        System.out.println(student1 + " осталось дней обучения: " + daysLeft);
        double avrMark = StudentFactory.averageMark(student1);
        System.out.println(student1 + " средняя оценка " + avrMark);
        StudentFactory.possibilityOfDeduction(student1);

        // Student 2
        int daysLeft2 = StudentFactory.daysLeft(student2);
        System.out.println(student2 + " осталось дней обучения: " + daysLeft2);
        double avrMark2 = StudentFactory.averageMark(student2);
        System.out.println(student2 + " средняя оценка " + avrMark2);
        StudentFactory.possibilityOfDeduction(student2);

        // Sorted list of students
        StudentFactory.listOfStudentsSortByMark(List.of(student1,student2), SortOrder.DESCENDING);
        StudentFactory.listOfStudentsSortByPossibleMark(List.of(student1,student2),SortOrder.DESCENDING);
        StudentFactory.listOfStudentsSortByTimeLeft(List.of(student1,student2),SortOrder.DESCENDING);
    }
}
