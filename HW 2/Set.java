import java.util.Arrays;
import java.util.Objects;

public class Set<T> {
    private T[] set;
    private int size;

    public Set() {
        set = (T[]) new Object[size];
    }

    public Set(T[] array) {
        set = (T[]) new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }

    public Set(Set<T> set) {
        this.set = set.set;
        this.size = set.size;
    }

    public boolean isHas(T el) {
        if (size == 0) return false;
        for (int i = 0; i < size; i++) {
            if (set[i].equals(el)) return true;
        }
        return false;
    }

    public void add(T el) {
        if (!(isHas(el))) {
            if (set.length == size) {
                size++;
                T[] newSet = (T[]) new Object[size*2];
                System.arraycopy(set, 0, newSet, 0, size - 1);
                newSet[size - 1] = el;
                set = newSet;
            }
            else {
                set[size] = el;
                size++;
            }
        }
    }

    public int size() {
        return size;
    }

    public void delete(T el) {
        for (int i = 0; i < size; i++) {
            if (set[i].equals(el)) {
                set[i] = set[size - 1];
                set[size - 1] = null;
                size--;
                break;
            }
        }
    }

    public Set<T> merge(Set<T> o) {
        Set<T> newSet = new Set<>();
        newSet.set = (T[]) new Object[this.size + o.size];
        newSet.size = this.size;
        for (int i = 0; i < this.size; i++) {
            newSet.set[i] = this.set[i];
        }
        for (int i = 0; i < o.size; i++) {
            boolean flag = true;
            for (int j = 0; j < this.size; j++) {
                if (o.set[i].equals(newSet.set[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) newSet.add(o.set[i]);
        }
        return newSet;
    }

    @Override
    public String toString() {
        return "Set{" +
                "set=" + Arrays.toString(set) +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set<?> set1 = (Set<?>) o;
        return size == set1.size &&
                Arrays.equals(set, set1.set);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(set);
        return result;
    }
}
