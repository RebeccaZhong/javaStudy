package mutilthread;

import java.util.ArrayList;
import java.util.List;

/**
 * 单线程的类
 * @Author: Rebecca
 * @Description:
 * @Date: Created in 2019/6/18 10:24
 * @Modified By:
 */
public class SingleThread implements Calculator {

    /**
     * 用单线程计算数组的和
     * @param calcData 需要求和的数组
     * @return
     * @author Rebecca 10:51 2019/6/18
     * @version 1.0
     */
    @Override
    public long sumUp(int[] calcData) {
        // 此句代码只是为了延长程序运行时间
        List<SingleThread> tasks = new ArrayList<SingleThread>();

        int calcDataLength = calcData.length;
        long sum = 0l;

        for (int i = 0; i < calcDataLength; i++) {
            sum += calcData[i];

            // 此句代码只是为了延长程序运行时间
            tasks.add(new SingleThread());
        }

        return sum;
    }

}
