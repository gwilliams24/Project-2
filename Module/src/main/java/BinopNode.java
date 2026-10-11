// AST tree; holds an operator with 2 AST children
public record BinopNode (Object node, AST left, AST right) implements AST {

    // Evaluate the AST expression
    public double eval() {
        double left = this.left.eval();
        double right = this.right.eval();
        if (this.node().equals("^")) {
            return Math.pow(left, right);
        } else if (this.node().equals("/")) {
            return left / right;
        } else if (this.node().equals("*")) {
            return left * right;
        } else if (this.node.equals("+")) {
            return left + right;
        } else {
            return left - right;
        }
    }
}


