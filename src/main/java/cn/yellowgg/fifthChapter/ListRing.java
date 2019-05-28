package cn.yellowgg.fifthChapter;

/**
 * @Author:黄广
 * @Description: 面试算法：如何判定链表有环
 * 解题思路：追及问题
 * @Date: Created in 19-5-28 下午3:22
 */
public class ListRing {

    /**
     * 链表节点
     */
    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 判断是否有环
     *
     * @param head 链表头节点
     */
    public static boolean isCycle(Node head) {
        //定义两个指针指向头节点
        Node p1 = head;
        Node p2 = head;
        //追
        while (p2 != null && p2.next != null) {
            //p1移动一个节点
            p1 = p1.next;
            //p2移动两个节点
            p2 = p2.next.next;
            //判断
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;
        System.out.println(isCycle(node1));
    }
}
