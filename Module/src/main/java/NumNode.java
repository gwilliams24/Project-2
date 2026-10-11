// holds a double, returns a double when evaluated in an AST
public record NumNode (double value) implements AST {

    public double eval(){
        return value;

    }


}
