import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
    private Item[] arr;
    private int n;

    public Stack() {
        arr = (Item[]) new Object[1];
        n = 0;
    }

    public void push(Item item) {
        if(n == arr.length) {
            resize(arr.length*2);
        }

        arr[n++] = item;
    }

    public Item pop() {
        Item item = arr[--n];
        arr[n] = null;

        if(n > 0 && n == arr.length/4) {
            resize(arr.length/2);
        }
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int index = n - 1;

        public boolean hasNext() {
            return index >= 0;
        }

        public Item next() {
            return arr[index--];
        }

        public void remove() {

        }
    }

    private void resize(int newSize) {
        Item[] newArr = (Item[]) new Object[newSize];
        System.arraycopy(arr,0,newArr,0,n);
        arr = newArr;
    }
}
