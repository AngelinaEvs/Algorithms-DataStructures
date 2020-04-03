import java.util.Arrays;
import java.util.Objects;

public class MultiSet<T> {
    private T[] multiSet;
    private int[] count;
    private int size;

    public MultiSet() {
        size = 0;
        multiSet = (T[]) new Object[size];
        count = new int[size];
    }

    public MultiSet(T[] array) {
        this();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                add(array[i]);
            }
        }
    }

    public MultiSet(MultiSet<T> multiSet) {
        this.multiSet = multiSet.multiSet;
        this.count = multiSet.count;
        this.size = multiSet.size;
    }

    public boolean add(T el) {
        if (el == null) return false;
        for (int i = 0; i < size; i++) {
            if (el.equals(multiSet[i])) {
                count[i]++;
                return true;
            }
        }
        if (size == multiSet.length) {
            size++;
            T[] newMultiSet = (T[]) new Object[size * 2];
            int[] newCount = new int[size * 2];
            System.arraycopy(multiSet, 0, newMultiSet, 0, size - 1);
            System.arraycopy(count, 0, newCount, 0, size - 1);
            newMultiSet[size - 1] = el;
            newCount[size - 1]++;
            multiSet = newMultiSet;
            count = newCount;
            return true;
        }
        else {
            multiSet[size] = el;
            count[size]++;
            size++;
            return true;
        }
    }

    public int size() {
        return size;
    }

    public void delete(T el) {
        for (int i = 0; i < size; i++) {
            if (el.equals(multiSet[i])) {
                count[i]--;
                if (count[i] == 0) {
                    if (i == size - 1) {
                        multiSet[i] = null;
                        size--;
                    }
                    else {
                        multiSet[i] = multiSet[size - 1];
                        count[i] = count[size - 1];
                        multiSet[size - 1] = null;
                        count[size - 1] = 0;
                        size--;
                    }
                }
            }
        }
    }

    public MultiSet merge(MultiSet<T> o) {
        MultiSet<T> newMultiSet = new MultiSet<>(this);
        for (int i = 0; i < o.size; i++) {
            for (int j = 0; j < o.count[i]; j++) {
                newMultiSet.add(o.multiSet[i]);
            }
        }
        return newMultiSet;
    }

    @Override
    public String toString() {
        return "MultiSet{" +
                "multiSet=" + Arrays.toString(multiSet) +
                ", count=" + Arrays.toString(count) +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
           MultiSet<T> that = (MultiSet<T>) o;
        if (size != that.size) return false;
        boolean f = true;
        for (int i = 0; i < this.size; i++) {
            if (!f) break;
            for (int j = i; j < that.size; j++) {
                if (this.multiSet[i].equals(that.multiSet[j])) {
                    if (this.count[i] != that.count[j]) return false;
                    T el = that.multiSet[j];
                    int val = that.count[j];
                    that.multiSet[j] = that.multiSet[i];
                    that.count[j] = that.count[i];
                    that.count[i] = val;
                    that.multiSet[i] = el;
                    break;
                }
                if (j == that.size - 1 && !(multiSet[i].equals(that.multiSet[j]))) {
                    f = false;
                    break;
                }
            }
        }
        return f;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(multiSet);
        result = 31 * result + Arrays.hashCode(count);
        return result;
    }
}
