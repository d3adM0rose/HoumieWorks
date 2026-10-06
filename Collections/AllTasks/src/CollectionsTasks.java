import java.util.*;

public class CollectionsTasks {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        students.add(new Student("Али", 18));
        students.add(new Student("Маша", 20));
        students.add(new Student("Данил", 19));

        System.out.println("Студенты:");

        for (Student student : students) {
            System.out.println(student);
        }

        // Оценки студентов
        Map<String, Integer> grades = new HashMap<>();
        grades.put("Али", 90);
        grades.put("Маша", 85);
        grades.put("Данил", 95);

        System.out.println("\nОценки:");
        System.out.println(grades);

        // Сортировка по возрасту
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.age, s2.age);
            }
        });

        System.out.println("\nПосле сортировки:");

        for (Student student : students) {
            System.out.println(student);
        }
    }
}
