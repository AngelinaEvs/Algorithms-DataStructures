import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[sc.nextInt()];
        int[] array2 = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = sc.nextInt();
            array2[i] = array[i];
        }
        mergeSort(array, 0, array.length - 1);
        System.out.println("R: " + Arrays.toString(array));
        mergeSortNoRecursive(array2, 0, array.length - 1);
        System.out.println("NoR: " + Arrays.toString(array2));
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left >= right) return;
        int middle = (left + right) / 2;
        mergeSort(array, left, middle);
        mergeSort(array, middle + 1, right);
        merge(array, left, middle, right);
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int[] mergeArray = new int[array.length];
        int begin = left;
        int middlePlus = middle + 1;
        for (int i = left; i <= right; i++) {
            if ((begin <= middle) && (middlePlus > right || array[begin] < array[middlePlus])) {
                mergeArray[i] = array[begin];
                begin++;
            }
            else {
                mergeArray[i] = array[middlePlus];
                middlePlus++;
            }
        }
        if (right + 1 - left >= 0) System.arraycopy(mergeArray, left, array, left, right + 1 - left);
    }

    public static void mergeSortNoRecursive(int[] array, int left, int right) {
        for (int i = 1; i < right - left; i = i * 2) {
            for (int j = left; j < right - i; j = j + (2 * i)) {
                merge(array, j, j + i, Integer.min(j + (2 * i), right));
            }
        }
    }
}
