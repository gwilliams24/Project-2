import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    // Tests for PostFix
    @Test
    void parsePostfix1() {
        assertEquals(new BinopNode("+",
                new NumNode(3), new BinopNode("*", new NumNode(4), new NumNode(5))),
                Parser.parsePostfix("3 4 5 * +"));
    }

    @Test
    void parsePostfix_too_many_operands() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parsePostfix("3 4 4 +"));
    }

    @Test
    void parsePostfix_insufficient_operands() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parsePostfix("3 4 + -"));
    }

    @Test
    void parsePostfix_empty() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parsePostfix(null));
    }

    @Test
    void parsePostfix_invalid_token(){
        assertThrows(IllegalArgumentException.class,
                () -> Parser.parsePostfix("3 a +"));
    }


    // Tests for InFix
    @Test
    void parseInfix1() {
        assertEquals(new BinopNode("*",
                        new BinopNode("^", new BinopNode("-", new BinopNode("+", new NumNode(3), new NumNode(4)),
                                new NumNode(2)), new NumNode(2)), new NumNode(5)),
                Parser.parseInfix("( 3 + 4 - 2 ) ^ 2 * 5"));
    }

    @Test
    void parseInfix2(){
        assertEquals(new BinopNode("/", new BinopNode("+", new NumNode(3), new NumNode(4)), new NumNode (6)),
                Parser.parseInfix("( 3 + 4 ) / 6"));
    }

    @Test
    void parseInfix3(){
        assertEquals(new BinopNode("/", new BinopNode("*", new NumNode(3), new NumNode(4)), new NumNode(6)),
                Parser.parseInfix("3 * 4 / 6"));
    }

    @Test
    void parse_Infix4(){
        assertEquals(new BinopNode("+", new NumNode(3), new BinopNode("^", new NumNode(4), new NumNode(2))),
                Parser.parseInfix("3 + 4 ^ 2"));
    }

    @Test
    void parseInfix_missing_close_parens() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parseInfix("( 3 + 4 ^ 2 * 5"));
    }

    @Test
    void parseInfix_invalid_token() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parseInfix("( 3 + a ) ^ 2 * 5"));
    }

    @Test
    void parseInfix_empty() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parseInfix(null));
    }

    @Test
    void parseInfix5_missing_open_paren() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parseInfix(("3 + 4 ) ^ 2 * 5")));
    }

    @Test
    void parseInfix_too_many_operands() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parseInfix(("( 3 3 + 4 ) ^ 2 * 5")));
    }

    @Test
    void parseInfix_insufficient_operands(){
        assertThrows(IllegalArgumentException.class,
                () -> Parser.parseInfix("3 + "));
    }
}
