package ManyToMany;

public class RegistrationRecord extends LinkTarget {
    public LinkTarget student;
    public LinkTarget course;

    public RegistrationRecord(LinkTarget student, LinkTarget course){
        this.student = student;
        this.course = course;
    }
    public boolean isRegistration() {
        return true;
    }
}