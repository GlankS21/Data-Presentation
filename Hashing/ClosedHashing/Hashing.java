package Hashing.ClosedHashing;
import Hashing.IHashing;

public class Hashing implements IHashing {
    private final char[][] arr;
    private final int size = 10;
    private static final char[] USED = {0};
    public Hashing() {
        arr = new char[size][];
    }
    @Override
    public void INSERT(char[] name) {
        int i = 0;
        int hash = hash(name);
        int start = HashFunction(hash, i);
        int index = start;
        int firstDeleted = -1;
        while (arr[index] != null) {
            if (compareCharArrays(name, arr[index])) return;
            if (firstDeleted == -1 && compareCharArrays(arr[index], USED)){ // Если место свободно
                firstDeleted = index;
            }
            index = HashFunction(hash, ++i);
            if (index == start) {
                if (firstDeleted != -1) arr[firstDeleted] = name;
                return;
            }
        }
        if (firstDeleted != -1) arr[firstDeleted] = name;
        else arr[index] = name;
    }
    @Override
    public void DELETE(char[] name) {
        int index = find(name);
        if(index != -1) arr[index] = USED;
    }

    @Override
    public void MAKENULL() {
        for (int i = 0; i < arr.length; i++)
            arr[i] = null;
    }
    @Override
    public boolean MEMBER(char[] name) {
        if(find(name) != -1) return true;
        return false;
    }
    private boolean compareCharArrays(char[] arr1, char[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }
    private int HashFunction(int hash, int i) {
        return (hash + i) % arr.length;
    }
    private int hash(char[] name) {
        int sum = 0;
        for (char c : name) {
            sum += c;
        }
        return sum;
    }

    public void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Index " + i + ": ");
            if (arr[i] == null) {
                System.out.println();
            } else if (compareCharArrays(arr[i], USED)) {
                System.out.println("<deleted>");
            } else {
                System.out.println(new String(arr[i]));
            }
        }
        System.out.println("----------------");
    }
    private int find(char[] name){
        int i = 0;
        int hash = hash(name);
        int start = HashFunction(hash, i);
        int index = start;

        while (arr[index] != null) {
            if (compareCharArrays(arr[index], name)) return index;
            index = HashFunction(hash, ++i);
            if (index == start) return -1;
        }
        return -1;
    }
}