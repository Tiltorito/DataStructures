import java.util.Arrays;

public class Union {
    private int[] id;
    private int[] size;

    public Union(int n) {
        id = new int[n];
        size = new int[n];
        for(int i = 0;i < n;i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p,int q) {
        int rootA = findRoot(p);
        int rootB = findRoot(q);

        if(rootA == rootB) { // If they are already connected
            return;
        }

        if(size[rootA] > size[rootB]) {
            id[rootB] = rootA;
            size[rootA] += size[rootB];

        }
        else {
            id[rootA] = rootB;
            size[rootB] += size[rootA];

        }
    }

	
    public boolean connected(int p,int q) {
        int rootA = findRoot(p);
        int rootB = findRoot(q);

        return rootA == rootB;
    }

	
    private int findRoot(int p) {
        while(p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }

        return p;
    }

    @Override
    public String toString() {
        return Arrays.toString(id);
    }
}
