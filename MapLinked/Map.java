package MapLinked;

import java.util.Arrays;
import Resources.*;
public class Map {
    private class Item{
        Value value;
        Item next;
        public Item(char[] name, char[] address, Item next){
            value = new Value(name, address);
            this.next = next;
        }
        public void setValue(char[] r) {
            this.value = new Value(this.value.getName(), r);
        }
    }

    private Item head;
    public Map() {
        head = null;
    }

    public void makeNull() {
        head = null;
    }
    public void assign(char[] d, char[] r) {
        if (head == null) {
            head = new Item(d, r, null);
            return;
        }
        Item temp = head;
        while (temp != null){
            if (Arrays.equals(temp.value.getName(), d)){
                temp.setValue(r);
                return;
            }
            temp = temp.next;
        }
        if (head.next == null){
            head.next = new Item(d, r, null);
            return;
        }
        temp = head.next;
        head.next = new Item(d, r, temp);
    }

    public boolean compute(char[] d, char[] r) {
        if (head == null) return false;
        Item temp = head;
        while (temp != null){
            if (temp.value.equals(new Value(temp.value.getName(), d))){
                temp.setValue(r);
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

}
