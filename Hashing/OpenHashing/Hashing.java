package Hashing.OpenHashing;
import Hashing.IHashing;
public class Hashing implements IHashing {
    private static class Obj{
        char[] name;
        Obj next;
        public Obj(){
            name = null;
            next = null;
        }
        public Obj(char[] name, Obj next){
            this.name = new char[10];
            for(int i = 0; i < name.length; i++){
                this.name[i] = name[i];
            }
            this.next = next;
        }
        public void setName(char[] name){
            if(name == null){
                this.name = null;
                return;
            }
            for(int i = 0; i < name.length; i++){
                this.name[i] = name[i];
            }
        }
        public boolean compareCharArrays(char[] name) {
            if(this.name == null || name.length > 10) return false;
            if(name.length < 10 && this.name[name.length] != '\0') return false; // Вариант "name1" && "name11"
            for (int i = 0; i < name.length; i++){
                if (this.name[i] != name[i]) return false;
            }
            return true;
        }
    }

    public Obj[] arr;
    private final int size = 10;

    public Hashing(){
        arr = new Obj[size];
        for(int i = 0; i < size; i++){
            arr[i] = new Obj();
        }
    }
    @Override
    public void INSERT(char[] name) {
        int index = HashFunction(name);
        if(arr[index].name == null) {
            arr[index].name = new char[size];
            arr[index].setName(name);
            return;
        }
        if(Prev(name, index) == null) {
            arr[index].next = new Obj(name, arr[index].next);
        }
    }
    @Override
    public void DELETE(char[] name) {
        int index = HashFunction(name);
        if (arr[index] == null) return;

        if (arr[index].compareCharArrays(name)) {
            if (arr[index].next == null) {
                arr[index].name = null;
            } else {
                arr[index].setName(arr[index].next.name);
                arr[index].next = arr[index].next.next;
            }
            return;
        }

        Obj prev = Prev(name, index);
        if (prev != null && prev.next != null) {
            prev.next = prev.next.next;
        }
    }
    @Override
    public boolean MEMBER(char[] name) {
        int index = HashFunction(name);
        if(arr[index] == null) return false;
        if(arr[index].compareCharArrays(name)) return true;
        return Prev(name, index) != null;
    }
    @Override
    public void MAKENULL(){
        for (Obj obj : arr) {
            obj.setName(null);
            obj.next = null;
        }
    }
    private int HashFunction(char[] name){
        int sum = 0;
        for (char c : name) {
            sum += c;
        }
        return sum % arr.length;
    }
    private Obj Prev(char[] name, int index){
        Obj Current = arr[index];
        Obj Prev = null;
        while (Current != null){
            if (Current.compareCharArrays(name)) {
                return Prev;
            }
            Prev = Current;
            Current = Current.next;
        }
        return null;
    }

    public void print() {
        System.out.println("----------------");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Index " + i + ": ");
            if (arr[i] == null || arr[i].name == null) System.out.println();
            else {
                System.out.print(ArrayToString(arr[i].name));
                Obj current = arr[i].next;
                while (current != null) {
                    System.out.print(" -> " + ArrayToString(current.name));
                    current = current.next;
                }
                System.out.println();
            }
        }
        System.out.println("----------------");
    }
    private String ArrayToString(char[] name){
        String result = "";
        for(int i = 0; i < name.length && name[i] != '\0'; i++){
            result += name[i];
        }
        return result;
    }
}