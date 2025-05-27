package Set;

import java.util.Scanner;

public class TestSet {
    public static void main(String[] args) {

//        Set setA = new Set();
//        setA.INSERT(1);
//        setA.INSERT(2);
//        setA.INSERT(7);
//
//        Set setB = new Set();
//        setB.INSERT(4);
//        setB.INSERT(5);
//        setB.INSERT(6);
//
//        setA.print();
//        setB.print();
//
//
//        Set union = setA.UNION(setB);
//        union.print();


        Scanner scanner = new Scanner(System.in);
        SetMenu(scanner);
    }

    private static void SetMenu(Scanner scanner) {
        Set setA = insertSet(scanner);
        Set setB = insertSet(scanner);
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Пересечение (A ∩ B)");
        System.out.println("2. Объединение (A ∪ B)");
        System.out.println("3. Разность (A \\ B)");
        System.out.println("4. MERGE");
        System.out.println("5. MAKENULL A");
        System.out.println("6. FIND");
        System.out.println("7. MEMBER");
        System.out.println("8. INSERT");
        System.out.println("9. DELETE");
        System.out.println("10. ASSIGN");
        System.out.println("11. MIN");
        System.out.println("12. MAX");
        System.out.println("13. EQUAL");
        System.out.println("14. Показать множество A");
        System.out.println("0. Выход");
        while (true) {
            System.out.print("Введите выбор (1-14, 0 для выхода): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Программа завершена.");
                break;
            }

            try {
                switch (choice) {
                    case 1: // INTERSECTION
                        Set intersection = setA.INTERSECTION(setB);
                        System.out.print("Результат пересечения (A ∩ B): ");
                        intersection.print();
                        break;

                    case 2: // UNION
                        Set union = setA.UNION(setB);
                        System.out.print("Результат объединения (A ∪ B): ");
                        union.print();
                        break;

                    case 3: // DIFFERENCE
                        Set difference = setA.DIFFERENCE(setB);
                        System.out.print("Результат разности (A \\ B): ");
                        difference.print();
                        break;

                    case 4: // MERGE
                        if(setA.INTERSECTION(setB).EQUAL(new Set())){
                            Set merge = setA.MERGE(setB);
                            System.out.print("Результат MERGER: ");
                            merge.print();
                        }
                        else System.out.println("A ∩ B != NULL");
                        break;

                    case 5: // MAKENULL
                        setA.MAKENULL();
                        System.out.println("Множество A очищено.");
                        System.out.print("Текущее множество A: ");
                        setA.print();
                        break;

                    case 6: // FIND
                        System.out.print("Введите число x для поиска: ");
                        int x = scanner.nextInt();
                        Set result = setA.FIND(setB, x);
                        if (result != null) {
                            System.out.print("Множество, содержащее " + x + ": ");
                            result.print();
                        } else {
                            System.out.println("Число " + x + " не найдено в обоих множествах.");
                        }
                        break;

                    case 7: // MEMBER
                        System.out.print("Введите число x для проверки: ");
                        x = scanner.nextInt();
                        System.out.println("Число x = " + x + " принадлежит множеству A: " + setA.MEMBER(x));
                        break;

                    case 8: // INSERT
                        System.out.print("Введите число x для добавления в множество A: ");
                        x = scanner.nextInt();
                        setA.INSERT(x);
                        System.out.print("Множество A после добавления: ");
                        setA.print();
                        break;

                    case 9: // DELETE
                        System.out.print("Введите число x для удаления из множества A: ");
                        x = scanner.nextInt();
                        setA.DELETE(x);
                        System.out.print("Множество A после удаления: ");
                        setA.print();
                        break;

                    case 10: // ASSIGN
                        System.out.println("Введите новое множество B для присваивания A:");
                        setB = insertSet(scanner);
                        setA.ASSIGN(setB);
                        System.out.print("Множество A после присваивания: ");
                        setA.print();
                        break;

                    case 11: // MIN
                        try {
                            System.out.println("Минимальный элемент в множестве A: " + setA.MIN());
                        } catch (SetException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 12: // MAX
                        try {
                            System.out.println("Максимальный элемент в множестве A: " + setA.MAX());
                        } catch (SetException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 13: // EQUAL
                        System.out.println("Введите новое множество B для сравнения с A:");
                        setB = insertSet(scanner);
                        System.out.println("Множество A равно множеству B: " + setA.EQUAL(setB));
                        break;

                    case 14: // Печать множества A
                        System.out.print("Текущее множество A: ");
                        setA.print();
                        break;

                    default:
                        System.out.println("Неверный выбор, пожалуйста, выберите снова!");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static Set insertSet(Scanner scanner) {
        Set set = new Set();
        System.out.print("Введите количество элементов: ");
        int n = scanner.nextInt();
        if (n > 0) {
            System.out.println("Введите элементы (через пробел):");
            for (int i = 0; i < n; i++) {
                int x = scanner.nextInt();
                set.INSERT(x);
            }
        }
        return set;
    }
}