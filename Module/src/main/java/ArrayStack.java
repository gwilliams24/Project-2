public class ArrayStack <T> {
    private Object[] array;
    private int size;

    public ArrayStack(Object[] array, int size) {
        this.array = array;
        this.size = size;
    }

    // Equals method for ArrayStack: checks to see if stacks are the same
    public boolean equals(Object other) {
        switch (other) {
            case ArrayStack array_other:
                if (this.size != array_other.size || this.array.length != array_other.array.length) {
                    return false;
                }
                for (int i = 0; i < this.array.length; i += 1) {
                    if (!this.array[i].equals(array_other.array[i])) {
                        return false;
                    }
                }
                return true;
            case null, default:
                return false;
        }
    }

    // Double the capacity of the given array
    public void doubleCapacity() {
        if (size == array.length) {
            Object[] doubledArray = new Object[array.length * 2];
            for (int index = 0; index < size; index += 1) {
                doubledArray[index] = array[index];
            }
            array = doubledArray;
        }
    }

    // Creates an empty ArrayStack
    public static ArrayStack emptyStack() {
        return new ArrayStack(new Object[1], 0);
    }

    // Push a value onto the top of the stack
    public void push(T value) {
        if (size == array.length) {
            doubleCapacity();
        }
        array[size] = value;
        size += 1;
    }

    @SuppressWarnings("unchecked")
    // Returns and removes the top value of the stack
    public T pop() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException("Index Error: there is nothing left in the stack");
        } else {
            size -= 1;
            T value = (T) this.array[size];
            array[size] = null;
            return value;
        }
    }
    @SuppressWarnings("unchecked")
    // Returns the top value of the stack but does not remove it
    public T peek(){
        if (this.size == 0) {
            throw new IndexOutOfBoundsException("Index Error: there is nothing left in the stack");
        } else {
            T value = (T) this.array[size-1];
            return value;
        }
    }


    // Checks to see if the stack is empty or not
    public boolean isEmpty(){
        return (size == 0);
    }


    // Returns the size of the stack (number of elements in it)
    public int size(){
        return size;
    }

}