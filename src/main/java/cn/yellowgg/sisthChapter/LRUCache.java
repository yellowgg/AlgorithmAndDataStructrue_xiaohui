package cn.yellowgg.sisthChapter;

import java.util.HashMap;

/**
 * @Author:黄广
 * @Description: LRU算法，回收最近最少使用的数据，非线程安全
 * @Date: Created in 19-6-7 上午11:28
 */
public class LRUCache {

    private Node head;
    private Node end;
    //缓存存储上限
    private int limit;

    private HashMap<String, Node> hashMap;

    public LRUCache(int limit) {
        this.limit = limit;
        hashMap = new HashMap<String, Node>();
    }

    /**
     * 读取节点，节点先刷新到哈希链表尾部再读取
     */
    public String get(String key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    /**
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        Node node = hashMap.get(key);
        //如果是新节点
        if (node == null) {
            //链表超长的话就移除头节点，即最近最少访问的节点
            if (hashMap.size() >= limit) {
                String oldkey = removeNode(head);
                hashMap.remove(oldkey);
            }
            //没超长就直接加到尾部
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        } else {
            //如果是老节点，更新值之后刷新节点的位置到尾部
            node.value = value;
            refreshNode(node);
        }
    }

    /**
     * 移除节点
     *
     * @param key
     */
    public void romove(String key) {
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }

    /**
     * 刷新被访问的节点位置，总之就是把节点放到哈希链表最后
     *
     * @param node 被访问的节点
     */
    private void refreshNode(Node node) {
        //如果访问的是尾节点，则无须移动节点
        if (node == end) {
            return;
        }
        //从哈希链表中移除节点
        removeNode(node);
        //重新在尾部插入节点
        addNode(node);
    }

    /**
     * 删除节点
     *
     * @param node 要删除的节点
     */
    private String removeNode(Node node) {
        if (node == head && node == end) {
            //移除唯一的节点
            head = null;
            end = null;
        } else if (node == end) {
            //移除尾节点
            end = end.pre;
            end.next = null;
        } else if (node == head) {
            //移除头节点
            head = head.next;
        } else {
            //移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * 尾部插入节点
     *
     * @param node 要插入的节点
     */
    private void addNode(Node node) {
        if (end != null) {
            end.next = node;
            end.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null) {
            head = node;
        }
    }

    /**
     * 节点类
     */
    class Node {
        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public Node pre;
        public Node next;
        public String key;
        public String value;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put("001", "用户1信息");
        lruCache.put("002", "用户2信息");
        lruCache.put("003", "用户3信息");
        lruCache.put("004", "用户4信息");
        lruCache.put("005", "用户5信息");
        lruCache.get("002");
        lruCache.put("002", "用户2信息更新");
        lruCache.put("006", "用户6信息");
        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));

    }
}
