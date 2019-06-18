package mutilThread;

import mutilthread.CalcData;
import mutilthread.MutilThreadOfForkJoinPool;
import mutilthread.MutilThreadOfThreadPoolExecutor;
import mutilthread.SingleThread;
import org.junit.Test;

/**
 * 线程测试类
 * @Author: Rebecca
 * @Description:
 * @Date: Created in 2019/6/18 10:40
 * @Modified By:
 */

public class ThreadTest {

    @Test
    public void testThread() throws Exception {
        int[] data = CalcData.getCalcData();
        // 单线程测试
        SingleThread singleThread = new SingleThread();
        long startTime = System.currentTimeMillis();
        System.out.println("数组的和： " + singleThread.sumUp(data));
        System.out.println("单线程耗时： " + (System.currentTimeMillis() - startTime) + " ms");

        // 多线程(ThreadPoolExecutor)测试
        MutilThreadOfThreadPoolExecutor threadPool = new MutilThreadOfThreadPoolExecutor();
        startTime = System.currentTimeMillis();
        System.out.println("数组的和： " + threadPool.sumUp(data));
        System.out.println("多线程(ThreadPoolExecutor)耗时： " + (System.currentTimeMillis() - startTime) + " ms");

        // 多线程(ForkJoinPool)测试
        MutilThreadOfForkJoinPool forkJoinPool = new MutilThreadOfForkJoinPool();
        startTime = System.currentTimeMillis();
        System.out.println("数组的和： " + forkJoinPool.sumUp(data));
        System.out.println("多线程(ForkJoinPool)耗时： " + (System.currentTimeMillis() - startTime) + " ms");
    }
}
