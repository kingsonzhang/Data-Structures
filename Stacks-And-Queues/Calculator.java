public class Calculator{
    public static void main(String args[]){
        Calculator tester = new Calculator();
        try{
            System.out.println(tester.calculate("( 6 + 3 ) * 5 + ( 10 + 2 ) / 3"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Evaluates a mathematical expression given in the parameter
     * @param expression the mathematical expression to evaluate in String format. Cannot handle negative numbers currently
     * @return the numeric result of evaluating the expression
     * @throws Exception if the expression is empty, invalid, or cannot be processed
     */
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

    /**
     * Converts a mathematical expression given in the parameter into postfix notation
     * @param expression the mathematical expression to evaluate in String format. Cannot handle negative numbers currently
     * @return postfix notation of given parameter as a String
     * @throws Exception if the expression is empty, invalid, or cannot be processed
     */
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