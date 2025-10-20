public class Tree<T extends Comparable<T>>{
    private Node head;

    protected class Node<T extends Comparable<T>>{
        private T data;
        private Node leftNode;
        private Node rightNode;

        //Constructors
        public Node(){
            this.data = null;
            this.leftNode = null;
            this.rightNode = null;
        }

        public Node(T data){
            this.data = data;
            this.leftNode = null;
            this.rightNode = null;
        }

        //Getter Methods
        public T getData(){
            return this.data;
        }

        public Node getLeftNode(){
            return this.leftNode;
        }

        public Node getRightNode(){
            return this.rightNode;
        }

        public boolean isLeaf(){
            return (this.getLeftNode() == null && this.getRightNode() == null);
        }

        //Setter Methods
        public void setData(T data){
            this.data = data;
        }

        public void setLeft(T data){
            this.leftNode = new Node(data);
        }

        public void setLeft(Node data){
            this.leftNode = data;
        }

        public void setRight(T data){
            this.rightNode = new Node(data);
        }

        public void setRight(Node data){
            this.rightNode = data;
        }
    }

    public Tree(){
        this.head = null;
    }

    public int getDepth(Node current){
        if (current.isLeaf())
            return 0;
        else
            return 1 + Math.max(this.getDepth(current.getLeftNode()), this.getDepth(current.getRightNode()));
    }

    public int getTreeDepth(){
        return this.getDepth(this.head);
    }

    public void insert(T data){
        if (this.head == null)
            this.head = new Node(data);
        else
            this.insertAtNode(data, this.head);
    }

    private void insertAtNode(T data, Node current){
        if (current.getData().compareTo(data) < 0)
            if (current.getLeftNode() == null)
                current.setLeft(data);
            else
                this.insertAtNode(data, current.getLeftNode());
        else
            if (current.getRightNode() == null)
                current.setRight(data);
            else
                this.insertAtNode(data, current.getRightNode());
    }
}