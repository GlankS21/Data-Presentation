package ManyToMany;

public class Course extends LinkTarget{
    int id;
    RegistrationRecord firstLink;
    public Course(int id){
        this.id = id;
        firstLink = null;
    }
    @Override
    boolean isRegistration() {
        return false;
    }
}