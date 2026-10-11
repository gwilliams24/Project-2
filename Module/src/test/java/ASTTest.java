import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ASTTest {

    @Test
    void InFix_eval1() {
        assertEquals(50, (Parser.parseInfix("2 + 3 * 4 * ( 4 - 2 ) ^ 2")).eval());
    }

    @Test
    void InFix_eval2() {
        assertEquals(-8, (Parser.parseInfix("4 / ( 2 - 3 ) * 2")).eval());
    }

    @Test
    void InFix_eval3(){assertEquals(67, Parser.parseInfix("( 2 ^ 3 ) ^ 2 + ( 3 * 2 ) / 2").eval());}

    @Test
    void eval3() {
        assertEquals(3, (Parser.parsePostfix("4 1 2 4 6 + * ^ -").eval()));
    }
}
