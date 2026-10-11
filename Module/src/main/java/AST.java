public interface AST <T> {
    // Arithmatic operations for AST
    public static double eval(AST tree){
        switch(tree) {
            case NumNode result:
                return result.value();
            case BinopNode binop_tree:
                double left = eval(binop_tree.left());
                double right = eval(binop_tree.right());
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


}
