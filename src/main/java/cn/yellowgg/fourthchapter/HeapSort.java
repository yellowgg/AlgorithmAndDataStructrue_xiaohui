package cn.yellowgg.fourthchapter;

import java.util.Arrays;

/**
 * @Author:黄广
 * @Description: 堆排序，在二叉堆的源码上稍作改动
 * @Date: Created in 19-5-24 下午1:30
 */
public class HeapSort {

    /**
     * “下沉”调整
     * 本质是让所有非叶子节点依次下称下沉
     *
     * @param array       待调整的堆
     * @param parentIndex 要“下沉”的父节点
     * @param length      堆的有效大小
     */
    private static void downAdjust(int[] array, int parentIndex, int length) {
        //temp 保存父节点的值，用于最后的赋值
        int temp = array[parentIndex];
        //获取左孩子节点
        int childIndex = 2 * parentIndex + 1;
        //这里循环的原因是：一个父节点下沉之后，它会变成孩子，如果它还能继续下沉的话，就循环执行
        while (childIndex < length) {
            //如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            //因为如果有右孩子，还需要判断左孩子右孩子谁更小，毕竟父节点是跟最小的孩子交换的
            //所以在有右孩子的前提下， 如果左孩子比右孩子小，那么就没必要定位到右孩子了
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                //左孩子总比右孩子小1
                childIndex++;
            }
            //如果父节点小于任何一个孩子的值，则直接跳出 不用交换
            //其实经过上面的判断 childIndex总是最小的孩子了
            if (temp <= array[childIndex]) {
                break;
            }

            //如果父节点大于孩子，那么就换一下值
            //孩子的值给父节点
            array[parentIndex] = array[childIndex];
            //父节点的下标变成孩子的下标 下沉
            parentIndex = childIndex;
            //孩子继续变成左孩子，看看还能不能往下沉，这里其实就是跟48行那里一样的初始值
            //因为即使初始成左孩子，如果遇到更小的右孩子，孩子下标依然会+1的
            childIndex = 2 * childIndex + 1;
        }
        //父节点先前保留的值给孩子，因为此时parentIndex的下标是所交换的孩子的下标了
        array[parentIndex] = temp;
    }

    /**
     * 堆排序（升序）
     *
     * @param array
     */
    public static void heapSort(int[] array) {
        //1.把无序数组构建成最大堆
        for (int i = array.length / 2; i >= 0; i--) {
            downAdjust(array, i, array.length - 1);

        }
        System.out.println(Arrays.toString(array));
        //2.循环删除堆顶元素，移动到集合尾部，调整堆产生新的堆顶
        for (int i = array.length - 1; i > 0; i--) {
            //最后一个元素和第一个元素进行交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            //“下沉”调整最大堆
            downAdjust(array, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,2,6,5,7,8,9,10,0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
