import DoubleLinkedList.DoubleLinkedList;
import DoubleLinkedList.Position;
import ListResources.Value;

public class TestDoubleLinkedList {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        initDoubleLinkedList(doubleLinkedList);
        System.out.println("Начальный список: ");
        doubleLinkedList.printList();
        delete_dublicate(doubleLinkedList);
        System.out.println("После удаления: ");
        doubleLinkedList.printList();
    }
    static void initDoubleLinkedList(DoubleLinkedList doubleLinkedList) {
        doubleLinkedList.insert(new Value("Name 1", "Address 1 Address 1 Address 1 Address 1 Address 1 Address 1 Address 1"), doubleLinkedList.first());
        doubleLinkedList.insert(new Value("Name 1", "Address 3"), doubleLinkedList.end());
        doubleLinkedList.insert(new Value("Name 2", "Address 2"), doubleLinkedList.end());
        doubleLinkedList.insert(new Value("Name 2", "Address 2"), doubleLinkedList.end());
        doubleLinkedList.insert(new Value("Name 1", "Address 1"), doubleLinkedList.end());
        doubleLinkedList.insert(new Value("Name 1", "Address 1"), doubleLinkedList.end());
        doubleLinkedList.insert(new Value("Name 4", "Address 4"), doubleLinkedList.next(doubleLinkedList.first()));
    }
    static void delete_dublicate(DoubleLinkedList doubleLinkedList){
        Position cur = doubleLinkedList.first();
        while(!cur.equals(doubleLinkedList.end())){
            Position next = doubleLinkedList.next(cur);
            while(!next.equals(doubleLinkedList.end())){
                if(doubleLinkedList.retrieve(cur).equals(doubleLinkedList.retrieve(next))){
                    doubleLinkedList.delete(next);
                    next = doubleLinkedList.next(cur);
                }
                else next = doubleLinkedList.next(next);
            }
            cur = doubleLinkedList.next(cur);
        }
    }
}
