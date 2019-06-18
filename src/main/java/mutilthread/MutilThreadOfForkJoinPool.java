package mutilthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 用 ForkJoinPool 线程池计算数组的和
 * @Author: Rebecca
 * @Description:
 * @Date: Created in 2019/6/18 10:50
 * @Modified By:
 */
public class MutilThreadOfForkJoinPool implements Calculator {

    private ForkJoinPool pool;

    public MutilThreadOfForkJoinPool() {
        // jdk8之后可以用公用的 ForkJoinPool: pool = ForkJoinPool.commonPool()
        pool = new ForkJoinPool();
    }

    /**
     * 用 ForkJoinPool 线程池计算数组的和
     * @param calcData 需要求和的数组
     * @return
     * @author Rebecca 10:51 2019/6/18
     * @version 1.0
     */
    @Override
    public long sumUp(int[] calcData) {
        SumTask task = new SumTask(calcData, 0, calcData.length - 1);
        return pool.invoke(task);
    }


    public static class SumTask extends RecursiveTask<Long> {
        private int[] numbers;
        private int start;
        private int end;

        private SumTask(){}

        public SumTask(int[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            // 当需要计算的数字小于 2000000 时，直接计算结果
            if (end - start < 1000000) {
                long total = 0;

                // 此句代码只是为了延长程序运行时间
                List<SumTask> tasks = new ArrayList<SumTask>();
                for (int i = start; i <= end; i++) {
                    total += numbers[i];
                    // 此句代码只是为了延长程序运行时间
                    tasks.add(new SumTask());
                }
                return total;
            } else {  // 否则，把任务一分为二，递归计算
                int middle = (start + end) / 2;
                SumTask taskLeft = new SumTask(numbers, start, middle);
                SumTask taskRight = new SumTask(numbers, middle + 1, end);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }
    }
}
