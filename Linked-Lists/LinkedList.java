public class LinkedList<T>{
    //LinkedList holds the start of a List of Nodes
    //Nodes hold type T data (for this exercise, will be type Account) and a pointer to the next Node or null
    //Count holds how many type T items are in the list
    //Node handles the ID of each Node. IDs begin at 1, so if an ID of 0 every appears, an error occurred
    private Node head;
    private int count;
    public class Node{
        private T data;
        private int ID;
        private Node next;

        //Constructor
        Node(T data, int ID, Node next){
            this.data = data;
            this.ID = ID;
            this.next = next;
        }

        //Getter Methods
        public T getData(){
            return this.data;
        }

        public int getID(){
            return this.ID;
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
    public Node getHead(){
        return this.head;
    }

    //Return the data, not the Node, of the Node with the given parameter ID
    //User should not be able to see how LinkedList works behind the scenes, so Node is never seen
    //Returns null if ID does not exist
    public T findNode(int ID){
        Node current = this.head;
        while (current != null){
            if (current.getID() == ID)
                return current.getData();
            current = current.getNextNode();
        }
        return null;
    }

    //Returns the last Node in the LinkedList
    public Node getLastNode(){
        Node current = this.head;
        while (current.getNextNode() != null)
            current = current.getNextNode();
        return current;
    }

    //Finds the middle ID of the LinkedList. Defaults to first Node in an even split situation
    public int findMedianID(){
        int start = Math.ceilDiv(this.count, 2);
        Node current = this.head;
        while(start > 1){
            current = current.getNextNode();
            start--;
        }
        return current.getID();
    }

    //For testing purposes only
    public void print(){
        Node current = this.head;
        while (current != null){
            System.out.println(current.getData().toString() + "ID: " + current.getID());
            current = current.getNextNode();
        }
    }

    //Setter Methods
    //addNode adds Nodes into the LinkedList. Handles the functionality of IDing each Node
    //If LinkedList is empty, the Node added will be the first in the LinkedList, starting with ID = 1
    //Additional Nodes will be added in two ways. First, if there is an empty incremental ID spot, insert at empty spot with ID
    //If no empty incremental ID spot is found, insert at the end of the LinkedList
    public void addNode(T data){
        if (this.head == null){
            this.head = new Node(data, 1, null);
            this.count++;
        }
        else{
            Node current = this.head;
            while (current.getNextNode() != null && (current.getNextNode().getID() - current.getID() == 1))
                current = current.getNextNode();
            current.setNextNode(new Node(data, current.getID() + 1, current.getNextNode()));
            this.count++;
        }
    }

    //Removes the first Node from the LinkedList
    public void deleteFirstNode(){
        if (this.head != null){
            this.head = this.head.getNextNode();
            this.count--;
        }
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
            if (current.getID() == ID){
                this.head = this.head.getNextNode();
                this.count--;
            }
            else{
                //Interate through the LinkedList until it stops either before the ID or at the end
                while (current.getNextNode() != null && ID > current.getID() + 1)
                    current = current.getNextNode();
                //If statement checks to see if current is at the end of the LinkedList
                //If current has reached the end, the ID has not been found,
                //Therefore, only delete a node if ID was found (i.e. has not reached the end)
                if (current.getNextNode() != null){
                    if (current.getNextNode().getID() == ID){
                        current.setNextNode(current.getNextNode().getNextNode());
                        this.count--;
                    }
                }
            }
        }
    }
}