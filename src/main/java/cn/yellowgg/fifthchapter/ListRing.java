package cn.yellowgg.fifthchapter;

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

    /**
     * 获取有环链表的环长度
     *
     * @param head 链表头节点
     */
    public static int getCycleLength(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null) {
            //p1移动一个节点
            p1 = p1.next;
            //p2移动两个节点
            p2 = p2.next.next;
            //当追上的时候，开始一个新的循环
            if (p1 == p2) {
                break;
            }
        }
        int length = 0;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            length++;
            if (p1 == p2) {
                return length;
            }
        }
        return 0;
    }

    /**
     * 获取入环节点
     *
     * @param head 链表头节点
     */
    public static Node getLinkNode(Node head) {
        //先获取入环点
        Node p1 = head;
        Node p2 = head;
        Node meetNode = null;
        while (p2 != null && p2.next != null) {
            //p1移动一个节点
            p1 = p1.next;
            //p2移动两个节点
            p2 = p2.next.next;
            //记录首次相遇点并终止循环
            if (p1 == p2) {
                meetNode = p1;
                break;
            }
        }
        //然后p1回到头节点，p2留在首次相遇点
        p1 = head;
        p2 = meetNode;
        while (p2 != null && p2.next != null) {
            //p1移动一个节点
            p1 = p1.next;
            //p2移动一个节点
            p2 = p2.next;
            //再次相遇就是入环节点
            if (p1 == p2) {
                return p1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        Node node6 = new Node(8);
        Node node7 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node4;
        if (isCycle(node1)) {
            System.out.println("该链表是有环链表");
            System.out.println("环长为" + getCycleLength(node1));
            System.out.println("入环节点为" + getLinkNode(node1).data);
        } else {
            System.out.println("该链表不是有环链表");
        }
    }
}
