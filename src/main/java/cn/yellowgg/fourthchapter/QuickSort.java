package cn.yellowgg.fourthchapter;

import java.util.Arrays;

/**
 * @Author:黄广
 * @Description: 快速排序，有双边循环法和单边循环法
 * @Date: Created in 19-5-23 下午7:15
 */
public class QuickSort {

    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        //递归结束条件：当startIndex大于或等于endIndex时
        if (startIndex >= endIndex) {
            return;
        }
        //得到基准元素位置
        int pivotIndex = unilateralPartition(arr, startIndex, endIndex);
        //根据基准元素，分成两部分进行递归排序
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    /**
     * 分治（双边循环法）
     *
     * @param arr        待交换的数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * @return
     */
    private static int bilateralPartition(int[] arr, int startIndex, int endIndex) {
        //取第一个元素（也可以选择随机元素）作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        //在两个指针没重合之前就不断的移动
        while (left != right) {
            //先是right指针移动
            while (left < right && arr[right] > pivot) {
                right--;
            }
            //然后是left指针移动
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            //指针都停下，交换
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }

        //当两个指针重合，就跟基准元素交换
        arr[startIndex] = arr[left];
        arr[left] = pivot;

        //返回基准位置(left和right都是一个值)
        return left;
    }

    /**
     * 分治 (单边循环法)
     *
     * @param arr        待交换的数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * @return
     */
    private static int unilateralPartition(int[] arr, int startIndex,
                                           int endIndex) {
        //取第一个元素（也可以选择随机元素）作为基准元素
        int pivot = arr[startIndex];
        int mark = startIndex;

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (arr[i] < pivot) {
                //mark指针右移代表区域边界+1
                mark++;
                //交换两者的值
                int temp = arr[mark];
                arr[mark] = arr[i];
                arr[i] = temp;
            }
        }

        //交换mark元素跟基准元素
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;

        //返回基准元素的新位置
        return mark;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
