package cn.yellowgg.sisthChapter;

/**
 * @Author:黄广
 * @Description: 位图算法
 * @Date: Created in 19-6-7 上午10:02
 */
public class MyBitMap {

    //每一个word是一个long类型元素，对应一个64位二进制数据
    private long[] words;

    //Bitmap的位数大小
    private int size;

    public MyBitMap(int size) {
        this.size = size;
        this.words = new long[(getWordIndex(size - 1) + 1)];
    }

    /**
     * 判断Bitmap某一位的状态
     *
     * @param bitIndex
     * @return
     */
    public boolean getBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超出Bitmap有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }

    /**
     * 定位Bitmap某一位所对应的word
     *
     * @param bitIndex 位图的第bitIndex位（bitIndex=0代表Bitmap左数第1位）
     */
    private int getWordIndex(int bitIndex) {
        //右移6位，相当于除以64
        return bitIndex >> 6;
    }

    /**
     * 把Bitmap某一位设置为true
     *
     * @param bitIndex 位图的第bitIndex位（bitIndex=0代表Bitmap左数第1位）
     */
    public void setBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超出Bitmap有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        words[wordIndex] |= (1L << bitIndex);
    }

    public static void main(String[] args) {
        MyBitMap myBitMap = new MyBitMap(128);
        myBitMap.setBit(126);
        myBitMap.setBit(75);
        System.out.println(myBitMap.getBit(126));
        System.out.println(myBitMap.getBit(75));
    }
}
