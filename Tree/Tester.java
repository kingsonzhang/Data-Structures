import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Tester{
    public static void main(String args[]){
        BST<Student> students = new BST<>();
        try{
            Scanner reader = new Scanner(new File("./Tree/tree-input.txt"));
            while (reader.hasNext()){
                String data = reader.nextLine();
                if (data.substring(0, 1).equals("I"))
                    students.insert(new Student(data.substring(1)));
                else if (data.substring(0, 1).equals("D"))
                    students.deleteNode(new Student(data.substring(1)));
            }
            reader.close();
            students.printDFS();
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}