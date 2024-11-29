import StackArray.*;
import Resources.*;
public class TestStack {
    public static void main(String[] args){
        String s = "abcdef";
        char[]symbols = s.toCharArray();
        int size = symbols.length;

        Stack stack = new Stack();
        System.out.println("Заносим символы в стек....");
        int i = 0;
        while(!stack.FULL()){
            stack.PUSH(symbols[i++]);
            if(i == size) break;
        }
        System.out.println("Вывесем на экран все символы:");
        while(!stack.EMPTY()){
            System.out.print(stack.TOP());
            stack.POP();
        }
    }

}
