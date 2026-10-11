public record NumNode (double value) implements AST {

    public double eval(){
        return value;

    }


}
