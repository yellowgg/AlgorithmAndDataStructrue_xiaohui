package cn.yellowgg.thirdchapter;

import java.util.*;

/**
 * @Author:黄广
 * @Description: 二叉树
 * @Date: Created in 19-5-16 下午2:39
 */
public class BinaryTree {

    /**
     * 二叉树节点
     */
    private static class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * 构建二叉树
     * <p>
     * 此处的构建方法是将一个线性的链表转化成非线性的二叉树，链表节点的顺序恰好是前序遍历的顺序
     * <p>
     * 链表中的空值代表左孩子或右孩子为空的情况
     *
     * @param inputList 输入序列
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        //判断异常情况
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }

        TreeNode node = null;
        //依次获取链表的元素构建二叉树
        Integer data = inputList.removeFirst();
        if (data != null) {
            //构建根节点
            node = new TreeNode(data);
            //构建左节点
            node.leftChild = createBinaryTree(inputList);
            //构建右节点
            node.rightChild = createBinaryTree(inputList);
        }
        //最后返回给调用者的是根节点
        return node;
    }

    /**
     * 二叉树递归前序遍历
     *
     * @param node 根节点
     */
    public static void preOrderTraveral(TreeNode node) {
        //判断异常情况
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }

    /**
     * 二叉树递归中序遍历
     *
     * @param node 根节点
     */
    public static void inOrderTraveral(TreeNode node) {
        //判断异常情况
        if (node == null) {
            return;
        }
        inOrderTraveral(node.leftChild);
        System.out.println(node.data);
        inOrderTraveral(node.rightChild);
    }

    /**
     * 二叉树递归后序遍历
     *
     * @param node 根节点
     */
    public static void postOrderTraveral(TreeNode node) {
        //判断异常情况
        if (node == null) {
            return;
        }
        postOrderTraveral(node.leftChild);
        postOrderTraveral(node.rightChild);
        System.out.println(node.data);
    }

    /**
     * 二叉树非递归前序遍历
     *
     * @param root 二叉树根节点
     */
    public static void preOrderTraveralWithStack(TreeNode root) {
        //判断异常情况
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            //迭代访问节点的左孩子，并入栈
            while (treeNode != null) {
                System.out.println(treeNode.data);
                //输出后边入栈
                stack.push(treeNode);
                //指向左孩子
                treeNode = treeNode.leftChild;
            }

            //如果节点没有左孩子了，则弹出栈顶节点，继续访问节点的右孩子
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 二叉树非递归中序遍历
     *
     * @param root 二叉树根节点
     */
    public static void inOrderTraveralWithStack(TreeNode root) {
        //判断异常情况
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                System.out.println(treeNode.data);
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 二叉树非递归后序遍历
     *
     * @param root 二叉树根节点
     */
    public static void postOrderTraveralWithStack(TreeNode root) {
        //判断异常情况
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        TreeNode treeNode = root;
        stack1.push(treeNode);
        while (!stack1.isEmpty()) {
            treeNode = stack1.pop();
            stack2.push(treeNode);
            if (treeNode.leftChild != null) {
                stack1.push(treeNode.leftChild);
            }
            if (treeNode.rightChild != null) {
                stack1.push(treeNode.rightChild);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().data);
        }
    }

    /**
     * 二叉树非递归（队列）层序遍历
     *
     * @param root 二叉树根节点
     */
    public static void levelOrderTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.data);
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
    }


    /**
     * 获取二叉树的深度
     *
     * @param root 二叉树根节点
     * @return
     */
    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(getDepth(root.leftChild),
                    getDepth(root.rightChild)) + 1;
        }
    }

    /**
     * 使用递归方式层序遍历二叉树
     * @param root
     */
    public static void levelOrderTraversalWithRecursive(TreeNode root){
        //先获取二叉树深度
        int depth = getDepth(root);
        for (int i = 1; i <= depth ; i++) {
            //输出每层的节点
            printEveryLevel(root,i);
        }
    }

    /**
     * 输出所给层的节点
     * @param root 二叉树根节点
     * @param level 第几层
     */
    private static void printEveryLevel(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.println(root.data);
        }else if (level > 1) {
            printEveryLevel(root.leftChild,level - 1);
            printEveryLevel(root.rightChild,level - 1);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList =
                new LinkedList<Integer>(Arrays.asList(new Integer[]{3, 2, 9,
                        null, null, 10, null, null, 8, null, 4}));
        TreeNode node = createBinaryTree(inputList);
        System.out.println("前序遍历：");
        preOrderTraveral(node);
        System.out.println("中序遍历：");
        inOrderTraveral(node);
        System.out.println("后序遍历：");
        postOrderTraveral(node);
        System.out.println("非递归前序遍历：");
        preOrderTraveralWithStack(node);
        System.out.println("非递归中序遍历：");
        inOrderTraveralWithStack(node);
        System.out.println("非递归后序遍历：");
        postOrderTraveralWithStack(node);
        System.out.println("非递归层序遍历：");
        levelOrderTraversal(node);
        System.out.println("层序遍历：");
        levelOrderTraversalWithRecursive(node);
    }
}
