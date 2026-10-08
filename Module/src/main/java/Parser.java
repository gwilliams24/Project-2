import java.util.NoSuchElementException;

public class Parser {

    public static AST parsePostfix(String input){
        ArrayStack <AST> stack  = ArrayStack.emptyStack();
        if (input == null){
            throw new IllegalArgumentException("empty input");
        }
        String[] tokens = input.split("\\s+");
        for (int i = 0; i< tokens.length; i += 1){
            if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*")
            || tokens[i].equals("/") || tokens[i].equals("^")){
                if (stack.size() < 2){
                    throw new IllegalArgumentException("insufficent operands");
                } else {
                    AST right = stack.pop();
                    AST left = stack.pop();
                    stack.push(new BinopNode(tokens[i], left, right));
                }
            }
            else{
                try {
                    double value = Double.parseDouble(tokens[i]);
                    stack.push(new NumNode(value));
                }
            }
            else{

            }
        }
        return stack.pop();
    }



    public static double operations(AST tree){
        switch(tree){
            case NumNode result:
                return result.value();
            case BinopNode binop_tree:
                double left = operations(binop_tree.left());
                double right = operations(binop_tree.right());
                if (binop_tree.node().equals("+")) {
                    return left + right;
                }
                else if (binop_tree.node().equals("-")) {
                    return left - right;
                }
                else if (binop_tree.node().equals("*")) {
                    return left * right;
                }
                else if (binop_tree.node().equals("/")) {
                    return left / right;
                }
                else {
                    return Math.pow(left, right);
                }

        }
    }

    public static double result (String input){
        AST tree = parsePostfix(input);
        return operations(tree);
    }


}
