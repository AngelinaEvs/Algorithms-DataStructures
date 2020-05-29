import java.util.Arrays;
import java.util.Random;

public class Karatsuba {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int a = random.nextInt(10000);
            int b = random.nextInt(10000);
            System.out.println(a * b + " " + multiply(a, b));
        }
    }

    public static int multiply(int a, int b) {
        String aString = Integer.toBinaryString(a);
        boolean[] firstNumber = new boolean[aString.length()];
        for (int i = 0; i < aString.length(); i++) {
            if (aString.charAt(i) == '1') firstNumber[firstNumber.length - i - 1] = true;
        }
        String bString = Integer.toBinaryString(b);
        boolean[] secondNumber = new boolean[bString.length()];
        for (int i = 0; i < bString.length(); i++) {
            if (bString.charAt(i) == '1') secondNumber[secondNumber.length - i - 1] = true;
        }
        boolean[] product = multiply(firstNumber, secondNumber);
        StringBuilder productString = new StringBuilder();
        for (int i = product.length - 1; i >= 0; i--) {
            if (product[i]) productString.append('1');
            else productString.append('0');
        }
        return Integer.parseInt(productString.toString(), 2);
    }

    private static boolean[] multiply(boolean[] firstNumber, boolean[] secondNumber) {
        if (firstNumber.length == 1) {
            boolean[] product = new boolean[secondNumber.length];
            if (firstNumber[0]) System.arraycopy(secondNumber, 0, product, 0, product.length);
            return product;
        }
        if (secondNumber.length == 1) {
            boolean[] product = new boolean[firstNumber.length];
            if (secondNumber[0]) System.arraycopy(firstNumber, 0, product, 0, product.length);
            return product;
        }
        int maxLength = Math.max(firstNumber.length, secondNumber.length);
        boolean[] firstRight = new boolean[maxLength / 2];
        boolean[] firstLeft = new boolean[maxLength - maxLength / 2];
        boolean[] secondRight = new boolean[maxLength / 2];
        boolean[] secondLeft = new boolean[maxLength - maxLength / 2];
        System.arraycopy(firstNumber, 0, firstRight, 0, Integer.min(maxLength / 2, firstNumber.length));
        System.arraycopy(secondNumber, 0, secondRight, 0, Integer.min(maxLength / 2, secondNumber.length));
        if (firstNumber.length >= maxLength / 2)
            System.arraycopy(firstNumber, maxLength / 2, firstLeft, 0, Integer.min(maxLength, firstNumber.length) - maxLength / 2);
        if (secondNumber.length >= maxLength / 2)
            System.arraycopy(secondNumber, maxLength / 2, secondLeft, 0, Integer.min(maxLength, secondNumber.length) - maxLength / 2);
        boolean[] term1 = multiply(firstLeft, secondLeft);
        boolean[] term2 = multiply(add(firstLeft, firstRight), add(secondLeft, secondRight));
        boolean[] term3 = multiply(firstRight, secondRight);
        return add(shift(term1, 2 * (maxLength / 2)), add(shift(sub(term2, add(term1, term3)), maxLength / 2), term3));
    }

    private static boolean[] add(boolean[] firstNumber, boolean[] secondNumber) {
        boolean[] largest = firstNumber.length > secondNumber.length ? firstNumber : secondNumber;
        int max = Integer.max(firstNumber.length, secondNumber.length);
        int min = Integer.min(firstNumber.length, secondNumber.length);
        boolean[] sum = new boolean[max + 1];
        int index = 0;
        int count = 0;
        for (int i = 0; i < min; i++) {
            if (firstNumber[i]) count++;
            if (secondNumber[i]) count++;
            if (count % 2 == 1) {
                sum[i] = true;
                index = i;
            }
            count = count > 1 ? 1 : 0;
        }
        for (int i = min; i < max; i++) {
            if (largest[i]) count++;
            if (count == 1) {
                sum[i] = true;
                index = i;
            }
            count = count == 2 ? 1 : 0;
        }
        if (count == 1) {
            sum[max] = true;
            index = max;
        }
        if (index != max) return Arrays.copyOfRange(sum,0,index + 1);
        return sum;
    }

    private static boolean[] sub(boolean[] firstNumber, boolean[] secondNumber) {
        boolean[] sub = new boolean[firstNumber.length];
        int count = 0;
        int index = 0;
        for (int i = 0; i < firstNumber.length; i++) {
            if (firstNumber[i]) count++;
            if (i < secondNumber.length && secondNumber[i]) count--;
            if (count == 1) {
                sub[i] = true;
                index = i;
                count = 0;
            }
            if (count < 0) {
                if (count == -1) {
                    sub[i] = true;
                    index = i;
                }
                count = -1;
            }
        }
        if (index != sub.length - 1) return Arrays.copyOfRange(sub,0,index + 1);
        return sub;
    }

    private static boolean[] shift(boolean[] number, int k) {
        boolean[] result = new boolean[number.length + k];
        System.arraycopy(number, 0, result, k, result.length - k);
        return result;
    }
}
