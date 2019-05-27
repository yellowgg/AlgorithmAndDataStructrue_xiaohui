package cn.yellowgg.fourthchapter;

import java.util.*;

/**
 * @Author:黄广
 * @Description: 桶排序，线性时间的排序算法
 * @Date: Created in 19-5-27 下午3:50
 */
public class BucketSort {

    /**
     * 所有的桶都保存在ArrayList集合中，每一个桶都被定义成为一个链表(Linked<Double>)，这样便于在尾部插入元素
     * 同时，使用了JDK的集合工具类Collections.sort为桶内部的元素进行排序
     * Collections.sort底层采用的是归并排序或Timsort，可以简单的当作一种时间复杂度为O(nlogn)的排序
     * @param array
     * @return
     */
    public static double[] bucketSort(double[] array) {
        //1.得到数列的最大值和最小值，并算出差值d
        double max = array[0];
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        double d = max - min;

        //2.初始化桶
        int bucketNum = array.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<LinkedList<Double>>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Double>());
        }

        //3.遍历原始数组，将每个元素放入桶中
        for (int i = 0; i < array.length; i++) {
            int num = (int) ((array[i] - min) * (bucketNum - 1) / d);
            bucketList.get(num).add(array[i]);
        }

        //4.对每个桶内部进行排序
        for (int i = 0; i < bucketList.size(); i++) {
            //JDK底层采用了归并排序或归并的优化版本
            Collections.sort(bucketList.get(i));
        }

        //5.输出全部元素
        double[] sortedArray = new double[array.length];
        int index = 0;
        for (LinkedList<Double> list : bucketList) {
            for (double element : list) {
                sortedArray[index++] = element;
            }
        }

        return sortedArray;
    }

    public static void main(String[] args) {
        double[] array = new double[]{4.12, 6.421, 0.0023, 3.0, 2.123, 8.122, 4.12,
                10.09};
        double[] sortedArray = bucketSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }
}
