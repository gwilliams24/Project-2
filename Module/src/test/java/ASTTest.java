import org.junit.jupiter.api.Test;

import javax.swing.text.html.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ASTTest {

    @Test
    void eval1() {
        assertEquals(50, Parser.parseInfix("2 + 3 * 4 * ( 4 - 2 ) ^ 2").eval());
    }

    @Test
    void eval2() {
        assertEquals(-8, Parser.parseInfix("4 / (2 - 3) * 2"));
    }

    @Test
    void eval3() {
        assertEquals(-8, Parser.parsePostfix("4 5 + 2 * 4 / - 6"));
    }
}
