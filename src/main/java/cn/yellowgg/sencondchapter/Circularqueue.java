package cn.yellowgg.sencondchapter;

/**
 * @Author:黄广
 * @Description: 循环队列
 * 公式：(队尾下标+1)%数组长度=队头下标
 * @Date: Created in 19-5-15 下午5:08
 */
public class Circularqueue {

    private int[] array;
    //队头
    private int front;
    //队尾
    private int tail;

    /**
     * @param capacity 容量大小
     */
    public Circularqueue(int capacity) {
        this.array = new int[capacity];
    }

    /**
     * 入队
     *
     * @param element 入队元素
     * @throws Exception
     */
    public void enQueue(int element) throws Exception {
        //判断队列容量
        if (isFull()) {
            throw new Exception("队列已满！");
        }
        //元素入队，队尾后移
        array[tail] = element;
        tail = (tail + 1) % array.length;
    }

    /**
     * 出队
     *
     * @return
     * @throws Exception
     */
    public int deQueue() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列以空！");
        }
        //元素出队，队头后移
        int deQueueElement = array[front];
        front = (front + 1) % array.length;
        return deQueueElement;

    }

    /**
     * 判断队列是否为满
     *
     * @return
     */
    public boolean isFull() {
        if ((tail + 1) % array.length == front) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        if (tail == front) {
            return true;
        } else {
            return false;
        }
    }


    public void output() {
        for (int i = front; i != tail; i = (i + 1) % array.length) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        Circularqueue circularqueue = new Circularqueue(6);
        circularqueue.enQueue(3);
        circularqueue.enQueue(5);
        circularqueue.enQueue(54);
        circularqueue.enQueue(6236);
        circularqueue.enQueue(235);
        circularqueue.output();
        circularqueue.deQueue();
        circularqueue.deQueue();
        circularqueue.deQueue();
        circularqueue.enQueue(2);
        circularqueue.enQueue(4);
        circularqueue.enQueue(92);
        circularqueue.output();

    }
}
