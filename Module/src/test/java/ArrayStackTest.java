import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStackTest {

    ArrayStack<Object> array1 = new ArrayStack<>(new String[]{"a", "b", "c", "d"}, 4);
    ArrayStack<Object> array2 = new ArrayStack<>(new Integer[]{1, 2, 3, 4}, 4);
    ArrayStack<Object> array3 = new ArrayStack<>(new String[]{"d", "c", "b", "a"}, 4);
    ArrayStack<Object> array4 = new ArrayStack<>(new String[1], 0);
    ArrayStack<Object> array5 = new ArrayStack<>(new String[]{"a", "b", "c", "d", null, null, null, null}, 4);
    ArrayStack<Object> array6 = new ArrayStack<>(new String[]{"a", "b", "c", null}, 3);
    ArrayStack<Object> array7 = new ArrayStack<>(new String[]{"a", "b", "c", null}, 4);

    @Test
    void empty_stack1() {
        assertEquals(array4, ArrayStack.emptyStack());
    }

    @Test
    void equals1() {
        assertEquals(array1, array1);
    }

    @Test
    void equals2() {
        assertNotEquals(array1, array3);
    }

    @Test
    void equals3() {
        assertNotEquals(array1, null);
    }

    @Test
    void equals4() {
        assertNotEquals(array1, "fakeArray");
    }

    @Test
    void equals5() {
        assertNotEquals(array1, array5);
    }

    @Test
    void equals6() {
        assertNotEquals(array1, array6);
    }

    @Test
    void equals7() {
        assertNotEquals(array1, array7);
    }

    @Test
    void equals8() {
        assertNotEquals(array7, array1);
    }

    @Test
    void push1() {
        array1.push("push");
        assertEquals(new ArrayStack<>(new String[]{"a", "b", "c", "d", "push", null, null, null}, 5), array1);
    }

    @Test
    void push2() {
        array2.push(5);
        assertEquals(new ArrayStack<>(new Integer[]{1, 2, 3, 4, 5, null, null, null}, 5), array2);
    }

    @Test
    void push3() {
        array5.push("push");
        assertEquals(new ArrayStack<>(new String[]{"a", "b", "c", "d", "push", null, null, null}, 5), array5);
    }

    @Test
    void pop1() {
        array2.pop();
        assertEquals(new ArrayStack<>(new Integer[]{1, 2, 3, null}, 3), array2);
    }

    @Test
    void pop2() {
        assertThrows(IndexOutOfBoundsException.class, () -> array4.pop());
    }

    @Test
    void peek1() {
        assertEquals(4, array2.peek());
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


