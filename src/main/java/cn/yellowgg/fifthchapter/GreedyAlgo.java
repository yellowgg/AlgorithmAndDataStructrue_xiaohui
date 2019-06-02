package cn.yellowgg.fifthchapter;

/**
 * @Author:黄广
 * @Description: 使用贪心算法，求出删除k个数字后的最小值
 * @Date: Created in 19-6-2 上午8:51
 */
public class GreedyAlgo {

    /**
     * 删除整数的k个数字，获取删除后的最小值
     * --这里用了两层循环，一层k，一层n，所以时间复杂度是O(kn)
     * --功能上没问题，性能就不怎么好
     * 1.每一次内层循环都需要从头开始遍历所有数字
     * 解决：我们应该停留在上一次删除的位置继续进行比较，而不是从头遍历比较
     * 2.subString方法本身性能不高，这个方法的底层实现涉及新字符串的创建以及逐个字符的复制，自身的时间复杂度是O(n)
     * 解决：应该避免在每删除一个数字后就调用subString方法
     *
     * @param num 原整数
     * @param k   删除数量
     */
    public static String removeDigits(String num, int k) {
        String numNew = num;
        for (int i = 0; i < k; i++) {
            boolean hasCut = false;
            //从左向右遍历，找到比自己右侧数字大的数字并删除
            for (int j = 0; j < numNew.length() - 1; j++) {
                if (numNew.charAt(j) > numNew.charAt(j + 1)) {
                    numNew = numNew.substring(0, j) + numNew.substring(j + 1,
                            numNew.length());
                    hasCut = true;
                    break;
                }
            }
            //如果没有找到要删除的数字，则删除最后一个数字
            if (!hasCut) {
                numNew = numNew.substring(0, numNew.length() - 1);
            }
            //清除整数左侧的数字0
            numNew = removeZero(numNew);
        }
        //如果整数的所有数字都删除了，直接返回0
        if (numNew.length() == 0) {
            return "0";
        }
        return numNew;
    }

    /**
     * 清除整数左侧的0
     */
    private static String removeZero(String num) {
        for (int i = 0; i < num.length() - 1; i++) {
            if (num.charAt(0) != '0') {
                break;
            }
            num = num.substring(1, num.length());
        }
        return num;
    }

    /**
     * 新的方法，以遍历数字为外循环，以k作为内循环
     */
    public static String removeDigitsV2(String num, int k) {
        //新整数的最终长度=原整数长度-k
        int newLength = num.length() - k;
        //创建一个栈，用于接收所有的数字
        char[] stack = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            //遍历当前数字
            char c = num.charAt(i);
            //当栈顶数字大于遍历到的当前数字时，栈顶数字出栈（相当于删除数字）
            while (top > 0 && stack[top - 1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            //遍历到的当前数字入栈
            stack[top++] = c;
        }
        //找到栈中第1个非零数字的位置，以此构建心得整数字符串
        int offset = 0;
        while (offset < newLength && stack[offset] == '0') {
            offset++;
        }
        return offset == newLength ? "0" : new String(stack, offset, newLength - offset);
    }


    public static void main(String[] args) {
        System.out.println(removeDigitsV2("1593212", 3));
        System.out.println(removeDigitsV2("30200", 1));
        System.out.println(removeDigitsV2("10", 2));
        System.out.println(removeDigitsV2("541270936", 3));
    }
}
