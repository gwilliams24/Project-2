import java.util.NoSuchElementException;

public class Parser {

    // turns string input into a AST when input is in Postfix notation
    public static AST parsePostfix(String input) {
        ArrayStack<AST> stack = ArrayStack.emptyStack();
        if (input == null) {
            throw new IllegalArgumentException("empty input");
        }
        String[] tokens = input.split("\\s+");
        for (int i = 0; i < tokens.length; i += 1) {
            if (operatorCheck(tokens[i])) {
                createBinopNode(tokens[i], stack);
            } else {
                createNumNode(tokens[i], stack);
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("too many operands");
            }

        return stack.pop();

    }

    // turns string input into an AST when input is in Infix notation
    public static AST parseInfix(String input) {
        ArrayStack<AST> expressions = ArrayStack.emptyStack();
        ArrayStack<String> operators = ArrayStack.emptyStack();
        if (input == null) {
            throw new IllegalArgumentException("empty input");
        }
        String[] tokens = input.split("\\s+");
        for (int i = 0; i < tokens.length; i += 1) {
            if (tokens[i].equals("(")) {
                operators.push(tokens[i]);
            } else if (tokens[i].equals(")")) {
                while (operators.size() > 0 && !operators.peek().equals("(")) {
                    String operator = operators.pop();
                    createBinopNode(operator, expressions);
                }
                if (operators.size() == 0) {
                    throw new IllegalArgumentException("mismatched close paren");
                }
                operators.pop();

            } else if (operatorCheck(tokens[i])) {
                token_operations(tokens[i], expressions, operators);

            } else {
                createNumNode(tokens[i], expressions);
            }
        }
        while (operators.size() > 0) {
            if (operators.peek().equals("(")) {
                throw new IllegalArgumentException("mismatched open paren");
            }
            createBinopNode(operators.pop(), expressions);

        }
        if (expressions.size() != 1){
            throw new IllegalArgumentException("too many operands");
        }

        return expressions.pop();
    }

    // helper function: checks to see what type token is
    public static boolean operatorCheck(String token) {
        return (token.equals("+") || token.equals("-") || token.equals("*")
                || token.equals("/") || token.equals("^"));
    }

    // helper function: creates a BinopNode holding an operator and 2 AST children
    public static void createBinopNode(String operator, ArrayStack<AST> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("insufficient operands");
        }
        AST right = stack.pop();
        AST left = stack.pop();
        stack.push(new BinopNode(operator, left, right));
    }

    // helper function: creates a NumNode holding an operand
    public static void createNumNode(String operand, ArrayStack<AST> stack) {
        try {
            double value = Double.parseDouble(operand);
            stack.push(new NumNode(value));
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid token");
        }
    }

    // helper function: gets precedence of an operator in Infix expression
    public static int getPrecedence(String token) {
        if (token.equals("^")) {
            return 3;
        }
        else if (token.equals("*") || token.equals("/")) {
            return 2;
        }
        else {
            return 1;
        }
    }

    // helper function: decides token push order for Infix expression
    public static void token_operations(String token, ArrayStack<AST> expressions, ArrayStack<String> operators) {
        int precedence = getPrecedence(token);
        while (operators.size() > 0 && !operators.peek().equals("(")) {
            int previous_precedence = getPrecedence(operators.peek());
            if (previous_precedence > precedence || (previous_precedence == precedence && !token.equals("^"))) {
                createBinopNode(operators.pop(), expressions);
            } else {
                break;
            }
        }
        operators.push(token);
    }
}
