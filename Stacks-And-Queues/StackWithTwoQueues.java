public class StackWithTwoQueues{
    private Queue stack;
    private Queue helper;

    //Constructor
    public StackWithTwoQueues(){
        this.stack = new Queue();
        this.helper = new Queue();
    }
    //Getter Methods
    public Object peek(){
        return this.stack.poll();
    }

    public int size(){
        return this.stack.size();
    }

    //Setter Methods
    public void push(Object data){
        this.helper.enqueue(data);
        while (this.stack.size() > 0)
            this.helper.enqueue(this.stack.dequeue());
        Queue temp = this.stack;
        this.stack = helper;
        this.helper = temp;
    }

    public Object pop(){
        return this.stack.dequeue();
    }
}