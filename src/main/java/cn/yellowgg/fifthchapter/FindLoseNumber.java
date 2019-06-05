package cn.yellowgg.fifthchapter;

/**
 * @Author:黄广
 * @Description: 寻找缺失的整数
 * @Date: Created in 19-6-5 下午3:02
 */
public class FindLoseNumber {

    /**
     * 寻找缺失的整数，这个方法是题目扩展2的解法，也就是异或运算+分治法
     *
     * @param array
     * @return
     */
    public static int[] findLostNum(int[] array) {
        //用于存储2个出现奇数次的整数
        int[] result = new int[2];

        //第1次进行整体异或运算
        int xorResult = 0;
        for (int i = 0; i < array.length; i++) {
            xorResult ^= array[i];
        }

        //如果进行异或运算的结果为0,则说明输入的数组不符合题目要求
        if (xorResult == 0) {
            return null;
        }

        //确定2个整数的不同位，以此来做分组
        //这里的原理是当一个数做与运算时，只有1&1=1，所以就是从s=1&x开始
        //第一位，等于0,s就左移，然后第二位，等于1,说明x的1在倒数第二位，所以s最终=2
        int separator = 1;
        while (0 == (xorResult & separator)) {
            separator <<= 1;
        }

        //第二次进行整体异或运算
        for (int i = 0; i < array.length; i++) {
            //此时因为分隔符的原因，整个数组分成了两部分，一部分是倒数第s位为0,一部分是倒数第s为1
            //因为这两部分肯定是有一个1一个0才最终异或为1的
            if (0 == (array[i] & separator)) {
                result[0] ^= array[i];
            } else {
                result[1] ^= array[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array = {4, 1, 2, 2, 5, 1, 4, 3};
        int[] result = findLostNum(array);
        System.out.println(result[0] + "----" + result[1]);
    }
}
