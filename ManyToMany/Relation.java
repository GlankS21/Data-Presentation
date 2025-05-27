package ManyToMany;

public class Relation {
    public StudentHash students;
    public CourseHash courses;
    public Relation() {
        students = new StudentHash();
        courses = new CourseHash();
    }

    /**
     *  1. Найдем студент и курс
     *  2. Если все найдены => Проверка существует ли студент в курсе
     *  3. Создаем новую запись о регистрации студента на курс
     *      если у студента нет связей => на прямо со студентом
     *      Иначе в начале
     *      если у курса нет связей => на прямо со курсом
     *      Иначе в начале
     */
    public void addStudentToCourse(char[] student, int courseId){
        Student s = students.FindStudent(student);
        if (s == null) throw new RuntimeException("Студент не найден");
        Course c = courses.FindCourse(courseId);
        if (c == null) throw new RuntimeException("Курс не найден");

        if(getRegistration(s, c) != null){
            System.out.println("Студент уже записан на курсе");
            return;
        }

        RegistrationRecord register = new RegistrationRecord(null, null);
        register.student = s.firstLink == null ? s : s.firstLink;
        s.firstLink = register;
        register.course = c.firstLink == null ? c : c.firstLink;
        c.firstLink = register;
    }

    /**
     *  1. Найдем студент и курс
     *  2. начинаем проверку регистраций студента
     *      2.1. Идем до получения Course
     *      2.2. Если найден нужный курс
     *          2.2.1 Получаем регистрации между студентом и курсом
     *          2.2.2 Удаляем регистрацию со стороны студента / курса
     *
     */
    public void removeStudentFromCourse(char[] student, int courseId){
        // Найдем студент и курс
        Student s = students.FindStudent(student);
        if (s == null) {
            System.out.println("Студент не найден");
            return;
        }
        Course c = courses.FindCourse(courseId);
        if (c == null) {
            System.out.println("Курс не найден");
            return;
        }
        // Если все верны, посмотрим список курса у студента
        LinkTarget record = s.firstLink;
        while (record.isRegistration()) {
            LinkTarget course = record;
            while (course.isRegistration()){
                course = ((RegistrationRecord) course).course;
            }
            // Если найден курс => удалим его
            if (course == c){
                LinkTarget register = getRegistration(s,c);
                removeRegistration(register, s);
                removeRegistration(register, c);
                return;
            }
            record = ((RegistrationRecord) record).student;
        }
        System.out.println("Cтудент не является участником курса");
    }

    /**
     *  1. Найден студент
     *  2. Получаем его первую регистрацию на курсы
     *  3. начинаем проверку регистраций студента
     *      3.1. Идем до получения Course
     *      3.2. Удаляем регистрацию со стороны студента / курса
     *      3.3. Переходим на следующий
     */
    public void removeStudent(char[] name) {
        Student s = students.FindStudent(name);
        if (s == null) {
            System.out.println("Студент не найден");
            return;
        }
        LinkTarget courses = s.firstLink;
        if (courses == null) {
            System.out.println("Студент никуда не записан");
            return;
        }

        while (courses.isRegistration()) {
            LinkTarget course = courses;
            while (course.isRegistration())
                course = ((RegistrationRecord) course).course; // Найдем курс
            removeRegistration(courses, (Course) course); // удалим его
            removeRegistration(courses, s);
            courses = ((RegistrationRecord) courses).student; // Переходим на следующий курс
        }
    }

    /**
     *  1. Найден курс
     *  2. Получаем первую регистрацию студентов
     *  3. Проходим по всем регистрациям курса
     *      3.1. Идем до получения student
     *      3.2. Удаляем регистрацию со стороны студента / курса
     *      3.3. Переходим на следующий
     */
    public void removeCourse(int courseId) {
        Course c = courses.FindCourse(courseId);
        if (c == null) {
            System.out.println("Курс не найден");
            return;
        }
        LinkTarget students = c.firstLink;
        if (students == null) {
            System.out.println("На курсе «" + c.id + "» нет студентов");
            return;
        }

        while (students.isRegistration()) {
            LinkTarget student = students;
            while (student.isRegistration())
                student = ((RegistrationRecord) student).student; // Найдем студент
            removeRegistration(students, (Student) student);
            removeRegistration(students, c);
            students = ((RegistrationRecord) students).course; // Переходим на следующий студент
        }
    }

    /**
     * 1. Найдем курс
     * 2. Получаем первую регистрации студентов
     * 3. Проходим по всем регистрациям курса
     *  3.1 Отобразим на экране
     *  3.2 Переходим на следующий
     */
    public void printStudentsOfCourse(int courseId){
        Course c = courses.FindCourse(courseId);
        if (c == null) {
            System.out.println("Курс не найден");
            return;
        }
        LinkTarget students = c.firstLink;
        if (students == null) {
            System.out.println("На курсе «" + c.id + "» нет студентов");
            return;
        }
        System.out.println("Студенты курса «" + c.id + "»: ");
        while (students.isRegistration()) {
            LinkTarget student = students;
            while (student.isRegistration())
                student = ((RegistrationRecord) student).student;
            System.out.println(((Student) student).name);
            students = ((RegistrationRecord) students).course;
        }
        System.out.println();
    }
    public void printCoursesOfStudent(char[] name){
        Student s = students.FindStudent(name);
        if (s == null) {
            System.out.println("Студент не найден");
            return;
        }
        LinkTarget courses = s.firstLink;
        if (courses == null) {
            System.out.println("Студент никуда не записан");
            return;
        }
        System.out.println("Курсы " + new String(name) + ": ");
        while (courses.isRegistration()) {
            LinkTarget course = courses;
            while (course.isRegistration())
                course = ((RegistrationRecord) course).course;
            System.out.println(((Course) course).id);
            courses = ((RegistrationRecord) courses).student;
        }
        System.out.println();
    }
    private String printArray(char[] input){
        StringBuilder result = new StringBuilder();
        for (char c : input) result.append(c);
        return result.toString();
    }
    // Найдем регистрацию (student s, course c)
    private LinkTarget getRegistration(Student s, Course c) {
        LinkTarget register = s.firstLink;
        while (register != null && register.isRegistration()) {
            LinkTarget course = register;
            while (course.isRegistration()){
                course = ((RegistrationRecord) course).course;
            }
            if (course == c) return register;
            register = ((RegistrationRecord) register).student;
        }
        return null;
    }
    private RegistrationRecord getPreviousRegister(LinkTarget register, Student s) {
        LinkTarget current = s.firstLink;
        LinkTarget prev = null;
        while (current.isRegistration()) {
            if (current == register) return (RegistrationRecord) prev;
            prev = current;
            current = ((RegistrationRecord) current).student;
        }
        return (RegistrationRecord) prev;
    }
    private RegistrationRecord getPreviousRegister(LinkTarget register, Course c) {
        LinkTarget current = c.firstLink;
        LinkTarget prev = null;
        while (current.isRegistration()) {
            if (current == register) return (RegistrationRecord) prev;
            prev = current;
            current = ((RegistrationRecord) current).course;
        }
        return (RegistrationRecord) prev;
    }
    private void removeRegistration(LinkTarget register, Student student) {
        if (student.firstLink == register) { // удалим первую регистрацию
            if (student.firstLink.student.isRegistration())
                student.firstLink = (RegistrationRecord) student.firstLink.student;
            else student.firstLink = null;
        }
        else {
            RegistrationRecord prev = getPreviousRegister(register, student);
            prev.student = ((RegistrationRecord) prev.student).student;
        }
    }
    private void removeRegistration(LinkTarget register, Course course) {
        if (course.firstLink == register) {
            if (course.firstLink.course.isRegistration())
                course.firstLink = (RegistrationRecord) course.firstLink.course;
            else course.firstLink = null;
        }
        else {
            RegistrationRecord prev = getPreviousRegister(register, course);
            prev.course = ((RegistrationRecord) prev.course).course;
        }
    }
}
