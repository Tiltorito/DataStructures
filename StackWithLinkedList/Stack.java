import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    public void push(Item item) {
        Node oldFirst = first;

        first = new Node();
        first.item = item;
        first.next = oldFirst;

        size++;
    }

    public Item pop() {

        Item item = first.item;
        first = first.next;

        size--;

        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        Item item;
        Node next;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
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
