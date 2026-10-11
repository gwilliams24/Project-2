import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    void parsePostfix1() {
        assertEquals(new BinopNode("+",
                new NumNode(3), new BinopNode("*", new NumNode(4), new NumNode(5))),
                Parser.parsePostfix("3 4 5 * +"));
    }

    @Test
    void parsePostfix2() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parsePostfix("3 4 4 + 5 *"));
    }

    @Test
    void parsePostfix3() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parsePostfix("3 + 4 - 5 *"));
    }

    @Test
    void parsePostfix4() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parsePostfix(null));
    }

    @Test
    void parseInfix1() {
        assertEquals(new BinopNode("*",
                        new BinopNode("^", new BinopNode("-", new BinopNode("+", new NumNode(3), new NumNode(4)),
                                new NumNode(2)), new NumNode(2)), new NumNode(5)),
                Parser.parseInfix("( 3 + 4 - 2 ) ^ 2 * 5"));
    }

    @Test
    void parseInfix2() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parseInfix("( 3 + 4 ^ 2 * 5"));
    }

    @Test
    void parseInfix3() {
        assertThrows(IllegalArgumentException.class,
                ()-> Parser.parseInfix("( 3 + a ) ^ 2 * 5"));
    }

}
