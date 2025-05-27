package ManyToMany;

public class CourseHash {
    private final int SIZE = 10;
    public Course[] courses;
    private final int USED = -1;
    private int length = 0;
    public CourseHash(){
        courses = new Course[SIZE];
    }

    public void INSERT(int id) {
        if (!hasSpace()) throw new RuntimeException("Нет места для вставки");
        int i = 0;
        int start = HashFunction(id, i);
        int index = start;
        int firstDeleted = -1;
        while (courses[index] != null) {
            if (courses[index].id == id) return;
            if (firstDeleted == -1 && courses[index].id == USED){ // Если место свободно
                firstDeleted = index;
            }
            index = HashFunction(id, ++i);
            if (index == start) {
                if (firstDeleted != -1) courses[firstDeleted] = new Course(id);
                return;
            }
        }
        if (firstDeleted != -1) courses[firstDeleted] = new Course(id);
        else courses[index] = new Course(id);
        length++;
    }
    public void DELETE(int id) {
        int index = find(id);
        if (index != -1) {
            courses[index].id = USED;
            length--;
        }
    }
    public Course FindCourse(int id) {
        int index = find(id);
        if (index != -1) return courses[index];
        return null;
    }

    public void print() {
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + ": ");
            if (courses[i] == null) {
                System.out.println("null");
            } else if (courses[i].id == USED) {
                System.out.println("USED");
            } else {
                System.out.println(courses[i].id);
            }
        }
    }
    public void MAKENULL() {
        for (int i = 0; i < SIZE; i++) courses[i] = null;
    }
    public int length(){
        return length;
    }
    private int find(int id) {
        int i = 0;
        int start = HashFunction(id, i);
        int index = start;
        while (courses[index] != null) {
            if (courses[index].id == id) return index;
            index = HashFunction(id, ++i);
            if (index == start) return -1;
        }
        return -1;
    }
    private int HashFunction(int hash, int i) {
        return (hash + i) % courses.length;
    }
    private boolean hasSpace(){
        return length != SIZE;
    }
}