import java.util.NoSuchElementException;

public class Parser {

    // helper function: checks to see what type token is
    public static boolean operatorCheck(String token) {
        return (token.equals("+") || token.equals("-") || token.equals("*")
                || token.equals("/") || token.equals("^"));
    }


    // Turns string input into a AST
    public static AST parsePostfix(String input) {
        ArrayStack<AST> stack = ArrayStack.emptyStack();
        if (input == null) {
            throw new IllegalArgumentException("empty input");
        }
        String[] tokens = input.split("\\s+");
        for (int i = 0; i < tokens.length; i += 1) {
            if (operatorCheck(tokens[i])) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("insufficient operands");
                } else {
                    createBinopNode(tokens[i], stack);
                }
            } else {
                createNumNode(tokens[i], stack);
            }
            if (stack.size() != 1) {
                throw new IllegalArgumentException("too many operands");
                }
            }
            return stack.pop();

    }


    public static void createBinopNode(String operator, ArrayStack<AST> stack) {
        AST right = stack.pop();
        AST left = stack.pop();
        stack.push(new BinopNode(operator, left, right));
    }

    public static void createNumNode(String operand, ArrayStack<AST> stack) {
        try {
            double value = Double.parseDouble(operand);
            stack.push(new NumNode(value));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid operand/operator: " + operand);
        }
    }


    //
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
                operators.pop();

            } else if (operatorCheck(tokens[i])) {
                token_operations(tokens[i], expressions, operators);

            } else {
                createNumNode(tokens[i], expressions);
            }
        }

        while (operators.size() > 0) {
            if (operators.peek().equals("(")) {
                throw new IllegalArgumentException("missing closing parenthesis");
            }
            createBinopNode(operators.pop(), expressions);

            }
            return expressions.pop();
        }
    }

    public static int getPrecedence(String token) {
        return switch (token) {
            case "^" -> 3;
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            default -> throw new IllegalArgumentException();
        };
    }

    public static void token_operations(String token, ArrayStack<AST> expressions, ArrayStack<String> operators) {
        int precedence = getPrecedence(token);
        while (operators.size() > 0 && !operators.peek().equals("(")) {
            int next_precedence = getPrecedence(operators.peek());
            if (precedence == 3) {
                if (next_precedence > precedence) {
                    createBinopNode(operators.pop(), expressions);
                } else {
                    break;
                }
            } else {
                if (next_precedence >= precedence) {
                    createBinopNode(operators.pop(), expressions);
                } else {
                    break;
                }

            }
        }
        operators.push(token);
    }
}
