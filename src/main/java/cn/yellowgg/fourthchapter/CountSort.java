package cn.yellowgg.fourthchapter;

import java.util.Arrays;

/**
 * @Author:黄广
 * @Description: 计数排序，不依靠元素比较
 * @Date: Created in 19-5-25 下午12:42
 */
public class CountSort {

    /**
     * 原版（朴素版）计数排序
     * <p>
     * 这个版本的计数排序有缺陷，比如数组为91,94,89,98,99 创建长度为99+1的数组，前面就浪费了
     *
     * @param arr
     * @return
     */
    public static int[] baseCountSort(int[] arr) {
        //1.得到数列的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }

        }
        //2.根据数列最大值确定统计数组的长度
        int[] countArray = new int[max + 1];
        //3.遍历数组，填充统计数组
        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i]]++;
        }
        //4.遍历统计数组，输出结果
        int index = 0;
        int[] sortedArray = new int[arr.length];
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                sortedArray[index++] = i;
            }
        }
        return sortedArray;
    }

    /**
     * 优化版计数排序
     *
     * @param arr
     * @return
     */
    public static int[] optimizeCountSort(int[] arr) {
        /**
         * 设原始数列的规模是n，最大和最小整数的差值是m
         * 时间复杂度分析：
         * 代码1,2,4步都涉及遍历原始数列，运算量都是n，第3步遍历统计数列，运算量是m
         * 所以总体运算量是3n+m，去掉系数，时间复杂度是O(n+m)
         * 空间复杂度分析：
         * 如果不考虑结果数组，只考虑统计数组大小，空间复杂度为O(m)
         */
        //1.得到数列的最大值和最小值，并算出差值d
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        int d = max - min;

        //2.创建统计数组并统计对应元素的个数
        int[] countArray = new int[d + 1];
        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i] - min]++;
        }

        //3.统计数组做变形，后面的元素等于前面的元素之和，从第二个元素开始
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        //4.倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组
        //经检验，倒序遍历虽然结果对，但名次不对，所以终究是错的
        int[] sortedAyyay = new int[arr.length];
        // for (int i = arr.length - 1; i >= 0; i--) {
        //     //这里-1是因为名次-1 比如第1名，应该存到sortedArray[0]
        //     sortedAyyay[countArray[arr[i] - min] - 1] = arr[i];
        //     countArray[arr[i] - min]--;
        // }

        //4.正序遍历原始数列，统计名次，升序
        //经检验，正序遍历才是稳定排序
        for (int i = 0; i < arr.length; i++) {
            //这里-1是因为名次-1 比如第1名，应该存到sortedArray[0]
            sortedAyyay[countArray[arr[i] - min] - 1] = arr[i];
            countArray[arr[i] - min]--;
        }
        return sortedAyyay;
    }

    public static void main(String[] args) {
        // int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10};
        // int[] sortedArray = baseCountSort(array);
        int[] array = new int[]{90, 99, 95, 94, 95};
        int[] sortedArray = optimizeCountSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }
}
