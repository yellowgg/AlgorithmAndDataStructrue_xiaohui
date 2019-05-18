package cn.yellowgg.thirdchapter;

import java.util.Arrays;

/**
 * @Author:黄广
 * @Description: 优先队列
 * 采用数组来存储二叉堆的元素，因此当元素数量超过数组长度时，需要进行扩容来扩大数组
 * @Date: Created in 19-5-18 下午12:53
 */
public class PriorityQueue {
    private int[] array;
    private int size;

    public PriorityQueue() {
        //队列初始化长度为32
        array = new int[32];
    }

    /**
     * 入队
     *
     * @param key 入队元素
     */
    public void enQueue(int key) {
        //队列长度超过范围，扩容
        if (size >= array.length) {
            resize();
        }
        array[size++] = key;
        upAdjust();
    }

    /**
     * 出队
     *
     * @throws Exception
     */
    public int deQueue() throws Exception {
        if (size <= 0) {
            throw new Exception("this queue is empty!");
        }
        //获取堆顶元素
        int head = array[0];
        //让最后一个元素移动到堆顶
        array[0] = array[--size];
        //调整
        downAdjust();
        return head;
    }

    /**
     * “下沉“调整
     */
    private void downAdjust() {
        //temp保存父节点的值，用于最后的赋值
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;
        while (childIndex < size) {
            //如果有右孩子且右孩子大于左孩子的值，定位到右孩子
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            //如果父节点大于任何一个孩子的值，跳出
            if (temp > array[childIndex]) {
                break;
            }

            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[childIndex] = temp;
    }


    /**
     * “上浮”调整
     */
    private void upAdjust() {
        //获取最后一个孩子，即插入的孩子
        int childIndex = size - 1;
        //获取父节点
        int parentIndex = childIndex / 2;
        int temp = array[childIndex];
        while (childIndex > 0 && temp > array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
        array[childIndex] = temp;
    }


    /**
     * 队列扩容
     */
    private void resize() {
        //扩容是队列容量翻倍
        int newSize = this.size * 2;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        System.out.println("出队元素:"+priorityQueue.deQueue());
        System.out.println("出队元素:"+priorityQueue.deQueue());
    }
}
