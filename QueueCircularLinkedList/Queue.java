

public class Queue<Item> {

    private Node last;
    private int size;

    public void enqueue(Item item) {
        if(isEmpty()) {
            last = new Node();
            last.item = item;
            last.next = last;
            size++;
            return;
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = oldLast.next; // this is the first
        oldLast.next = last;

        size++;
    }

    public Item dequeue() {
        if(isEmpty()) {
            return null;
        }
        Item item = last.next.item; // the first
        last.next = last.next.next;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    private class Node {
        Item item;
        Node next;
    }
}
