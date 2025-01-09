package List.CursorList;

import Resources.Value;

public class Item {
    private Value value;
    private int next = -1;
    public Item(Value value, int next) {
        this.value = new Value(value.getName(),value.getAddress());
        this.next = next;
    }
    public Item(int next){
        this.next = next;
    }
    public int getNext(){return next;}
    public void setNext(int next){ this.next = next;}
    public Value getValue() {return value;}
    public void setValue(Value value) { this.value = value;}
    public void setItem(Value value, int next){
        this.value = value;
        this.next = next;
    }

}
