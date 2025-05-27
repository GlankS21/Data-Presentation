package Hashing;

import Hashing.OpenHashing.Hashing;

import java.util.Scanner;

public class TestHashing {
    public static void main(String[] args) {
//        HashTable();
        Hashing hash = new Hashing();
        hash.INSERT("abb".toCharArray());
        hash.INSERT("aac".toCharArray());
        hash.INSERT("abc".toCharArray());
        hash.print();
        hash.DELETE("abb".toCharArray());
        hash.DELETE("aac".toCharArray());
        hash.INSERT("aac".toCharArray());
        hash.INSERT("abb".toCharArray());
        hash.print();
    }

    static void HashTable(){
        Hashing hashTable = new Hashing();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите функцию:");
            System.out.println("1) INSERT    2) MEMBER    3) DELETE    4) MAKENULL    5) PRINT    6) EXIT");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите имена (разделенные запятой): ");
                    String insertNames = scanner.nextLine();
                    String[] namesArray = insertNames.split("\\s*,\\s*");
                    for (String name : namesArray) {
                        hashTable.INSERT(name.toCharArray());
                    }
                    break;
                case 2:
                    System.out.print("name: ");
                    String checkName = scanner.nextLine();
                    System.out.println("Существует ли имя? " + hashTable.MEMBER(checkName.toCharArray()));
                    break;
                case 3:
                    System.out.print("name: ");
                    String deleteName = scanner.nextLine();
                    hashTable.DELETE(deleteName.toCharArray());
                    break;
                case 4:
                    hashTable.MAKENULL();
                    break;
                case 5:
                    hashTable.print();
                    break;
                case 6: scanner.close();
                    return;
                default: System.out.println("Выберите еще раз");
            }
        }
    }
}
