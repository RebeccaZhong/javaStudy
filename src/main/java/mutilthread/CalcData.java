package mutilthread;

import java.util.Random;

/**
 * 生成计算数据的类
 * @Author: Rebecca
 * @Description:
 * @Date: Created in 2019/6/18 10:25
 * @Modified By:
 */
public class CalcData {
    // 长度为1000万
    private static int calcDataLength = 10000000;

    public static int[] getCalcData() {
        Random random = new Random();
        int[] calcData = new int[calcDataLength];
        for (int i = 0; i < calcDataLength; i++) {
            // 0~10的随机数  生成[m,n]范围内指定的随机数： rand.nextInt(n -m + 1) +m;
            calcData[i] = random.nextInt(100001);
        }
        return calcData;
    }

}
