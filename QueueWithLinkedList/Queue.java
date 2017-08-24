import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    public void enqueue(Item item) {
        Node oldTail = last;

        last = new Node();
        last.item = item;

        if(isEmpty()) {
            first = last;
        }
        else {
            oldTail.next = last;
        }

        size++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;

        size--;

        if(isEmpty()) {
            last = null;
        }

        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class Node {
        Item item;
        Node next;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;

            return item;
        }

        public void remove() {

        }
    }
}
