package StackArray;
public class Stack implements IStack {
    private char[] stack;
    private int up;
    public Stack(){
        stack = new char[50];
        up = -1;
    }
    public void MAKENULL(){
        up = -1;
    }
    public char TOP(){
        return stack[up];
    }
    public char POP(){
       return stack[up--];
    }
    public void PUSH(char x){
        stack[++up] = x;
    }
    public boolean EMPTY(){
        return up == -1;
    }
    public boolean FULL(){
        return up == stack.length - 1;
    }
}
