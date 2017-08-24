import java.util.Comparator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Μπάμπης Μπιλλίνης on 19/5/2017.
 */
public class BinarySearchTree<Item extends Comparable<Item>> {
    private Node root;
    private int size;
    private LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<Node>();

    /**
     *  Adds a Item in the binary search tree.
     * @param item The item to be added.
     */
    public void add(Item item) {
        Node fresh = new Node();
        fresh.item = item;

        if(root == null) {
            root = fresh;
        }
        else {
            add(root, fresh);
        }

        size++;
    }


    /**
     * Removes a specified Item from the binary search tree.
     * The compareTo method is be used.
     * @param item The Item to be removed.
     * @return true if the item has been found and removed successfully, otherwise false.
     */
    public boolean remove(Item item) {
        boolean res = remove(find(item));
        if(res) {
            size--;
        }

        return res;
    }

    private boolean remove(SearchResult res) {
        if(res.child == null) {
            return false;
        }

        Node fresh = null;
        if(res.child.hasOneChildren()) {
            if(res.child.hasLeftChildren()) {
                fresh = res.child.left;
            }
            else {
                fresh = res.child.right;
            }
        }
        else if(res.child.hasTwoChildren()) {
            SearchResult tmpRes = successor(res.child);
            fresh = tmpRes.child;
            fresh.left = res.child.left;
            Node oldRight = fresh.right;
            if(fresh != res.child.right)
                fresh.right = res.child.right;
            else
                fresh.right = null;
            tmpRes.parent.left = oldRight;
        }

        if(res.parent == null) {
            root = fresh;
            return true;
        }

        if(res.child.compareTo(res.parent) > 0) {
            res.parent.right = fresh;
        }
        else {
            res.parent.left = fresh;
        }



        return true;
    }

    /**
     * Check whether the Item is contained in the binary search tree.
     * @param item The Item to be checked if exist in the bst.
     * @return true if the item exist in the bst, false otherwise
     */
    private boolean contains(Item item) {
        return find(item).child != null;
    }

    private SearchResult find(Item item) {
        return find(item, (e,v) -> {return e.compareTo(v);});
    }

    // care from where you call it.. root.right might be null
    private SearchResult successor(Node root) {
        SearchResult res = new SearchResult();
        if(root == null) {
            return res;
        }
        res.parent = root;
        return successor(res, root.right);
    }

    private SearchResult successor(SearchResult res, Node root) {
        if(root.hasLeftChildren()) {
            res.parent = root;
            return  successor(res, root.left);
        }

        res.child = root;
        return res;
    }

    private SearchResult find(Item item, Comparator<Item> comp) {
        return find(new SearchResult(), root, item, comp);
    }

    private SearchResult find(SearchResult res, Node root, Item item, Comparator<Item> comp) {
        if(root == null)
            return res;

        if(item.compareTo(root.item) == 0) {
            res.child = root;
            return res;
        }

        res.parent = root;

        if(item.compareTo(root.item) > 0) {
            if(root.hasRightChildren()) {
                return find(res, root.right, item, comp);
            }

            return res;
        }

        if(root.hasLeftChildren()) {
            return find(res, root.left, item, comp);
        }

        return res;
    }

    private void add(Node root, Node node) {
        if(node.compareTo(root) > 0) {
            if(!root.hasRightChildren()) {
                root.right = node;
            }
            else {
                add(root.right, node);
            }
        }
        else if(node.compareTo(root) < 0) {
            if(!root.hasLeftChildren()) {
                root.left = node;
            }
            else {
                add(root.left, node);
            }
        }
    }

    /**
     * Retuns the number of elements in the bst.
     * @return the number of elements.
     */
    public int size() {
        return size;
    }

    /**
     * Check whether the bst has no elements.
     * @return true if this collection contains no elements.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Traverse the bst in levelorder
     * @param action The action to be performed.
     */
    public void levelOrder(Actionator<Item> action) {
        levelOrder(root, action);
    }

    private void levelOrder(Node root, Actionator<Item> action) {
        action.run(root.item);

        if(root.hasLeftChildren())
            queue.add(root.left);

        if(root.hasRightChildren())
            queue.add(root.right);

        if(!queue.isEmpty())
            levelOrder(queue.remove(), action);
    }

    private class SearchResult {
        Node parent;
        Node child;
    }

    private class Node implements Comparable<Node> {
        Node left;
        Node right;
        Item item;

        @Override
        public int compareTo(Node o2) {
            return item.compareTo(o2.item);
        }

        public boolean hasLeftChildren() {
            return left != null;
        }
        public boolean hasRightChildren() {
            return right != null;
        }

        public boolean hasTwoChildren() {
            return hasRightChildren() & hasRightChildren();
        }

        public boolean hasOneChildren() {
            return hasLeftChildren() ^ hasRightChildren();
        }

        public boolean isLeaf() {
            return !hasChildren();
        }

        public boolean hasChildren() {
            return hasLeftChildren() || hasRightChildren();
        }
    }
}
