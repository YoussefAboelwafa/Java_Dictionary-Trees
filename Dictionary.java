import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Dictionary {
    Tree d;
    int size = 0;

    Dictionary(int type) {
        if (type == 1) {
            d = new AVL<>();
        } else if (type == 2) {
            d = new RB<>();
        }
    }

    public void insert(String toInsert) {
        if (d.contains(toInsert)) {
            System.out.print("(" + toInsert + ")" + "\u001B[31mAlready EXIST\n\u001B[0m");
        } else {
            d.insert(toInsert);
            System.out.print("(" + toInsert + ")" + "\u001B[32m Succefully inserted ✅\n\u001B[0m");
            size++;
        }
    }

    public void delete(String toDelete) {
        if (!d.contains(toDelete)) {
            System.out.print("(" + toDelete + ")" + "\u001B[31mword NOT FOUND\n\u001B[0m");
        } else {
            d.delete(toDelete);
            System.out.print("(" + toDelete + ")" + "\u001B[32m Succefully DELETED ✅\n\u001B[0m");
            size--;
        }
    }

    public Boolean search(String toSearch) {
        if (d.contains(toSearch)) {
            System.out.println("\u001B[32mWord FOUND\u001B[0m ✅");
        } else {
            System.out.println("\u001B[31mWord NOT FOUND\u001B[0m ❌");
        }
        return d.contains(toSearch);
    }

    public void batch_insert(String path) {
        try {
            int Inserted = 0;
            int notInserted = 0;
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (d.contains(data)) {
                    notInserted++;
                } else {
                    d.insert(data);
                    Inserted++;
                    size++;
                }
            }
            myReader.close();
            System.out.print("(" + Inserted + ")" + "\u001B[32m words SUCCEFULLY INSERTED ✅\n\u001B[0m");
            if (notInserted != 0) {
                System.out.print("(" + notInserted + ")" + "\u001B[31m words ALREADY EXIST \n\u001B[0m");
            }

        } catch (FileNotFoundException e) {
            System.out.println("\u001B[31mAn ERROR occurred opening file\u001B[0m ");
        }
    }

    public void batch_delete(String path) {
        try {
            int Found = 0;
            int notFOUND = 0;
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (d.contains(data)) {
                    d.delete(data);
                    Found++;
                } else {
                    notFOUND++;
                }
            }
            myReader.close();
            System.out.print("(" + Found + ")" + "\u001B[32m words SUCCEFULLY DELETED ✅\n\u001B[0m");
            if (notFOUND != 0) {
                System.out.print("(" + notFOUND + ")" + "\u001B[31m words NOT FOUND \n\u001B[0m");
            }
        } catch (FileNotFoundException e) {
            System.out.println("\u001B[31mAn ERROR occurred opening file\u001B[0m ");
        }

    }

    public void traverse() {

        if (d.isEmpty()) {
            System.out.print("\u001B[31mNothing to show, dictionary is EMPTY\n\u001B[0m");
        } else d.traverse();
    }

    public int getSize() {

        return size;
    }

    // TODO
    public int getHeight() {
        return getHeight();
    }

}
