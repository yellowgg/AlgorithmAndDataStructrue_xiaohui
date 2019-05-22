package cn.yellowgg.fourthchapter;

import java.util.Arrays;

/**
 * @Author:黄广
 * @Description: 冒泡排序
 * @Date: Created in 19-5-22 下午1:37
 */
public class BubbleSort {

    /**
     * 冒泡排序第一版 原始版本
     *
     * @param array
     */
    public static void sortOne(int array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    /**
     * 冒泡排序第二版 对已经有序的数列做标记
     * 问题所在
     * 当整个序列已经是有序的，但算法仍然执行比较下去，就浪费了
     * 解决思路
     * 做一个标记，每一趟比较时，弄一个标记，如果发生了交换，证明是乱序的，就继续比较
     * 如果期间一直没有发生改变，就证明是已经排好序的了，就退出整个大循环
     * 所以确切说这个版本的冒泡排序能优化掉最后一趟不必要的比较
     *
     * @param array
     */
    public static void sortTwo(int array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            //有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    //因为有元素交换 所以不是有序的，标记为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }


    /**
     * 冒泡排序第三版 记录有序边界
     * 问题所在
     * 如果整个数组有后半部分已经是有序的 然而算法仍然会比较后面的 这样也会浪费
     * 解决思路
     * 在每一轮排序之后 记录下来最后一次元素交换的位置，该位置为无序序列的边界，再往后就是有序区了
     *
     * @param array
     */
    public static void sortThree(int array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            //有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            //无边数列的边界，每次比较只需要比到这里为止，因为后面的肯定是有序的了
            int sortBorder = array.length - 1;
            for (int j = 0; j < sortBorder; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    //因为有元素交换 所以不是有序的，标记为false
                    isSorted = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    sortBorder = j;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        sortThree(array);
        System.out.println(Arrays.toString(array));
    }
}
