import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStackTest {

    ArrayStack<Object> array1 = new ArrayStack<>(new String[]{"a", "b", "c", "d"}, 4);
    ArrayStack<Object> array2 = new ArrayStack<>(new String[]{"A", "B", "C", "D", "E"}, 5);
    ArrayStack<Object> array3 = new ArrayStack<>(new String[]{"d", "c", "b", "a"}, 4);
    ArrayStack<Object> array4 = new ArrayStack<>(new String[1], 0);
    ArrayStack<Object> array5 = new ArrayStack<>(new String[]{"a", "b", "c", "d", null, null, null, null}, 4);

    @Test
    void empty_stack1() {
        assertEquals(array4, ArrayStack.emptyStack());
    }

    @Test
    void push1() {
        array1.push("push");
        assertEquals(new ArrayStack<>(new String[]{"a", "b", "c", "d", "push", null, null, null}, 5), array1);
    }

    @Test
    void push2() {
        array5.push("push");
        assertEquals(new ArrayStack<>(new String[]{"a", "b", "c", "d", "push", null, null, null}, 5), array5);
    }

    @Test
    void pop1() {
        array1.pop();
        assertEquals(new ArrayStack<>(new String[]{"a", "b", "c", null}, 3), array1);
    }

    @Test
    void pop2() {
        assertThrows(IndexOutOfBoundsException.class, () -> array4.pop());
    }

    @Test
    void peek1() {
        assertEquals("D", array2.peek());
    }

    @Test
    void peek2() {
        assertThrows(IndexOutOfBoundsException.class, () -> array4.peek());
    }

    @Test
    void size1() {
        assertEquals(4, array5.size());
    }

    @Test
    void size2() {
        assertEquals(0, array4.size());
    }

    @Test
    void is_empty1() {
        assertTrue(array4.isEmpty());
    }

    @Test
    void is_empty2() {
        assertFalse(array3.isEmpty());
    }

}


