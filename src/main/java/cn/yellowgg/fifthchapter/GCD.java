package cn.yellowgg.fifthchapter;

/**
 * @Author:黄广
 * @Description: 最大公约数
 * @Date: Created in 19-5-29 下午3:38
 */
public class GCD {

    /**
     * 小灰的暴力解决方法
     * 效率：如果传的是10000 和 10001
     * 此方法就要循环10000/2-1=4999次
     */
    public static int getGreatestCommonDivisor(int a, int b) {
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        //如果两数能整除，小的数就是最大公约数
        if (big % small == 0) {
            return small;
        }
        //从较小整数的一半开始，试图找到一个整数i能否同时被a和b整除
        for (int i = small / 2; i > 1; i--) {
            if (small % i == 0 && big % i == 0) {
                return i;
            }
        }
        //如果找不到，最大公约数只能是1
        return 1;
    }

    /**
     * 辗转相除法
     * 效率：当两个整数较大时，做a%b取模运算的性能会比较差
     * 取模算法可以用更相减损术
     */
    public static int getGreatestCommonDivisorV2(int a, int b) {
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        if (big % small == 0) {
            return small;
        }
        //递归
        return getGreatestCommonDivisorV2(big % small, small);
    }

    /**
     * 更相减损术
     * 效率：此术是不稳定的算法，当两个整数相差较大时，相减的性能也很差
     * 比如计算10000和1的最大公约数时，就要递归9999次
     */
    public static int getGreatestCommonDivisorV3(int a, int b) {
        if (a == b) {
            return a;
        }
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        return getGreatestCommonDivisorV3(big - small, small);
    }

    /**
     * 终极版：辗转相除法和更相减损术结合，移位运算
     */
    public static int gcd(int a, int b) {
        //两者相等，则就是最大公约数
        if (a == b) {
            return a;
        }
        // >> 除2  << 乘2
        // 当a和b均为偶数时，gcd(a,b) = 2 x gcd(a/2,b/2) = 2 x gcd(a>>1,b>>1)
        if ((a & 1) == 0 && (b & 1) == 0) {
            return gcd(a >> 1, b >> 1) << 1;
        } else if ((a & 1) == 0 && (b & 1) != 0) {
            // 当a为偶数，b为奇数时，gcd(a,b) = gcd(a/2,b) = gcd(a>>1,b)
            return gcd(a >> 1, b);
        } else if ((a & 1) != 0 && (b & 1) == 0) {
            // 当a为奇数，b为偶数时，gcd(a,b) = gcd(a,b/2) = gcd(a,b>>1)
            return gcd(a, b >> 1);
        } else {
            // 当a和b均为奇数时，先利用更相减损术运算一次，gcd(a,b) = gcd(b,a-b)，此时a-b必然是偶数，然后又可以继续进行移位运算
            int big = a > b ? a : b;
            int small = a < b ? a : b;
            return gcd(big - small, small);
        }
    }

}
