import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Hashtable{
    //Set the MAXSIZE for the Hashtable.
    private final int MAXSIZE = 1009;

    //Hashtable is an ArrayList of ArrayLists. Collisions handled with seperate chaining
    private ArrayList<ArrayList<String>> table;
    
    public static void main(String args[]){
        Hashtable anagramRoots = new Hashtable();
        HeapSort heapSorter = new HeapSort();
        try (Scanner fileReader = new Scanner(new File("./Hashtable/pride-and-prejudice.txt"))){
            while (fileReader.hasNextLine()){
                String[] line = fileReader.nextLine().split("[^a-zA-Z0-9]+");
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

    //Constructors
    public Hashtable(){
        this.table = new ArrayList<>(this.MAXSIZE);
        for (int i = 0; i < this.MAXSIZE; i++) {
            this.table.add(new ArrayList<>());
        }
    }

    /**
     * Computes a hash value for the given string.
     * @param word the input string to hash
     * @return the computed hash value in the range 0 to MAXSIZ - 1
     */
    public int hash(String word){
        int sum = 0;
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++)
            sum += (int) Character.toLowerCase(letters[i]);
        return (sum * 101) % MAXSIZE;
    }

    /**
     * Inserts the word into the hash table
     * @param word String to be inserted into the hash table
     */
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

    /**
     * Finds the total amount of elements in the hash table
     * @return int size of the entire hash table
     */
    public int size(){
        int size = 0;
        for (int i = 0; i < this.table.size(); i++){
            size += this.table.get(i).size();
        }
        return size;
    }

    //Helper method to convert a word into a char array into an int array of ASCII values of lowercase character
    public int[] stringToValue(String word){
        char[] letters = word.toCharArray();
        int[] letterValues = new int[letters.length];
        for (int i = 0; i < letters.length; i++)
            letterValues[i] = (int) Character.toLowerCase(letters[i]);
        return letterValues;
    }

    //Helper method takes in an int array, and converts it back into a String
    public String charArrayToString(int[] letters){
        String word = "";
        for (int i = 0; i < letters.length; i++)
            word = word + (char) letters[i];
        return word;
    }
}