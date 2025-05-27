package Set;

public interface ISet {
    Set INTERSECTION(Set B);
    Set UNION(Set B); // returns the union of sets A and B.
    Set DIFFERENCE(Set B);
    Set MERGE(Set B);
    void MAKENULL();
    Set FIND(Set B, int x);
    boolean MEMBER(int x);
    void INSERT(int x);
    void DELETE(int x);
    void ASSIGN(Set B);
    int MIN();
    int MAX();
    boolean EQUAL(Set B);
}
