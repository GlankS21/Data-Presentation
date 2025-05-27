package ManyToMany;

public class Test {
    public static void main(String[] args) {
        Relation s = new Relation();
        String[][] data = {
                {"Аня", "1"},
                {"Аня", "2"},
                {"Вася", "1"},
                {"Вася", "2"},
                {"Дима", "1"},
        };
        for (String[] d : data) {
            char[] studentName = d[0].toCharArray();
            int courseId = Integer.parseInt(d[1]);
            s.students.INSERT(studentName);
            s.courses.INSERT(courseId);
            s.addStudentToCourse(studentName, courseId);
        }
        s.courses.INSERT(3);
        s.courses.INSERT(4);
        s.printStudentsOfCourse(4);

        s.printStudentsOfCourse(1);
        s.printStudentsOfCourse(2);

        s.printCoursesOfStudent("Вася".toCharArray());
        s.removeStudentFromCourse("Вася".toCharArray(), 1);
        s.removeStudentFromCourse("Вася".toCharArray(), 3);
        s.addStudentToCourse("Вася".toCharArray(), 1);
        s.addStudentToCourse("Вася".toCharArray(), 1);
        s.printCoursesOfStudent("Вася".toCharArray());
        s.printStudentsOfCourse(1);
        s.removeStudent("Вася".toCharArray());
        s.printCoursesOfStudent("Вася".toCharArray());
        s.printStudentsOfCourse(2);
        s.removeCourse(2);
        s.printStudentsOfCourse(2);
    }
}
