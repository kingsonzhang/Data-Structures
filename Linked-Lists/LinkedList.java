public class LinkedList<T>{
    private Node head;
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
        public void setData(T data){
            this.data = data;
        }

        public void setNextNode(Node next){
            this.next = next;
        }
    }

    //Constructor
    LinkedList(){
        this.head = null;
    }

    //Getter Methods
    public boolean isEmpty(){
        return this.head == null;
    }

    public Node getHead(){
        return this.head;
    }

    public Node getLastNode(){
        Node current = this.head;
        while (current.getNextNode() != null)
            current = current.getNextNode();
        return current;
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
    public void addNode(T data){
        if (this.head == null)
            this.head = new Node(data, 1, null);
        else{
            Node current = this.head;
            while (current.getNextNode() != null && (current.getNextNode().getID() - current.getID() == 1))
                current = current.getNextNode();
            current.setNextNode(new Node(data, current.getID() + 1, current.getNextNode()));
        }
    }

    public void deleteNode(int ID){
        Node current = this.head;
        if (current != null){
            if (current.getID() == ID)
                this.head = this.head.getNextNode();
            while (current.getNextNode() != null && ID > current.getID()){
                if (current.getNextNode().getID() == ID)
                    current.setNextNode(current.getNextNode().getNextNode());
                current = current.getNextNode();
            }
        }
    }
}