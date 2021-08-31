import java.util.NoSuchElementException;

/*****************************************************************
 * This class LinkedList implements linked data structures using
 * a Doubly Linked List (DLL).
 *****************************************************************/

public class LinkedList<E> implements List<E> {

    /*****************************************************************
     * State: data fields
     *        values for the final data fields are initialized.
     * ***************************************************************/
    private Node<E> first;
    private Node<E> last;
    private int     size;

    /*****************************************************************
     * Constructor: LinkedList()
     *              values for the final data fields are initialized.
     * ***************************************************************/
    public LinkedList() {
        first   = null;
        last    = null;
        size    = 0;
    }

    /*****************************************************************
     * method: add(E item)
     *         uses the append method
     * ***************************************************************/
    @Override
    public boolean add(E item) {
        int oldSize = size;
        append(item);
        size++;
        return size == oldSize + 1;
    }

    /*****************************************************************
     * method: add(int index, E item)
     *         inserts elements at a given location in the list
     * ***************************************************************/
    @Override
    public void add(int index, E item) {
        if (index == size) {
            append(item);
        } else {
            if (isEmpty()) {
                append(item);
            } else {
                checkIndex(index);
                insertBefore(index, item);
            }
        }
        size++;
    }

    /*****************************************************************
     * method: append(E item)
     *         appends elements to the end of the list
     * ***************************************************************/
    private void append(E item) {
        Node<E> oldLast = last;
        Node<E> newNode = new Node<E>(oldLast, item);
        last            = newNode;

        if(isEmpty())
            first = newNode;
        else
            oldLast.next = newNode;
    }

    /*****************************************************************
     * method: checkIndex(int index)
     *         checks if the given index is valid
     * ***************************************************************/
    private void checkIndex(int index) {
        String message = "Invalid Index: " + index;
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(message);
    }

    /*****************************************************************
     * method: clear()
     *         clears list of all elements
     * ***************************************************************/
    @Override
    public void clear() {
        first = null;
        last  = null;
        size  = 0;
    }

    /*****************************************************************
     * method: contains(E item)
     *         searches for a specific item within the linked structure
     * ***************************************************************/
    @Override
    public boolean contains(E item) {
        return indexOf(item) != -1;
    }

    /*****************************************************************
     * method: detach(int index)
     *         detaches the node at the specified index from list
     * ***************************************************************/
    private E detach(int index) {
        Node<E> current     = node(index);
        Node<E> nodeBefore  = current.prev;
        Node<E> nodeAfter   = current.next;
        E       data        = current.data;

        // detach current node form node before
        if(nodeBefore == null)
            first = nodeAfter;
        else
            nodeBefore.next = nodeAfter;

        // detach current node form node after
        if(nodeAfter == null)
            last = nodeBefore;
        else
            nodeAfter.prev = nodeBefore;

        // set current node for garbage collection
        current.next = null;
        current.prev = null;

        return data;
    }

    /*****************************************************************
     * method: get(int index)
     *         returns the item at the specified position in the list
     * ***************************************************************/
    @Override
    public E get(int index) {
        checkIndex(index);
        Node<E> current = node(index);
        return current.data;
    }

    /*****************************************************************
     * method: indexOf(E item)
     *         searches for a specific item within the linked structure
     *         and returns the first occurrence (location/index)
     *         in the array
     * ***************************************************************/
    @Override
    public int indexOf(E item) {
        int index = 0;
        if(!isEmpty()){
            for (Node<E> node = first; node != null; node = node.next, index++) {
                if (node.data.equals(item))
                    return index;
            }
        }
        return -1;
    }

    /*****************************************************************
     * method: insertBefore(int index, E item)
     *         inserts an item before the non-null node at the
     *         specified index in the list
     * ***************************************************************/
    private void insertBefore(int index, E item) {
        Node<E> current     = node(index);
        Node<E> nodeBefore  = current.prev;
        Node<E> newNode     = new Node<E>(nodeBefore, item);

        if (nodeBefore == null) {
            first = newNode;
        } else {
            current.prev        = newNode;
            nodeBefore.next     = newNode;
        }

        newNode.prev        = nodeBefore;
        newNode.next        = current;


    }

    /*****************************************************************
     * method: isEmpty()
     *         returns true, if the list is empty
     * ***************************************************************/
    @Override
    public boolean isEmpty() {
        return first == null && size == 0;
    }

    /*****************************************************************
     * method: iterator()
     *         returns an object used to traverse the elements in
     *         the list
     * ***************************************************************/
    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator();
    }

    /*****************************************************************
     * method: node(int index)
     *         returns a reference to the node at the given position
     *         in the list
     * ***************************************************************/
    private Node<E> node(int index) {
        Node<E> current;

        if (index < size / 2) {
            current = first;
            for (int i = 0; i < index; i++)
                current = current.next;

        } else {
            current = last;
            for (int i = size - 1; i > index; i--)
                current = current.prev;
        }
        return current;
    }

    /*****************************************************************
     * method: remove(int index)
     *         removes the item at the given position  in the list
     * ***************************************************************/
    @Override
    public E remove(int index) {
        checkIndex(index);
        E oldItem = detach(index);
        size--;
        return oldItem;
    }

    /*****************************************************************
     * method: remove(E item)
     *         removes the first occurrence of the item specified from
     *         the list, if present.
     * ***************************************************************/
    @Override
    public boolean remove(E item) {
        if(contains(item)){
            E oldItem = remove(indexOf(item));
            return item.equals(oldItem);
        }
        return false;
    }

    /*****************************************************************
     * method: set(int index, E item)
     *         replaces the item at the specified position with the
     *         one passed
     * ***************************************************************/
    @Override
    public E set(int index, E item) {
        checkIndex(index);

        Node<E> current = node(index);
        E oldItem       = current.data;
        current.data    = item;

        return oldItem;
    }

    /*****************************************************************
     * method: size()
     *         returns the number of elements in the list
     * ***************************************************************/
    @Override
    public int size() {
        return size;
    }

    /*****************************************************************
     * method: toString()
     *         displays the contents of the list
     * ***************************************************************/
    public String toString() {
        if (isEmpty())
            return "[]";
        else {
            StringBuilder result = new StringBuilder("[" + first.data);
            for (Node<E> node = first.next; node != null; node = node.next)
                result.append(", ").append(node.data);
            return result.append("]").toString();
        }
    }

    /*****************************************************************
     * Nested Class: Linked Iterator Inner Class
     *               implements methods of Iterator interface.
     * ***************************************************************/
    private class LinkedIterator implements Iterator<E> {

        Node<E>     current;
        int         position;
        boolean     isRemovable;

        public LinkedIterator() {
            current     = first;
            position    = 0;
            isRemovable = false;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public E next() {
            String message = "No more elements left!";
            if (!hasNext())
                throw new NoSuchElementException(message);

            E currentItem   = current.data;
            current         = current.next;
            position++;
            isRemovable     = true;
            return currentItem;
        }

        @Override
        public void remove() {
            String message = "Unable To Remove!";
            if(!isRemovable)
                throw new IllegalStateException(message);

            LinkedList.this.remove(--position);
        }

    }

    /*****************************************************************
     * Nested Class: Node
     *               private static inner class
     * ***************************************************************/
    private static class Node<E> {

        Node<E> prev;
        Node<E> next;
        E       data;

        public Node(Node<E> prev, E data) {
            this(prev, null, data);
        }

        public Node(Node<E> prev, Node<E> next, E data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }
}
