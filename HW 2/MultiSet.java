import java.util.Arrays;
import java.util.Objects;

public class MultiSet<T> {
    private T[] a;
    private int size;
    private int maxsize;
    private int[] count;

    public MultiSet() {
        a = (T[]) new Object[this.maxsize];
        size = 0;
    }

    public MultiSet(T[] a) {
        maxsize = a.length + 5;
        this.a = (T[]) new Object[maxsize];
        size = a.length;
        for (int i = 0; i <  a.length; i++) {
            this.a[i] = a[i];
        }
        count = new int[maxsize];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.a[i] == this.a[j]) {
                    count[i]++;
                }
            }
        }
    }

    public void add(T el) {
        a[size] = el;
        size++;
        int n = 1;
        for (int i = 0; i < size - 1; i++) {
            if (el == a[i]) {
                count[i]++;
                if (n != count[i]) {
                    n = count[i];
                }
            }
        }
        count[size - 1] = n;
    }

    public int size() {
        return size;
    }

    public void delete(T el) {
        for (int i = size - 1; i > -1; i--) {
            if (a[i] == el) {
                for (int j = i; j > -1; j--) {
                    if (count[i] == count[j]) {
                        count[j] = 0;
                    }
                }
                a[i] = a[size - 1];
                count[i] = count[size - 1];
                a[size - 1] = null;
                count[size - 1] = 0;
                size--;
            }
        }
    }

    public MultiSet merge(MultiSet<T> o) {
        MultiSet<T> newSet = new MultiSet<>();
        newSet.a = (T[]) new Object[this.size + o.size];
        newSet.count = new int[this.size + o.size];
        for (int i = 0; i < newSet.count.length; i++) {
            newSet.count[i] = 0;
        }
        for (int i = 0; i < this.size; i++) {
            newSet.a[i] = this.a[i];
            newSet.size++;
        }
        for (int i = newSet.size; i < newSet.a.length; i++) {
            for (int j = 0; j < o.size; j++) {
                newSet.a[i] = o.a[j];
                newSet.size++;
                i++;
            }
        }
        for (int i = 0; i < newSet.a.length; i++) {
            for (int j = 0; j < newSet.a.length; j++) {
                if (newSet.a[i] == newSet.a[j]) {
                    newSet.count[i]++;
                }
            }
        }
        newSet.maxsize = newSet.size;
        return newSet;
    }

    @Override
    public String toString() {
        return "Set{" +
                "a=" + Arrays.toString(a) +
                ", size=" + size +
                ", maxsize=" + maxsize +
                ", count=" + Arrays.toString(count) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiSet<?> multiSet = (MultiSet<?>) o;
        return size == multiSet.size &&
                Arrays.equals(a, multiSet.a) &&
                Arrays.equals(count, multiSet.count);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, maxsize);
        result = 31 * result + Arrays.hashCode(a);
        result = 31 * result + Arrays.hashCode(count);
        return result;
    }
}