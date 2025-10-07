import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Hashtable{
    //Set the MAXSIZE for the Hashtable. For now, collisions will be handled using
    //Seperate Chaining
    //Hashtable is essentially an Array of size MAXSIZE of LinkedLists
    private final int MAXSIZE = 101;
    private ArrayList<ArrayList<String>> table;
    public static void main(String args[]){
        Hashtable anagramRoots = new Hashtable();
        HeapSort heapSorter = new HeapSort();

        try (Scanner fileReader = new Scanner(new File("./Hashtable/pride-and-prejudice.txt"))){
            //Read a file line by line instead of the entire file
            //Split each line by word, not including numbers
            //Loop through the array of words and convert each word into an array of characters
            //Sort the letters alphabetically, convert the letters back into a string
            //Insert the anagram root word into the Hashtable
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
        //Define the size of the Hashtable according to MAXSIZE
        this.table = new ArrayList<>(this.MAXSIZE);
        for (int i = 0; i < this.MAXSIZE; i++) {
            this.table.add(new ArrayList<>());
        }
    }

    //Collision handling will be handled by chaining
    //Really simple hash algorithm, simply mod the sum of the ASCII values
    //of each invidiual lowercase letter by MAXSIZE
    public int hash(String word){
        int sum = 0;
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++)
            sum += (int) Character.toLowerCase(letters[i]);
        return sum % MAXSIZE;
    }

    //Find the hash of the word, and insert it into the private array of LinkedLists
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

    //Loop through the private Array of LinkedLists
    //Sum all the elements of the LinkedLists and return
    public int size(){
        int size = 0;
        for (int i = 0; i < this.table.size(); i++){
            size += this.table.get(i).size();
        }
        return size;
    }

    //Helper method to convert a word into a char array into
    //an int array of ASCII values of each individual lowercase character
    public int[] stringToValue(String word){
        char[] letters = word.toCharArray();
        int[] letterValues = new int[letters.length];
        for (int i = 0; i < letters.length; i++)
            letterValues[i] = (int) Character.toLowerCase(letters[i]);
        return letterValues;
    }

    //Helper method takes in an int array, and converts it back
    //into a String
    public String charArrayToString(int[] letters){
        String word = "";
        for (int i = 0; i < letters.length; i++)
            word = word + (char) letters[i];
        return word;
    }
}