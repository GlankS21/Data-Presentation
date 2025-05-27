package ManyToMany;
public class StudentHash {
    private static final char[] USED = {0};
    private final int SIZE = 10;
    public Student[] students;
    private int length = 0;
    public StudentHash() {
        students = new Student[SIZE];
    }
    public void INSERT(char[] name) {
        if (!hasSpace()) throw new RuntimeException("Нет места для вставки");
        int i = 0;
        int hash = hash(name);
        int start = HashFunction(hash, i);
        int index = start;
        int firstDeleted = -1;
        while (students[index] != null) {
            if (compareCharArrays(name, students[index].name)) return;
            if (firstDeleted == -1 && compareCharArrays(students[index].name, USED)){ // Если место свободно
                firstDeleted = index;
            }
            index = HashFunction(hash, ++i);
            if (index == start) {
                if (firstDeleted != -1) students[firstDeleted] = new Student(name);
                return;
            }
        }
        if (firstDeleted != -1) students[firstDeleted] = new Student(name);
        else students[index] = new Student(name);
        length++;
    }
    public void DELETE(char[] name) {
        int index = find(name);
        if (index != -1) {
            students[index].name = USED;
            length--;
        }
    }
    public Student FindStudent(char[] name) {
        int index = find(name);
        if (index != -1) return students[index];
        return null;
    }
    public void MAKENULL() {
        for (int i = 0; i < students.length; i++)
            students[i] = null;
    }
    public int length(){
        return length;
    }
    public void print() {
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + ": ");
            if (students[i] == null) {
                System.out.println("null");
            }
            else if (students[i].name == USED) {
                System.out.println("USED");
            }
            else {
                for (int j = 0; j < students[i].name.length; j++) {
                    if(students[i].name[j] != '\0') {
                        System.out.print(students[i].name[j]);
                    }
                }
                System.out.println();
            }
        }
    }
    private int hash(char[] arr) {
        int sum = 0;
        for (char c : arr) sum += c;
        return sum;
    }
    private int HashFunction(int hash, int i) {
        return (hash + i) % students.length;
    }
    private boolean hasSpace() {
        return length != SIZE;
    }
    private boolean compareCharArrays(char[] arr1, char[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }
    private int find(char[] name) {
        int i = 0;
        int hash = hash(name);
        int start = HashFunction(hash, i);
        int index = start;
        while (students[index] != null) {
            if (compareCharArrays(students[index].name, name)) return index;
            index = HashFunction(hash, ++i);
            if (index == start) return -1;
        }
        return -1;
    }
}
