import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

public class CompleteBinaryTree<T extends Comparable<T>>{
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
    public CompleteBinaryTree(){
        this.head = null;
    }

    //Getter Methods
    /**
     * Finds the maximum depth of the Tree
     * @return int of the max depth of the Tree
     */
    public int getTreeDepth(){
        return this.getDepth(this.head);
    }

    //Helper function to find the depth of the Tree recursively
    private int getDepth(Node current){
        if (current == null)
            return 0;
        if (current.isLeaf())
            return 1;
        else
            return 1 + Math.max(this.getDepth(current.getLeftNode()), this.getDepth(current.getRightNode()));
    }

    /**
     * Prints the contents of the Tree using Depth First Search
     */
    public void printDFS(){
        this.DFS(this.head);
    }

    //Helper function to recursively print out each element in a DPS
    private void DFS(Node current){
        if (current != null){
            if (current.getLeftNode() != null)
                this.DFS(current.getLeftNode());
            System.out.println(current.getData().toString());
            if (current.getRightNode() != null)
                this.DFS(current.getRightNode());
        }
    }

    /**
     * Prints the contents of the Tree using Breadth First Search
     */
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
    /**
     * Inserts the data into the CompleteBinaryTree at the first available Node breadth first
     * @param data to be inserted
     */
    public void insert(T data){
        if (this.head == null)
            this.head = new Node<>(data);
        else{
            boolean inserted = false;
            Queue<Node<T>> queue = new LinkedList<>();
            queue.add(this.head);
            while (!inserted){
                Node<T> element = queue.remove();
                if (element.getLeftNode() == null){
                    element.setLeft(data);
                    inserted = true;
                }
                else
                    queue.add(element.getLeftNode());

                if (!inserted && element.getRightNode() == null){
                    element.setRight(data);
                    inserted = true;
                }
                else
                    queue.add(element.getRightNode());
            }
        }
    }

    /**
     * Converts a given ArrayList into a Complete Binary Tree
     * @param list that will be converted into a Complete Binary Tree
     */
    public void insert(ArrayList<T> list){
        for (int i = 0; i < list.size(); i++)
            this.insert(list.get(i));
    }
}