public class LinkedList<T>{
    private Node head;
    public class Node{
        private T data;
        private Node next;

        Node(T data, Node next){
            this.data = data;
            this.next = next;
        }

        //Getter Methods
        public T getData(){
            return this.data;
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
    public Node getHead(){
        return this.head;
    }

    public Node getLastNode(){
        if (this.head == null)
            return null;
        else{
            Node current = this.head;
            while (current.getNextNode() != null)
                current = current.getNextNode();
            return current;
        }
    }

    //Setter Methods
    public void addNode(T data){
        if (this.head == null)
            this.head = new Node(data, null);
        else
            this.getLastNode().setNextNode(new Node(data, null));
    }
}