package StackLinked;
import Resources.Value;
public interface IStack {
    void MAKENULL(); // делает стек пустым
    char TOP(); // возвращает элемент (копия) из вершины стека S.
    char POP(); // удаляет элемент из вершины стека S, и возвращает его в качестве результатата.
    void PUSH(char x); // вставляет элемент x в вершину стека S.
    boolean EMPTY(); // возвращает значение true, если стек S пустой, и значение false в противном случае.
    boolean FULL(); // возвращает значение true, если стек S полный, и значение false в противном случае.
}
