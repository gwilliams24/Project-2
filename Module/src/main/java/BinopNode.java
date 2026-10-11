public record BinopNode (Object node, AST left, AST right) implements AST {

    public double eval(){
        switch(node) {
            case NumNode result:
                return result.value();
            case BinopNode binop_tree:
                double left = binop_tree.left.eval();
                double right = binop_tree.right().eval();
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
                throw new IllegalStateException("Unexpected value: " + node);
        }
    }
}


