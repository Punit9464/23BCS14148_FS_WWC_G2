import java.util.*;

class Person {
    private String id, name;
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return this.id; }
    public String getName() { return this.name; }
}

class Student extends Person {
    private int marks;

    public Student(String id, String name, int marks) {
        super(id, name);
        this.marks = marks;
    }

    public int getMarks() { return this.marks; }
    public String getRole() { return "UnderGraduate"; }

    @Override
    public String toString() {
        return this.getId() + " " + this.getName() + " " + this.marks + " " + getRole();
    }
}

class GraduateStudent extends Student {
    private String area;

    public GraduateStudent(String name, String id, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }

    @Override
    public String getRole() {
        return "Graduate (" + this.area + ")";
    }

    @Override
    public String toString() {
        return super.getId() + " " + super.getName() + " " + super.getMarks() + " " + this.getRole();
    }
}

class Repository<T> {
    private Map<String, T> map = new HashMap<>();

    public void save(String key, T value) {
        map.put(key, value);
    }
    public T findOne(String key) { return map.get(key); }
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        map.forEach((k,v) -> list.add(v));
        return list;
    }
    public void delete(String key) { map.remove(key); }
}

class Teacher extends Person {
    private String subject;
    public Teacher(String id, String name, String subject) {
        super(id, name);
        this.subject = subject;
    }
    public String getSubject() { return this.subject; }

    @Override
    public String toString() {
        return this.getId() + " " + this.getName() + " " + this.subject;
    }

    public void getReport(Repository<Student> studentRepository) {
        System.out.println();
        System.out.println("ALL STUDENTS REPORT: ");
        List<Student> students = studentRepository.findAll();
        students.forEach(System.out::println);
    }

    public void getReport(Repository<Student> studentRepository, String id) {
        System.out.println();
        System.out.println("STUDENT REPORT: ");
        Student s = studentRepository.findOne(id);
        System.out.println(s != null ? s : "Not Found");
    }
}

public class Day3_StudentManagementSystem {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("23bcs14148", "Punit", 100));
        list.add(new Student("23bcs13840", "Mansi", 99));
        list.add(new Student("23bcs12714", "Vansh", 95));
        list.add(new GraduateStudent("23bcs13909", "Aryan", 90, "Computer Science"));

        Repository<Student> studentRepository = new Repository<>();
        for(Student student : list) studentRepository.save(student.getId(), student);

        System.out.println("ALL: ");
        list.forEach(System.out::println);

        System.out.println("Look up for a Student with ID: " + "23bcs14148");
        Student studentData =  studentRepository.findOne("23bcs14148");
        System.out.println(studentData != null ? studentData.toString() : "not found");

        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if(student.getMarks() < 95) {
                iterator.remove();
                studentRepository.delete(student.getId());
            }
        }

        System.out.println("After Removal: ");
        list.forEach(System.out::println);

        Teacher teacher = new Teacher("e12345", "Suraj", "Full Stack");
        teacher.getReport(studentRepository);

        teacher.getReport(studentRepository, "23bcs12714");
    }
}
