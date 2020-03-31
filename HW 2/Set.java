import java.util.Arrays;
import java.util.Objects;

public class Set<T> {
    private T[] a;
    private int size;
    private int maxsize = 10;

    public Set() {
        this.a = (T[]) new Object[this.maxsize];
        this.size = 0;
    }

    public Set(T[] a, int size, int maxsize) {
        this.a = a;
        this.size = size;
        this.maxsize = maxsize;
    }

    public Set(Set<T> o) {
        this.a = o.a;
        this.size = o.a.length - 1;
    }

    public boolean isHas(T el) {
        for (int i = 0; i < this.size; i++) {
            if (el == this.a[i]) {
                return true;
            }
        }
        return false;
    }

    public void add(T el) {
        if (!(this.isHas(el))) {
            this.a[this.size] = el;
            this.size++;
        }
    }

    public int size() {
        return this.size;
    }

    public void delete(T el) {
        for (int i = 0; i < this.a.length; i++) {
            if (this.a[i] == el) {
                this.a[i] = this.a[this.size - 1];
                this.a[this.size - 1] = null;
                this.size--;
                break;
            }
        }
    }

    public Set<T> merge(Set<T> o) {
        Set<T> newSet = new Set<>();
        newSet.a = (T[]) new Object[this.size + o.size];
        newSet.size = this.size;
        for (int i = 0; i < this.size; i++) {
            newSet.a[i] = this.a[i];
        }
        for (int i = 0; i < o.size; i++) {
            boolean f = true;
            for (int j = 0; j < this.size; j++) {
                if (o.a[i].equals(newSet.a[j])) {
                    f = false;
                    break;
                }
            }
            if (f) {
                newSet.add(o.a[i]);
            }
        }
        return newSet;
    }

    @Override
    public String toString() {
        return "Set{" +
                "a=" + Arrays.toString(a) +
                ", size=" + size +
                ", maxsize=" + maxsize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set<?> set = (Set<?>) o;
        return size == set.size &&
                maxsize == set.maxsize &&
                Arrays.equals(a, set.a);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, maxsize);
        result = 31 * result + Arrays.hashCode(a);
        return result;
    }
}
