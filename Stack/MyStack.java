import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/*****************************************************************
 * This class MyStack implements a LIFO data structure using a
 * Singly Linked List (SLL).
 *****************************************************************/

public class MyStack<E> {

    /*****************************************************************
     * State: data fields
     *        values for the final data fields are initialized.
     * ***************************************************************/
    private Node<E> first;          // bottom
    private Node<E> last;           // top
    private int     size;

    /*****************************************************************
     * Constructor: MyStack()
     *              values for the final data fields are initialized.
     * ***************************************************************/
    public MyStack() {
        first  = null;
        last   = null;
        size   = 0;
    }

    /*****************************************************************
     * method: isEmpty()
     *         tests if stack is empty
     * ***************************************************************/
    public boolean isEmpty() {
        return last == null && size() == 0;
    }

    /*****************************************************************
     * method: peek()
     *         returns the element at the top of the stack without
     *         removing it from the stack
     * ***************************************************************/
    public E peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return last.data;
    }

    /*****************************************************************
     * method: pop()
     *         removes the element at the top of the stack and
     *         returns it
     * ***************************************************************/
    public E pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is Empty!");
        E lastItem      = last.data;
        if (size == 1) {
            last            = null;
        } else {
            Node<E> oldLast = last;
            last            = last.next;
            oldLast.next    = null;
        }
        size--;
        return lastItem;
    }

    /*****************************************************************
     * method: push(E item)
     *         places given element on top of stack
     * ***************************************************************/
    public E push(E item) {
        Node<E> oldLast = last;
        Node<E> newNode = new Node<E>(oldLast, item);
        last            = newNode;

        if(isEmpty())
            first = newNode;

        size++;
        return last.data;
    }

    /*****************************************************************
     * method: size()
     *         returns number of elements in stack
     * ***************************************************************/
    public int size() {
        return size;
    }

    /*****************************************************************
     * method: toString()
     *         displays the contents of the stack from left (top)
     *         to right (bottom)
     * ***************************************************************/
    public String toString() {
        if (isEmpty())
            return "[]";
        else {
            ArrayList<E> list = new ArrayList<E>();
            for (Node<E> node = last; node != first; node = node.next)
                list.add(node.data);
            int lastIndex = size - 1;

            StringBuilder result = new StringBuilder("[" + list.get(lastIndex));
            for (int i = lastIndex - 1; i >= 0; i--) {
                result.append(", ").append(list.get(i));
            }
            return result.append("]").toString();
        }
    }

    /*****************************************************************
     * Nested Class: Node
     *               private static inner class
     * ***************************************************************/
    private static class Node<E> {

        Node<E> next;
        E       data;

        public Node(Node<E> next, E data) {
            this.next = next;
            this.data = data;
        }
    }

}
