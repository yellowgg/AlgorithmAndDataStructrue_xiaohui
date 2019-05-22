package cn.yellowgg.fourthchapter;

import java.util.Arrays;

/**
 * @Author:黄广
 * @Description: 鸡尾酒排序 相比冒泡而言好一点，但在随机序列比较随机的时候，其和冒泡都一样效率差
 * @Date: Created in 19-5-22 下午5:00
 */
public class CocktailSort {

    public static void sort(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length / 2; i++) {
            //有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            //奇数轮 ，从左向右比较和交换
            for (int j = i; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    //有元素交换，所以不是有序的，标记为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
            //在偶数轮之前，将有序标记重新初始化
            isSorted = true;
            //偶数轮，从右向左比较和交换
            for (int j = array.length - i - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    //有元素交换，所以不是有序的，标记为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }

        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
