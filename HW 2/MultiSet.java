import java.util.Arrays;
import java.util.Objects;

public class MultiSet<T>{//набор, множество; события, информация
    private T[] a;
    private int size;
    private int maxsize = 10;
    private int[] count;

    public MultiSet() {
        this.a = (T[]) new Object[this.maxsize];
        this.size = 0;
    }

    public MultiSet(T[] a, int size, int maxsize) {
        this.a = (T[]) new Object[this.maxsize];
        for (int i = 0; i < a.length; i++) {
            this.a[i] = a[i];
        }
        this.size = size;
        this.maxsize = maxsize;
        this.count = new int[maxsize];
        for (int i = 0; i < maxsize; i++) {
            this.count[i] = 0;
        }
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.a[i] == this.a[j]) {
                    this.count[i]++;
                }
            }
        }
    }

    public MultiSet(T[] a) {
        this.a = (T[]) new Object[maxsize];
        this.size = a.length;
        for (int i = 0; i <  a.length; i++) {
            this.a[i] = a[i];
        }
        this.count = new int[maxsize];
        for (int i = 0; i < maxsize; i++) {
            this.count[i] = 0;
        }
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.a[i] == this.a[j]) {
                    this.count[i]++;
                }
            }
        }
    }

    public void add(T el) {
        this.a[this.size] = el;
        this.size++;
        int n = 1;
        for (int i = 0; i < this.size - 1; i++) {
            if (el == this.a[i]) {
                this.count[i]++;
                if (n != this.count[i]) {
                    n = this.count[i];
                }
            }
        }
        this.count[this.size - 1] = n;
    }

    public int size() {
        return this.size;
    }

    public void delete(T el) {//удаляем конкретный элемент // remove
        for (int i = this.size - 1; i > -1; i--) {
            if (this.a[i] == el) {
                for (int j = i; j > -1; j--) {
                    if (this.count[i] == this.count[j]) {
                        this.count[j] = 0;
                    }
                }
                this.a[i] = this.a[this.size - 1];
                this.count[i] = this.count[this.size - 1];
                this.a[this.size - 1] = null;
                this.count[this.size - 1] = 0;
                this.size--;
            }
        }
    }

    public MultiSet merge(MultiSet<T> o) {
        MultiSet<T> newSet = new MultiSet<>();//(a, size, maxsize);
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

    /*public boolean isHas(T el) {//contains
        for (int i = 0; i < this.size; i++) {
            if (el == this.a[i]) {
                return true;
            }
        }
        return false;
    }*/


}

/*Multiset - набор, в котором могут быть повторы[множество повторов событий]
- сделать доп массив (1 - данные data, 2 - счетчики counters ++ add, -- del)
интерфейс тот же
можно сделать int has(el)||int countOf(el) -> O(n)
 */
