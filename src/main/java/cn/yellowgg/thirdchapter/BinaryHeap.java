package cn.yellowgg.thirdchapter;

import java.util.Arrays;

/**
 * @Author:黄广
 * @Description: 二叉堆，顺序存储
 * 父节点的下标是parent
 * 左孩子的下标是2 X parent + 1
 * 右孩子的下标是2 X parent + 2
 * @Date: Created in 19-5-17 下午1:57
 */
public class BinaryHeap {

    /**
     * 构建堆
     *
     * @param array 待调整的堆
     */
    public static void buildHeap(int[] array) {
        //从最后一个非叶子节点开始，依次做“下沉”调整
        /**
         * i是最后一个叶子节点的下标，因为二叉堆是完全二叉树
         * 节点数量是偶数的话，最后一个叶子节点肯定是右孩子
         * 可推出父节点为(rightChild - 2) / 2
         * 节点数量是奇数的话，最后一个叶子节点肯定是左孩子
         * 可推出父节点为(leftChild - 1) / 2
         */
        //此处的数组数量是10,所以最后一个叶子节点是右孩子
        //然后非叶子节点都是下标--就可以了，这一切的前提就是二叉堆是完全二叉树
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
    }

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
     * “上浮”调整
     *
     * @param array 待调整的堆
     */
    public static void upAdjust(int[] array) {
        //获取最后一个节点的下标
        int childIndex = array.length - 1;
        //因为孩子节点的下标是奇数，所以知道孩子是左孩子，就用左孩子的方法推出父节点下标
        int parentIndex = (childIndex - 1) / 2;
        //temp 保存插入的叶子节点的值，用于最后的赋值
        int temp = array[childIndex];
        //当插入的值小于父节点的值就循环
        while (childIndex > 0 && temp < array[parentIndex]) {
            //父节点的值比孩子的小，父节点下沉，孩子上浮
            array[childIndex] = array[parentIndex];
            //插入节点的下标变成所交换的父节点的下标
            childIndex = parentIndex;
            //然后继续寻找新的父节点，这里无须分左右孩子寻找父节点，因为无论是左右孩子，
            //按照公式推下来，父节点都是同一个
            parentIndex = (parentIndex - 1) / 2;
        }
        //最后插入值就插到上浮最高的父节点
        array[childIndex] = temp;
    }

    public static void main(String[] args) {

        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
