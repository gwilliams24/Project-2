import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ASTTest {

    // Post fix tests:
    @Test
    void Postfix_eval1() {
        assertEquals(3, (Parser.parsePostfix("4 1 2 4 6 + * ^ -").eval()));
    }

    @Test
    void Postfix_eval2(){assertEquals(1025, Parser.parsePostfix("1 2 30 4 5 * - ^ +").eval());}

    @Test
    void Postfix_eval3(){assertEquals(3, Parser.parsePostfix("4 12 4 8 + / -").eval());}

    @Test
    void Postfix_eval_empty(){assertThrows(IllegalArgumentException.class,
            ()-> Parser.parsePostfix(null));}

    @Test
    void Postfix_eval_too_many_operands(){assertThrows(IllegalArgumentException.class,
            () -> Parser.parsePostfix("2 3 4 +"));}

    @Test
    void Postfix_eval_insufficient_operands(){assertThrows(IllegalArgumentException.class,
            () -> Parser.parsePostfix("2 +"));}

    @Test
    void Postfix_eval_invalid_token(){assertThrows(IllegalArgumentException.class,
            () -> Parser.parsePostfix("2 a +"));}



    // In fix tests:
    @Test
    void Infix_eval1() {
        assertEquals(50, (Parser.parseInfix("2 + 3 * 4 * ( 4 - 2 ) ^ 2")).eval());
    }

    @Test
    void Infix_eval2() {
        assertEquals(-8, (Parser.parseInfix("4 / ( 2 - 3 ) * 2")).eval());
    }

    @Test
    void Infix_eval3(){assertEquals(67, Parser.parseInfix("( 2 ^ 3 ) ^ 2 + ( 3 * 2 ) / 2").eval());}

    @Test
    void Infix_eval_4(){assertEquals(12.5, Parser.parseInfix("2 / 4 + 6 * 2").eval());}

    @Test
    void Infix_eval_empty() {
        assertThrows(IllegalArgumentException.class,
                () -> Parser.parseInfix(null));
    }

    @Test
    void Infix_eval_invalid_token(){assertThrows(IllegalArgumentException.class,
            () -> Parser.parseInfix("1 + a"));}

    @Test
    void Infix_eval_insufficient_operands(){assertThrows(IllegalArgumentException.class,
        () -> Parser.parseInfix("1 + 2 *"));
    }

    @Test
    void Infix_eval_too_many_operands(){assertThrows(IllegalArgumentException.class,
            () -> Parser.parseInfix("1 + 2 3"));
    }

    @Test
    void Infix_eval_mismatched_open_paren(){assertThrows(IllegalArgumentException.class,
            () -> Parser.parseInfix("1 + (2 * 3"));}

    @Test
    void Infix_eval_mismatched_closed_paren(){assertThrows(IllegalArgumentException.class,
            () -> Parser.parseInfix("1 + 2 )"));}

}
