//Need to rewrite most of the class
//Must have insertAtIndex
//Delete should remove and return the node, so merge can create a new LinkedList of accounts to add
//Create a find emptyIndexes that returns a LinkedList of ints of emptyIndexes

/*
What's the logic here?
Calling merge will make the current bank find all the empty indexes of the current Accounts linkedlist
That will return a LinkedList of empty indexes
Iterate through the parameter bank and find all the accounts at the given indexes, returned in a LinkedList
The accounts can be null, the merge function will handle null accounts
Honestly maybe just make a Stack and Queue
*/

public class LinkedList<T>{
    //LinkedList holds the start of a List of Nodes
    //Nodes hold type T data (for this exercise, will be type Account) and a pointer to the next Node or null
    //Count holds how many type T items are in the list
    //Node handles the ID (index) of each Node.
    private Node head;
    private int count;
    public class Node{
        private T data;
        private int index;
        private Node next;

        //Constructor
        Node(T data, int index, Node next){
            this.data = data;
            this.index = index;
            this.next = next;
        }

        //Getter Methods
        public T getData(){
            return this.data;
        }

        public int getIndex(){
            return this.index;
        }

        public Node getNextNode(){
            return this.next;
        }

        //Setter Methods
        //No setter method for ID. ID is handled when type T data are added to the list
        public void setData(T data){
            this.data = data;
        }

        public void setNextNode(Node next){
            this.next = next;
        }
    }

    //Constructor
    //Default LinkedList holds an empty list, pointing to null
    LinkedList(){
        this.head = null;
        this.count = 0;
    }

    //Getter Methods
    //isEmpty returns if the LinkedList is empty
    public boolean isEmpty(){
        return this.head == null;
    }

    //getHead returns the head of the LinkedList
    protected Node getHeadNode(){
        return this.head;
    }

    public T getHead(){
        if (this.isEmpty())
            return null;
        return this.getHeadNode().getData();
    }

    //Returns the last Node in the LinkedList
    protected Node getLastNode(){
        Node current = this.getHeadNode();
        while (current.getNextNode() != null)
            current = current.getNextNode();
        return current;
    }

    public Integer getHeadIndex(){
        if (this.isEmpty())
            return null;
        return this.getHeadNode().getIndex();
    }

    //Return the data, not the Node, of the Node with the given parameter index
    //User should not be able to see how LinkedList works behind the scenes, so Node is never seen
    //Returns null if Node does not exist at index
    public T findData(int index){
        Node current = this.getHeadNode();
        while (current != null){
            if (current.getIndex() == index)
                return current.getData();
            current = current.getNextNode();
        }
        return null;
    }

    //Finds the middle index of the LinkedList. Defaults to first Node in an even split situation
    public int findMedianIndex(){
        int start = Math.ceilDiv(this.count, 2);
        Node current = this.getHeadNode();
        while(start > 1){
            current = current.getNextNode();
            start--;
        }
        return current.getIndex();
    }
    
    public LinkedList<Integer> findEmptyIndexes(){
        LinkedList<Integer> emptyIndexes = new LinkedList<>();
        if (this.getHeadNode().getIndex() != 0)
            for (int i = 0; i < this.getHeadNode().getIndex(); i++)
                emptyIndexes.addNode(i);
        Node current = this.getHeadNode();
        while (current.getNextNode() != null){
            if (current.getNextNode().getIndex() - current.getIndex() != 1)
                for (int i = current.getIndex() + 1; i < current.getNextNode().getIndex(); i++)
                    emptyIndexes.addNode(i);
            current = current.getNextNode();
        }
        return emptyIndexes;
    }

    //For testing purposes only
    public void print(){
        Node current = this.getHeadNode();
        while (current != null){
            System.out.println(current.getData().toString() + "\nID: " + current.getIndex());
            current = current.getNextNode();
        }
    }

    //Setter Methods
    //addNode adds Nodes into the LinkedList. Handles the functionality of indexing each Node
    //If LinkedList is empty, the Node added will be the first in the LinkedList, starting with ID = 1
    //Additional Nodes will be added in a few ways. First, if LinkedList is empty, insert data at head with index 1
    //Second, if head index is not 1 (meaning head node was deleted at some point), insert Node at head
    //Otherwise, add the Node at the end of the LinkedList
    public void addNode(T data){
        if (this.head == null){
            this.head = new Node(data, 0, null);
            this.count++;
        }
        else{
            Node current = this.getHeadNode();
            if (current.getIndex() != 0){
                this.head = new Node(data, 0, current);
                this.count++;
            }
            else{
                while (current.getNextNode() != null && (current.getNextNode().getIndex() - current.getIndex() == 1))
                    current = current.getNextNode();
                current.setNextNode(new Node(data, current.getIndex() + 1, current.getNextNode()));
                this.count++;
            }
        }
    }

    public void addNodeAtIndex(T data, int index){
        if (this.isEmpty() || this.getHeadNode().getIndex() > index){
            this.head = new Node(data, index, null);
            this.count++;
        }
        else{
            Node current = this.getHeadNode();
            while (current.getNextNode() != null && current.getNextNode().getIndex() < index)
                current = current.getNextNode();
            current.setNextNode(new Node(data, index, current.getNextNode()));
        }
    }

    //Removes the first Node from the LinkedList
    public T deleteFirstNode(){
        Node deleted = this.getHeadNode();
        if (deleted != null){
            this.head = this.head.getNextNode();
            this.count--;
            return deleted.getData();
        }
        return null;
    }

    //Removes the entire LinkedList, resetting the head to null
    public void deleteAll(){
        this.head = null;
        this.count = 0;
    }

    //Finds and deletes a Node with a given ID
    public void deleteNode(int ID){
        Node current = this.head;
        if (current != null){
            if (current.getIndex() == ID){
                this.head = this.head.getNextNode();
                this.count--;
            }
            else{
                //Interate through the LinkedList until it stops either before the ID or at the end
                while (current.getNextNode() != null && current.getNextNode().getIndex() != ID)
                    current = current.getNextNode();
                //If statement checks to see if current is at the end of the LinkedList
                //If current has reached the end, the ID has not been found,
                //Therefore, only delete a node if ID was found (i.e. has not reached the end)
                if (current.getNextNode() != null){
                    if (current.getNextNode().getIndex() == ID){
                        current.setNextNode(current.getNextNode().getNextNode());
                        this.count--;
                    }
                }
            }
        }
    }
}