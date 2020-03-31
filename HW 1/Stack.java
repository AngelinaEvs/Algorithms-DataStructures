import java.util.Arrays;

public class Stack {
    private int a[];
    private int size;
    private int maxsize = 10;

    public Stack() {
        this.a = new int[this.maxsize];
        this.size = 0;
    }

    public void add(int elem) {
        a[this.size] = elem;
        this.size++;
    }

    public int pop() {
        int c = this.a[this.size - 1];
        this.a[this.size - 1] = 0;
        this.size--;
        return c;
    }

    public int peek() {
        return this.a[this.size - 1];
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return "Stack: " + Arrays.toString(a) +
                ", size = " + size +
                ", maxsize = " + maxsize;
    }
}
