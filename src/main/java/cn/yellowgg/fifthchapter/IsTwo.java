package cn.yellowgg.fifthchapter;

/**
 * @Author:黄广
 * @Description: 判断一个正整数是否是2的整数次幂
 * @Date: Created in 19-5-30 下午7:20
 */
public class IsTwo {

    /**
     * 原始版判断
     */
    public static boolean isPowerOf2(int num) {
        int temp = 1;
        while (temp <= num) {
            if (temp == num) {
                return true;
            }
            temp *= 2;
        }
        return false;
    }

    /**
     * 利用了移位的性能比乘法快，但本质不变，时间复杂度依然是O(logn)
     */
    public static boolean isPowerOf2V2(int num) {
        int temp = 1;
        while (temp <= num) {
            if (temp == num) {
                return true;
            }
            temp = temp << 1;//左移1的意思是乘2,相对的，右移1的意思是除2
        }
        return false;
    }

    /**
     * 终极版判断，利用二进制推导出来的，详情可看笔记
     */
    public static boolean isPowerOf2V3(int num) {
        return (num & num - 1) == 0;
    }
}
