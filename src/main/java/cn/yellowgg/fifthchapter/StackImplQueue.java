package cn.yellowgg.fifthchapter;

import java.util.Stack;

/**
 * @Author:黄广
 * @Description: 用栈实现队列，一个栈不行就用两个
 * @Date: Created in 19-5-31 下午7:31
 */
public class StackImplQueue {
    private Stack<Integer> stackA = new Stack<Integer>();
    private Stack<Integer> stackB = new Stack<Integer>();

    /**
     * 入队操作
     * @param element
     */
    public void enQueue(int element) {
        //直接压入栈A
        stackA.push(element);
    }

    /**
     * 出队操作
     * @return
     */
    public Integer deQueue(){
        //如果栈B和栈A都为空，就返回null
        if (stackB.isEmpty()) {
            if (stackA.isEmpty()) {
                return null;
            }
            //元素迁移
            transfer();
        }
        //栈B有元素就弹出栈顶
        return stackB.pop();
    }

    /**
     * 栈A元素迁移到栈B
     */
    private void transfer() {
        while (!stackA.isEmpty()) {
            stackB.push(stackA.pop());
        }
    }

    public static void main(String[] args) {
        StackImplQueue siq = new StackImplQueue();
        siq.enQueue(1);
        siq.enQueue(2);
        siq.enQueue(3);
        System.out.println(siq.deQueue());
        siq.enQueue(4);
        siq.enQueue(5);
        siq.enQueue(6);
        System.out.println(siq.deQueue());
        System.out.println(siq.deQueue());
    }
}
