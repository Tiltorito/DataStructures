import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size;
    private int first;
    private int last;

    public Queue() {
        arr = (Item[]) new Object[1];
    }

    public void enqueue(Item item) {
        if(isFull()) {
            resize(arr.length * 2);
        }

        if(++last == arr.length) {
            last = 0;
        }

        arr[last] = item;
        size++;
    }

    public Item dequeue() {
        if(isEmpty()) {
            return null;
        }

        Item item = arr[first];
        arr[first] = null;
        size--;

        if(++first == arr.length) {
            first = 0;
        }

        if(size > 0 && size == arr.length / 4) {
            resize(arr.length / 4);
        }

        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private int index = 0;

        public boolean hasNext() {
            return index < size;
        }

        public Item next() {
            return arr[ (first + index++) % arr.length];
        }

        public void remove() {

        }
    }
    private void resize(int newSize) {
        Item[] newArr = (Item[]) new Object[newSize];
        for(int i = first,ctr = 0; i <= last; i++) {
            if(i == arr.length) {
                i = 0;
            }
            newArr[ctr++] = arr[i];
        }
        arr = newArr;
        first = 0;
        last = size - 1;
    }

}
