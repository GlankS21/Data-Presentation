package Map.MapLinked;
import Resources.*;
import Map.IMap;
public class Map implements IMap{
    private class Item{
        Value value;
        Item next;
        public Item(char[] name, char[] address, Item next){
            value = new Value(name, address);
            this.next = next;
        }
        public void setValue(char[] r) {
            this.value.setAddress(r);
        }
    }
    private Item head;
    public Map() {
        head = null;
    }
    public void MAKENULL() {
        head = null;
    }
    public void ASSIGN(char[] d, char[] r) {
        if (head == null) {
            head = new Item(d, r, null);
            return;
        }
        Item temp = FindByKey(d);
        if(temp != null){
            temp.setValue(r);
            return;
        }
        head = new Item(d, r, head);
    }
    public boolean COMPUTE(char[] d, char[] r) {
        Item temp = FindByKey(d);
        if (temp == null) return false;
        for(int i = 0; i < r.length && temp.value.getAddress()[i] != '\0'; i++){
            r[i] = temp.value.getAddress()[i];
        }
        return true;
//        Item temp = FindByKey(d);
//        if (temp == null) return false;
//        temp.setValue(r);
//        return true;
    }
    public void print() {
        Item current = head;
        while (current != null) {
            current.value.print();
            current = current.next;
        }
    }
    private Item FindByKey(char[] d) {
        Item key = head;
        while (key != null) {
            if (Value.CompareCharArray(key.value.getName(), d))
                return key;
            key = key.next;
        }
        return null;
    }
}
