import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\u001B[40mEnter the type of the backend tree of the dictionary : " + "\u001B[0m");
        System.out.print("\u001B[33m1)AVL Tree\n2)Red Black Tree\u001B[0m\nAnswer >> ");
        String D_type;
        Dictionary d;
        D_type = scanner.next();
        while (true) {
            try {
                if (Integer.parseInt(D_type) == 1 || Integer.parseInt(D_type) == 2) {
                    break;
                } else {
                    System.out.print("\u001B[31mError,Please choose a right answer\n\u001B[0mAnswer >> ");
                    D_type = scanner.next();
                }

            } catch (Exception e) {
                System.out.print("\u001B[31mError,Please choose a right answer\n\u001B[0mAnswer >> ");
                D_type = scanner.next();
            }
        }
        d = new Dictionary(Integer.parseInt(D_type));

        options(d);
    }

    static void options(Dictionary d) {
        while (true) {
            System.out.println("-------------------------------------------------------------" + "\n\u001B[40mDictionary options : \u001B[0m");
            System.out.println("\u001B[33m1)Show dictionary\u001B[0m");
            System.out.println("\u001B[33m2)Insert word\u001B[0m");
            System.out.println("\u001B[33m3)Insert batch\u001B[0m");
            System.out.println("\u001B[33m4)Delete word\u001B[0m");
            System.out.println("\u001B[33m5)Delete batch\u001B[0m");
            System.out.println("\u001B[33m6)Search word\u001B[0m");
            System.out.println("\u001B[33m7)Get size of dictionary\u001B[0m");
            System.out.println("\u001B[33m8)Get height of the tree\u001B[0m");
            System.out.print("Choose option>> ");
            Scanner scanner = new Scanner(System.in);
            try {
                int option = scanner.nextInt();

                //Bounded options
                if (option <= 0 || option > 8) {
                    System.out.print("\u001B[31mError,Please choose a right option\n\u001B[0m");
                    options(d);
                }

                //SHOW DICTIONARY
                if (option == 1) {
                    d.traverse();
                }

                //INSERT WORD
                else if (option == 2) {
                    System.out.print("Enter the word to insert >> ");
                    String toInsert = scanner.next();
                    d.insert(toInsert);
                }

                //BATCH INSERT
                else if (option == 3) {
                    System.out.print("Enter the path of the file to insert >> ");
                    String fileToInsert = scanner.next();
                    d.batch_insert(fileToInsert);
                }

                //DELETE WORD
                else if (option == 4) {
                    System.out.print("Enter the word to delete >> ");
                    String toDelete = scanner.next();
                    d.delete(toDelete);
                }

                //BATCH DELETE
                else if (option == 5) {
                    System.out.print("Enter the path of the file to delete >> ");
                    String fileToDelete = scanner.next();
                    d.batch_delete(fileToDelete);
                }

                //SEARCH
                else if (option == 6) {
                    System.out.print("Enter the word to search >> ");
                    String toSearch = scanner.next();
                    d.search(toSearch);
                }

                //SIZE
                else if (option == 7) {
                    System.out.println("\u001B[34mDictionary size = " + "(\u001B[0m" + d.getSize() + "\u001B[34m)\u001B[0m");
                }

                //HEIGHT
                else if (option == 8) {
                    System.out.println("\u001B[34mDictionary tree height = " + "(\u001B[0m" + d.getHeight() + "\u001B[34m)\u001B[0m");
                }
            } catch (InputMismatchException e) {
                System.out.print("\u001B[31mError,Please choose a right option\n\u001B[0m");
            }
        }
    }
}