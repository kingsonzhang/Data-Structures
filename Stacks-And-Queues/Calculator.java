public class Calculator{
    public static void main(String args[]){
        Calculator tester = new Calculator();
        try{
            System.out.println(tester.calculate("( 6 + 3 ) * 5 + ( 10 + 2 ) / 3"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    //Calculate will return the result of the evaluated expression
    //Will throw an exception if the expression is empty
    //Expression will empty if the postfix notation conversion receives an invalid expression

    //Once postfix notation has been received, follow these steps
    //Create a stack and begin to dequeue the numbers
    //If a number is encountered, push it onto the stack
    //If an operation is encountered, pop two numbers off the stack, and operate
    //Push the result of the operation onto the stack
    //Result should be one final number on the stack
    //Pop said number off the stack and return
    public double calculate(String expression) throws Exception{
        Queue postfix = this.convertToPostfix(expression);
        if (postfix != null){
            Stack values = new Stack();
            while (postfix.size() != 0){
                String component = postfix.poll().toString();
                if (this.isInteger(component))
                    values.push(Integer.valueOf(postfix.dequeue().toString()));
                else{
                    double secondValue = Double.parseDouble(values.pop().toString());
                    double firstValue = Double.parseDouble(values.pop().toString());
                    switch (component){
                        case "+" -> values.push(firstValue + secondValue);
                        case "-" -> values.push(firstValue - secondValue);
                        case "*" -> values.push(firstValue * secondValue);
                        case "/" -> values.push(firstValue / secondValue);
                    }
                    postfix.dequeue();
                }
            }
            return Double.parseDouble(values.pop().toString());
        }
        else{
            throw new Exception("Obtained an empty expression");
        }
    }

    //Converts an expression into postfix notation
    //Will throw an exception if expression is not valid

    //Step by step rules for how postfix conversion works
    //Create a stack and queue. The queue will hold the converted postfix notation

    //When a "(" is encountered, immediately push onto the stack
    //When a ")" is encountered, immediately pop everything onto the stack and enqueue into the queue
    //until a "(" is encountered. If not encountered, invalid expression

    //When either a "*" or "/" is encountered, immediately push onto the stack
    //When either a "+" or "-" is encountered, pop all "*" and "/" signs and enqueue them

    //When a number is encountered, immediately enqueue the number
    private Queue convertToPostfix(String expression) throws Exception{
        Queue postfixNotation = new Queue();
        String[] pieces = expression.split(" ");
        if (pieces.length > 2){
            Stack helper = new Stack();
            for(int i = 0; i < pieces.length; i++){
                String value = pieces[i];
                switch (value){
                    case "(" -> helper.push(value);
                    case ")" -> {
                        while (helper.size() != 0 && !helper.peek().toString().equals("("))
                            postfixNotation.enqueue(helper.pop());
                        if (helper.peek().toString().equals("("))
                            helper.pop();
                    }
                    case "*", "/" -> {
                        if (postfixNotation.size() == 0)
                            throw new Exception("Not a valid expression");
                        helper.push(value);
                    }
                    case "+", "-" -> {
                        if (postfixNotation.size() == 0)
                            throw new Exception("Not a valid expression");
                        while (helper.size() != 0 && (helper.peek().toString().equals("*") || helper.peek().toString().equals("/")))
                            postfixNotation.enqueue(helper.pop());
                        helper.push(value);
                    }
                    default -> {
                        if (this.isInteger(value))
                            postfixNotation.enqueue(value);
                        else
                            return null;
                    }
                }
            }
            while (helper.size() != 0)
                postfixNotation.enqueue(helper.pop());
        }
        if (postfixNotation.size() < 3)
            return null;
        return postfixNotation;
    }
    
    //Helper method to determine if a string is a valid number
    private boolean isInteger(String str) {
        if (str == null)
            return false;
        int length = str.length();
        if (length == 0)
            return false;
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1)
                return false;
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }
}