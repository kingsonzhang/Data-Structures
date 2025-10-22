import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>>{
    private Node<T> head;

    protected class Node<T extends Comparable<T>>{
        private T data;
        private Node<T> leftNode;
        private Node<T> rightNode;

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

        public Node<T> getLeftNode(){
            return this.leftNode;
        }

        public Node<T> getRightNode(){
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
            this.leftNode = new Node<>(data);
        }

        public void setLeft(Node<T> data){
            this.leftNode = data;
        }

        public void makeLeftNodeNull(){
            this.leftNode = null;
        }

        public void setRight(T data){
            this.rightNode = new Node<>(data);
        }

        public void setRight(Node<T> data){
            this.rightNode = data;
        }

        public void makeRightNodeNull(){
            this.rightNode = null;
        }
    }

    //Constructors
    public Tree(){
        this.head = null;
    }

    //Getter Methods
    public int getTreeDepth(){
        return this.getDepth(this.head);
    }

    private int getDepth(Node current){
        if (current == null)
            return 0;
        if (current.isLeaf())
            return 1;
        else
            return 1 + Math.max(this.getDepth(current.getLeftNode()), this.getDepth(current.getRightNode()));
    }

    public void printDFS(){
        this.DFS(this.head);
    }

    private void DFS(Node current){
        if (current != null){
            if (current.getLeftNode() != null)
                this.DFS(current.getLeftNode());
            System.out.println(current.getData().toString());
            if (current.getRightNode() != null)
                this.DFS(current.getRightNode());
        }
    }

    public void printBFS(){
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> BFS = new LinkedList<>();
        if (this.head != null)
            queue.add(this.head);
        while (!queue.isEmpty()){
            Node current = queue.remove();
            if (current.getLeftNode() != null)
                queue.add(current.getLeftNode());
            if (current.getRightNode() != null)
                queue.add(current.getRightNode());
            BFS.add(current);
        }
        while (!BFS.isEmpty())
            System.out.println(BFS.remove().getData().toString());
    }

    //Setter Methods
    public void insert(T data){
        if (this.head == null)
            this.head = new Node<>(data);
        else
            this.insertAtNode(data, this.head);
    }

    //If data comparison at node is equivalent, insert on left side of tree
    private void insertAtNode(T data, Node<T> current){
        if (current.getData().compareTo(data) > 0)
            if (current.getLeftNode() == null)
                current.setLeft(data);
            else
                this.insertAtNode(data , current.getLeftNode());
        else
            if (current.getRightNode() == null)
                current.setRight(data);
            else
                this.insertAtNode(data, current.getRightNode());
    }

    public void deleteNode(T data){
        if (this.head != null){
            if (this.head.getData().equals(data)){
                if (this.head.isLeaf())
                    this.head = null;
                else if (this.head.getLeftNode() == null)
                    this.head = this.head.getRightNode();
                else if (this.head.getRightNode() == null)
                    this.head = this.head.getLeftNode();
                else{
                    Node<T> deepest = this.head.getRightNode();
                    while (deepest.getLeftNode() != null)
                        deepest = deepest.getLeftNode();
                    deepest.setLeft(this.head.getLeftNode());
                    this.head = this.head.getRightNode();
                }
            }
            else{
                Node<T> current = this.head;
                Node<T> previous = null;
                boolean leftSide = false;
                while (current != null && !data.equals(current.getData())){
                    if (data.compareTo(current.getData()) > 0){
                        previous = current;
                        current = current.getRightNode();
                        leftSide = false;
                    }
                    else{
                        previous = current;
                        current = current.getLeftNode();
                        leftSide = true;
                    }
                }
                if (current != null){
                    if (leftSide){
                        if (current.isLeaf())
                            previous.makeLeftNodeNull();
                        else if (current.getLeftNode() == null)
                            previous.setLeft(current.getRightNode());
                        else if (current.getRightNode() == null)
                            previous.setLeft(current.getLeftNode());
                        else{
                            Node<T> deepest = current.getRightNode();
                            while (deepest.getLeftNode() != null)
                                deepest = deepest.getLeftNode();
                            deepest.setLeft(current.getLeftNode());
                            previous.setLeft(current.getRightNode());
                        }
                    }
                    else{
                        if (current.isLeaf())
                            previous.makeRightNodeNull();
                        else if (current.getLeftNode() == null)
                            previous.setRight(current.getRightNode());
                        else if (current.getRightNode() == null)
                            previous.setRight(current.getLeftNode());
                        else{
                            Node<T> deepest = current.getRightNode();
                            while (deepest.getLeftNode() != null)
                                deepest = deepest.getLeftNode();
                            deepest.setLeft(current.getLeftNode());
                            previous.setRight(current.getRightNode());
                        }
                    }
                }
            }
        }
    }
}