package StackLinked;
import Resources.*;
public class Stack implements IStack{
    private class Item{
        char value;
        Item next;
        public Item(char value, Item next){
            this.value = value;
            this.next = next;
        }
    }
    private Item head;
    public Stack(){
        head = null;
    }
    public void MAKENULL(){
        head = null;
    }
    public char TOP(){
        return head.value;
    }
    public char POP(){
        char result = head.value;
        head = head.next;
        return result;
    }
    public void PUSH(char value){
        head = new Item(value, head);
    }
    public boolean EMPTY(){
        return head == null;
    }
    public boolean FULL(){
        return false;
    }
}
