package Map.MapATD;
import Map.IMap;
import List.DoubleLinkedList.*;
import Resources.*;
public class Map implements IMap {
    private final List list;
    public Map() {list = new List();}
    @Override
    public void MAKENULL() {list.makeNull();} //делает M(d) равным r независимо от того, как M(d) было определено ранее.
    @Override
    public void ASSIGN(char[] d, char[] r) {
        // Если список пустой => добавляем первым элементом
        if (list.first().equals(list.end())) {
            list.insert(new Value(d, r), list.first());
            return;
        }
        Position temp = FindByKey(d);
        if(!temp.equals(list.end())) {
            list.retrieve(temp).setAddress(r);
            return;
        }
        // Если ключ не найден
        list.insert(new Value(d,r), list.first());
    }
    //возвращает значение true и присваивает переменной r значение M(d), если последнее определено, и возвращает false в противном случае.
    @Override
    public boolean COMPUTE(char[] d, char[] r) {
//        Position position = FindByKey(d);
//        if(position.equals(list.end()))
//            return false;
//        list.retrieve(position).setAddress(r);
//        return true;
        Position temp = FindByKey(d);
        if (temp == null) return false;
        for(int i = 0; i < r.length && temp.item.getValue().getAddress()[i] != '\0'; i++){
            r[i] = temp.item.getValue().getAddress()[i];
        }
        return true;
    }
    public void print(){
        list.printList();
    }
    private Position FindByKey(char[] key) {
        Position position = list.first();
        while (!position.equals(list.end())) {
            if (Value.CompareCharArray(key,list.retrieve(position).getName()))
                return position;
            position = list.next(position);
        }
        return position;
    }

}
