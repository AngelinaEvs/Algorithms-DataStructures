import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {3, 11, -1, 8};
        bubbleSort(array);
        System.out.println("Array after bubble sorting: " + Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {
        boolean flag = true;
        int tmp;

        while (flag) {
            flag = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                    flag = true;
                }
            }
        }
    }
}

