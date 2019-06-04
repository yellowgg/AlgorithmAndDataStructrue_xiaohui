package cn.yellowgg.fifthchapter;

/**
 * @Author:黄广
 * @Description: 如何求解金矿问题
 * 关键词：动态规划、贪心算法、类似背包问题、状态转移方程式、自底向上求解
 * @Date: Created in 19-6-4 下午2:05
 */
public class Goldproblem {

    /**
     * 获取金矿最优收益
     *
     * @param w 工人数量
     * @param n 可选金矿数量
     * @param p 金矿开采所需的工人数量
     * @param g 金矿储量
     */
    public static int getBestGoldMining(int w, int n, int[] p, int[] g) {
        if (w == 0 || n == 0) {
            return 0;
        }

        //如果所剩工人不够挖掘时，只剩一种最优子结构
        if (w < p[n - 1]) {
            return getBestGoldMining(w, n - 1, p, g);
        }

        //正常情况下，具有两行总最优子结构
        return Math.max(getBestGoldMining(w, n - 1, p, g),
                getBestGoldMining(w - p[n - 1], n - 1, p, g) + g[n - 1]);
    }

    /**
     * 自底向上求解，表格法
     *
     * @param w 工人数量
     * @param p 金矿开采所需的工人数量
     * @param g 金矿储量
     */
    public static int getBestGoldMiningV2(int w, int[] p, int[] g) {
        //创建表格
        int[][] resultTable = new int[g.length + 1][w + 1];

        //填充表格
        for (int i = 1; i <= g.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < p[i - 1]) {
                    resultTable[i][j] = resultTable[i - 1][j];
                } else {
                    resultTable[i][j] = Math.max(resultTable[i - 1][j],
                            resultTable[i - 1][j - p[i - 1]] + g[i - 1]);
                }

            }
        }

        //返回最后一个格子的值
        return resultTable[g.length][w];
    }

    /**
     * 自底向上求解，表格法，优化
     *
     * @param w 工人数量
     * @param p 金矿开采所需的工人数量
     * @param g 金矿储量
     */
    public static int getBestGoldMiningV3(int w, int[] p, int[] g) {
        //创建当前结果
        int[] result = new int[w + 1];
        //填充一维数组
        for (int i = 1; i <= g.length; i++) {
            for (int j = w; j >= 1; j--) {
                if (j >= p[i - 1]) {
                    result[j] = Math.max(result[j], result[j - p[i - 1]] + g[i - 1]);
                }
            }
        }

        //返回最后一个格子的值
        return result[w];
    }

    public static void main(String[] args) {
        int w = 10;
        int[] p = {5, 5, 3, 4, 3};
        int[] g = {400, 500, 200, 300, 350};
        System.out.println("最优收益：" + getBestGoldMining(w, g.length, p, g));
        System.out.println("最优收益：" + getBestGoldMiningV2(w, p, g));
        System.out.println("最优收益：" + getBestGoldMiningV3(w, p, g));
    }
}
