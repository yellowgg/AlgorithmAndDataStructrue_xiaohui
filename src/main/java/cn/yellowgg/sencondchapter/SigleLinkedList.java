package cn.yellowgg.sencondchapter;

/**
 * @Author:黄广
 * @Description: 单链表操作
 * 单链表的插入和删除操作，不考虑两者之前查找元素的过程，
 * 只考虑纯粹的插入和删除操作，时间复杂度都是O(1)。
 * @Date: Created in 19-5-15 下午2:07
 */
public class SigleLinkedList {

    //头节点指针
    private Node head;
    //尾节点指针
    private Node tail;
    //链表实际长度
    private int size;

    /**
     * 插入元素
     *
     * @param data  插入元素
     * @param index 插入位置
     * @throws Exception
     */
    public void insert(int data, int index) throws Exception {
        //先判断位置
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围！");
        }

        //如果可以插入，开始正常逻辑
        //1.生成一个新节点
        Node newNode = new Node(data);
        //2.根据插入位置而插入
        //2.1 链表为空，此时新节点就是第一个节点
        if (size == 0) {
            //让头指针和尾指针都指向这个新节点
            head = newNode;
            tail = newNode;
        } else if (size == index) {
            //2.2 插入的位置是链表尾部
            //2.2.1 先让最后的节点指向新节点
            tail.next = newNode;
            //2.2.2 然后让尾指针再指向这个新节点
            tail = newNode;
        } else {
            //2.3 插入的位置既不是头也不是尾，所以就是中间了
            //2.3.1 获取插入节点的前置节点和后置节点
            Node prevNode = get(index - 1);
            Node nextNode = prevNode.next;
            //2.3.2 前置节点指向插入节点
            prevNode.next = newNode;
            //2.3.3 插入节点指向后置节点
            newNode.next = nextNode;
        }
        //插完之后链表节点数增加
        size++;

    }

    /**
     * 删除元素
     *
     * @param index 删除位置
     * @return 删除的元素
     * @throws Exception
     */
    public Node remove(int index) throws Exception {
        //先判断位置
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围！");
        }

        //正常情况写逻辑
        //定义一个返回节点
        Node removeNode = null;
        if (index == 0) {
            //如果是删除头节点
            removeNode = head;
            //头指针往后移，头节点就没了
            head = head.next;
        } else if (index == size - 1) {
            //如果是删除尾节点
            //把倒数第二个节点指向null即可
            Node prevNode = get(index - 1);
            removeNode = prevNode.next;
            prevNode.next = null;
            //尾指针也换一下
            tail = prevNode;
        } else {
            //删除中间节点
            //让前置节点指向后置节点即可
            Node prevNode = get(index - 1);
            Node nextNode = prevNode.next.next;
            removeNode = prevNode.next;
            prevNode.next = nextNode;
        }
        //删除后链表长度减少
        size--;
        return removeNode;
    }

    /**
     * 查找元素
     * 查找的最坏情况下时间复杂度为O(n)
     *
     * @param index 查找的位置
     * @return
     * @throws Exception
     */
    public Node get(int index) throws Exception {
        //先判断位置
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围！");
        }
        //正常情况下写逻辑
        //临时节点定位到头指针，往后找
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 输出链表
     */
    public void output() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    /**
     * 链表节点
     */
    private static class Node {
        //数据域，这里就用int类型学习即可
        int data;
        //指针域，充当指向下一个节点的域
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws Exception {
        SigleLinkedList sigleLinkedList = new SigleLinkedList();
        // Node node = sigleLinkedList.get(0);
        // System.out.println(node.data);
        // sigleLinkedList.output();
        // sigleLinkedList.insert(1,2);
        sigleLinkedList.insert(3, 0);
        sigleLinkedList.insert(34723, 1);
        sigleLinkedList.insert(45, 2);
        sigleLinkedList.insert(152, 3);
        sigleLinkedList.insert(1, 4);
        System.out.println("输出链表：");
        sigleLinkedList.output();
        Node node = sigleLinkedList.get(1);
        System.out.println("第2个节点："+node.data);
        sigleLinkedList.insert(1234, 1);
        System.out.println("第2个节点插入元素，输出链表：");
        sigleLinkedList.output();
        sigleLinkedList.remove(3);
        System.out.println("删除第4个节点，输出链表：");
        sigleLinkedList.output();
    }
}

