package Stack;
import Stack.StackLinked.*;
public class TestStack {
    public static void main(String[] args){
        String s = "abcdefgh";
        char[] symbols = s.toCharArray();
        Stack stack = new Stack();
        System.out.print("Заносим символы в стек: ");
        for (char c : symbols) {
            if (stack.FULL()) break;
            System.out.print(c);
            stack.PUSH(c);
        }
        System.out.println();
        System.out.print("Вывесем на экран все символы: ");
        while (!stack.EMPTY()) {
            System.out.print(stack.POP());
        }
    }
}
