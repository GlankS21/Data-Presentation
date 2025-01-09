package List;
import List.LinkedList.*;
import Resources.Value;
public class TestList {
    public static void main(String[] args) {
        List List = new List();
        initList(List);
        System.out.println("Начальный список: ");
        List.printList();
        delete_dublicate(List);
        System.out.println("После удаления: ");
        List.printList();
    }
    static void initList(List List) {
        List.insert(new Value("Name 1", "Address 3"), List.end());
        List.insert(new Value("Name 2", "Address 2"), List.end());
        List.insert(new Value("Name 2", "Address 2"), List.end());
        List.insert(new Value("Name 1", "Address 1"), List.end());
        List.insert(new Value("Name 4", "Address 4"), List.next(List.first()));
        List.insert(new Value("Name 1", "Address 1"), List.end());
    }
    static void delete_dublicate(List list){
        Position cur = list.first();
        while(!cur.equals(list.end())){
            Position next = list.next(cur);
            while(!next.equals(list.end())){
                if(list.retrieve(cur).equals(list.retrieve(next))){
                    list.delete(next);
                    next = list.next(cur);
                }
                else next = list.next(next);
            }
            cur = list.next(cur);
        }
    }
}