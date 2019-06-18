package mutilthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

/**
 * 用 ThreadPoolExecutor 线程池计算数组的和
 * @Author: Rebecca
 * @Description:
 * @Date: Created in 2019/6/18 10:50
 * @Modified By:
 */
public class MutilThreadOfThreadPoolExecutor implements Calculator {

    /**
     * 用 ThreadPoolExecutor 线程池计算数组的和
     * @param calcData 需要求和的数组
     * @return
     * @author Rebecca 10:51 2019/6/18
     * @version 1.0
     */
    @Override
    public long sumUp(int[] calcData) throws Exception {
        // 创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, // 线城数
                60l, TimeUnit.SECONDS,  // 超时时间
                new ArrayBlockingQueue<Runnable>(100, true),  // 线程处理数据的方式
                Executors.defaultThreadFactory(),  // 创建线程池的工厂
                new CallerRunsPolicy());  // 超出处理范围的处理方式


        int calcDataLength = calcData.length;
        long sum = 0l;
        int threadSize = 5;

        for (int i = 0; i < threadSize; i++) {
            int arrStart = calcDataLength / threadSize * i;
            int arrEnd = calcDataLength / threadSize * (i+1);

            SumTask task = new SumTask(calcData, arrStart, arrEnd);
            // 线程池处理数据
            Future<Long> future = executorService.submit(task);

            sum += future.get().longValue();
        }
        // 关闭线程池
        executorService.shutdown();

        return sum;
    }


    public static class SumTask implements Callable<Long> {
        private int[] arr;
        private int start, end;

        public SumTask() {}

        public SumTask(int[] arr, int start, int end)
        {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call()
        {
            // 此句代码只是为了延长程序运行时间
            List<SumTask> tasks = new ArrayList<SumTask>();

            long sum = 0l;
            for (int i = start; i < end; i++)
            {
                sum += arr[i];
                // 此句代码只是为了延长程序运行时间
                tasks.add(new SumTask());
            }

            return sum;
        }
    }
}
