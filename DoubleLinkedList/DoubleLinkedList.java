import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by Μπάμπης Μπιλλίνης on 19/5/2017.
 */
public class DoubleLinkedList<Item extends Comparable<Item>> implements Iterable<Item> {
    private Node first;
    private int size;

    public void add(Item item) {
        if(first == null) { // or if isEmpty()
            first = new Node();
            first.item = item;
        }
        else {
            Node curr = first;
            Node currPrev = null;

            while (curr != null && item.compareTo(curr.item) > 0) {
                currPrev = curr;
                curr = curr.next;
            }

            Node fresh = new Node();
            fresh.item = item;

            if (curr == null) { // Then we add it in the end of the list
                currPrev.next = fresh;
                fresh.prev = currPrev;
            }
            else {
                fresh.prev = curr.prev;
                fresh.next = curr;
                curr.prev = fresh;

                if (currPrev == null) { // if currPrev == null then we should add it in the start of the list
                    first = fresh;
                }
                else {
                    currPrev.next = fresh;
                }
            }
        }
        size++;
    }

    public void traverse(Actionator<Item> action) {
        Node curr = first;

        while(curr != null) {
            action.run(curr.item);
            curr = curr.next;
        }
    }

    public boolean remove(Item item) {
        Node curr = first;

        while(curr != null && item.compareTo(curr.item) != 0) {
            curr = curr.next;
        }

        if(curr == null) {
            return false;
        }

        remove(curr);
        return true;
    }

    public boolean remove(int index) {
        rangeCheck(index);

        Node curr = first;
        for(int i = 0; i < index; i++) {
            curr = curr.next;
        }

        remove(curr);
        return true;
    }

    private void remove(Node curr) {
        if(curr == first) { // if its the first Node
            first = curr.next;
            first.prev = null;
        }
        else if(curr.next == null) { // if it's the last Node
            curr.prev.next = null;
        }
        else {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }

        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOf(Item item) {
        return indexOf(item, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public int indexOf(Item item, Comparator<Item> comp) {
        Node curr = first;

        int index = -1;
        int ctr = 0;

        while(curr != null) {
            if(comp.compare(curr.item,item) == 0) {
                index = ctr;
                break;
            }
            curr = curr.next;
            ctr++;
        }

        return index;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private void rangeCheck(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private class ListIterator implements Iterator<Item> {
        private Node curr = first;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Item next() {
            Item item = curr.item;
            curr = curr.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    private class Node {
        Node prev;
        Node next;
        Item item;
    }
}
