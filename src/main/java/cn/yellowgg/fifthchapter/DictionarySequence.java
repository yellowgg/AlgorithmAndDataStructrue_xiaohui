package cn.yellowgg.fifthchapter;

import java.util.Arrays;

/**
 * @Author:黄广
 * @Description: 寻找全排列的下一个排列，其实也就是字典序算法
 * @Date: Created in 19-6-1 下午7:59
 */
public class DictionarySequence {

    public static int[] findNearesNum(int[] numbers) {
        //1.从后往前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
        int index = findTransferPoint(numbers);
        //如果数字置换边界是0，说明整个数组已经逆序，无法得到更大的相同数字组成的整数，返回null
        if (index == 0) {
            return null;
        }

        //2.把逆序区域的前一位和逆序区域中刚刚大于它的数字交换位置复制并入参，避免直接修改入参
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        exchangeHead(numbersCopy, index);

        //3.把原来的逆序区域转为顺序状态
        reverse(numbersCopy, index);
        return numbersCopy;
    }

    /**
     * 把逆序转换成顺序
     */
    private static int[] reverse(int[] num, int index) {
        for (int i = index, j = num.length - 1; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
        return num;
    }

    /**
     * 交换数字
     */
    private static int[] exchangeHead(int[] numbers, int index) {
        int head = numbers[index - 1];
        for (int i = numbers.length - 1; i > 0; i--) {
            if (head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    /**
     * 找到交换边界
     */
    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        //打印12345之后的10个全排列整数
        for (int i = 0; i < 10; i++) {
            numbers = findNearesNum(numbers);
            outputNumbers(numbers);
        }
    }

    /**
     * 输出数组
     */
    private static void outputNumbers(int[] numbers) {
        for (int i : numbers) {
            System.out.print(i);
        }
        System.out.println();
    }
}
