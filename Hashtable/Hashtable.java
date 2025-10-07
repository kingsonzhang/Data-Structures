import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Hashtable{
    private final int MAXSIZE = 101;
    private ArrayList<ArrayList<String>> table;
    public static void main(String args[]){
        Hashtable anagramRoots = new Hashtable();
        HeapSort heapSorter = new HeapSort();
        try (Scanner fileReader = new Scanner(new File("./Hashtable/pride-and-prejudice.txt"))){
            while (fileReader.hasNextLine()){
                String[] line = fileReader.nextLine().split("\\W+");
                for (int i = 0; i < line.length; i++){
                    if (!line[i].equals("") && line[i] != null){
                        int[] letters = anagramRoots.stringToValue(line[i]);
                        heapSorter.sort(letters);
                        anagramRoots.insert(anagramRoots.charArrayToString(letters));
                    }
                }
            }
            System.out.println(anagramRoots.size());
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Hashtable(){
        //For now, Hashtable will have defined size of 101
        this.table = new ArrayList<>(this.MAXSIZE);
        for (int i = 0; i < this.MAXSIZE; i++) {
            this.table.add(new ArrayList<>());
        }
    }

    //Collision handling will be handled by chaining
    public int hash(String word){
        int sum = 0;
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++)
            sum += (int) Character.toLowerCase(letters[i]);
        return sum % MAXSIZE;
    }

    public void insert(String word){
        ArrayList<String> bucket = this.table.get(this.hash(word));
        boolean found = false;
        for (int i = 0; i < bucket.size() && !found; i++){
            if (bucket.get(i).equals(word))
                found = true;
        }
        if (!found)
            bucket.add(word);
    }

    public int size(){
        int size = 0;
        for (int i = 0; i < this.table.size(); i++){
            size += this.table.get(i).size();
        }
        return size;
    }

    public int[] stringToValue(String word){
        char[] letters = word.toCharArray();
        int[] letterValues = new int[letters.length];
        for (int i = 0; i < letters.length; i++)
            letterValues[i] = (int) Character.toLowerCase(letters[i]);
        return letterValues;
    }

    public String charArrayToString(int[] letters){
        String word = "";
        for (int i = 0; i < letters.length; i++)
            word = word + (char) letters[i];
        return word;
    }
}