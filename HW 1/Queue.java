import java.util.Arrays;

public class Queue<T> {
    private T[] a;
    private int finile;
    private int maxsize = 10;
    private int first;

    public Queue() {
        this.a = (T[]) new Object[this.maxsize];
        this.finile = 0;
        this.first = 0;
    }

    public void add(T elem) {
        if (this.a[maxsize - 1] != null) {
            for (int i = 0; i < a.length; i++) {
                if (first != a.length - 1) {
                    a[i] = a[first];
                    first++;
                }
                else {
                    a[i] = null;
                    finile--;
                }
            }
            first = 0;
        }
        a[this.finile] = elem;
        this.finile++;
    }

    public T poll() {
        T c = this.a[this.first];
        this.a[this.first] = null;
        this.first++;
        return c;
    }

    public T peek() {
        return this.a[this.first];
    }

    public int size() {
        return this.finile - this.first;
    }

    @Override
    public String toString() {
        return "Queue{" +
                "a=" + Arrays.toString(a) +
                ", size=" + (finile - first) +
                ", maxsize=" + maxsize +
                ", length=" + a.length +
                '}';
    }
}

