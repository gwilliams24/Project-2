import java.util.NoSuchElementException;

public class Parser {


    public static int typeCheck(String token) {
        if (token.equals("+") || token.equals("-") || token.equals("*")
                || token.equals("/") || token.equals("^")) {
            return 1;
        } else if (token.equals("0") || token.equals("1") || token.equals("2") || token.equals("3")
                    || token.equals("4") || token.equals("5") || token.equals("6") || token.equals("7")
                    || token.equals("8") || token.equals("9")) {
            return 2;
        } else {
            throw new IllegalArgumentException("input includes a non-valid operand/operator");
        }
    }
    public static AST parsePostfix(String input){
        ArrayStack <AST> stack  = ArrayStack.emptyStack();
        if (input == null) {
            throw new IllegalArgumentException("empty input");
        }
        String[] tokens = input.split("\\s+");
        for (int i = 0; i< tokens.length; i += 1) {
            if (typeCheck(tokens[i]) == 1) {
                if (stack.size() < 2){
                    throw new IllegalArgumentException("insufficent operands");
                } else {
                    AST right = stack.pop();
                    AST left = stack.pop();
                    stack.push(new BinopNode(tokens[i], left, right));
                }
            } else if (typeCheck(tokens[i]) == 2) {
                try {
                    double value = Double.parseDouble(tokens[i]);
                    stack.push(new NumNode(value));
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        return stack.pop();
    }

    public static AST parseInFix(String input) {
        ArrayStack <AST> expressions  = ArrayStack.emptyStack();
        ArrayStack <AST> operations = ArrayStack.emptyStack();
        if (input == null) {
            throw new IllegalArgumentException("empty input");
        }
        String[] tokens = input.split("\\s+");
        for (int i = 0; i< tokens.length; i += 1) {
            if (tokens[i].equals("(")) {

            } else if (typeCheck(tokens[i]) == 1) {

            } else if (typeCheck(tokens[i]) == 2) {

            } else {
                throw new IllegalArgumentException();
            }
        }
        return stack.pop();
        }
    }

    public static double operations(AST tree){
        switch(tree) {
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
            default:
                throw new IllegalStateException("Unexpected value: " + tree);
        }
    }

    public static double result (String input){
        AST tree = parsePostfix(input);
        return operations(tree);
    }


}
