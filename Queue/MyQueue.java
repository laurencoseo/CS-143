/*****************************************************************
 * This class MyQueue implements a FIFO data structure using a
 * Singly Linked List (SLL).
 *****************************************************************/

public class MyQueue<E> {

    /*****************************************************************
     * State: data fields
     *        values for the final data fields are initialized.
     * ***************************************************************/
    private Node<E> first;      // front
    private Node<E> last;       // back
    private int     size;

    /*****************************************************************
     * Constructor: MyStack()
     *              values for the final data fields are initialized.
     * ***************************************************************/
    public MyQueue() {
        first  = null;
        last   = null;
        size   = 0;
    }

    /*****************************************************************
     * method: add(E item)
     *         places given element at back of queue.
     * ***************************************************************/
    public boolean add(E item) {
        int oldSize = size;
        append(item);
        size++;
        return size == (oldSize + 1);
    }

    /*****************************************************************
     * method: append(E item)
     *         helper method to "help" the add method append data
     *         to back of queue
     * ***************************************************************/
    private void append(E item) {
        Node<E> oldLast = last;
        Node<E> newNode = new Node<E>(item);
        last            = newNode;

        if(isEmpty())
            first = newNode;
        else
            oldLast.next = newNode;
    }

    /*****************************************************************
     * method: isEmpty()
     *         returns true, if queue has no elements
     * ***************************************************************/
    public boolean isEmpty() {
        return first == null && size() == 0;
    }

    /*****************************************************************
     * method: peek()
     *         returns front element from queue without removing
     *         it from the queue
     * ***************************************************************/
    public E peek() {
        if (isEmpty())
            return null;
        return first.data;
    }

    /*****************************************************************
     * method: remove()
     *         removes element from front of queue and returns it
     * ***************************************************************/
    public E remove() {
        if (isEmpty())
            return null;
        E firstItem      = first.data;
        Node<E> oldFirst = first;
        first            = first.next;
        oldFirst.next    =  null;
        size--;
        return firstItem;
    }

    /*****************************************************************
     * method: size()
     *         returns number of elements in queue.
     * ***************************************************************/
    public int size() {
        return size;
    }

    /*****************************************************************
     * method: toString()
     *         displays the contents of the queue from left (front)
     *         to right (back)
     * ***************************************************************/
    public String toString() {
        if (isEmpty())
            return "[]";
        else{
            StringBuilder result = new StringBuilder("[" + first.data);
            for (Node<E> node = first.next; node != null; node = node.next)
                result.append(", ").append(node.data);
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

        public Node(E data){
            this(null, data);
        }

        public Node(Node<E> next, E data){
            this.next = next;
            this.data = data;
        }
    }

}
