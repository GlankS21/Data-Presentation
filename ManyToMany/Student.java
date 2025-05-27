package ManyToMany;

public class Student extends LinkTarget {
    char[] name;
    RegistrationRecord firstLink;
    Student(char[] name) {
        this.name = new char[name.length];
        for(int i = 0; i < name.length; i++){
            this.name[i] = name[i];
        }
        firstLink = null;
    }
    @Override
    boolean isRegistration() {
        return false;
    }
}